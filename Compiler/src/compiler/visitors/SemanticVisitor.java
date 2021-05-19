package compiler.visitors;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

import compiler.ast.*;
import compiler.error.SemanticError;
import compiler.parsing.VSOPParser.*;
import compiler.util.Pair;
import compiler.util.Triplet;
import compiler.util.VariableStack;
import compiler.vsop.VSOPBinOp;
import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPField;
import compiler.vsop.VSOPMethod;
import compiler.vsop.VSOPType;

import static compiler.vsop.VSOPConstants.*;

/**
 * A class that performs semantic analysis of a VSOP program.
 */
public class SemanticVisitor {

	private PrintStream err;

	private Map<Class<?>, Function<ExprContext, ASTExpr>> dispatcher;

	private Map<String, VSOPClass> classMap;
	private Map<VSOPMethod, ASTExpr> methods = new HashMap<>();
	private Map<VSOPField, Optional<ASTExpr>> fields = new HashMap<>();
	private VSOPClass currentClass;
	private boolean inFieldInit = false;

	public Queue<SemanticError> errorQueue = new LinkedList<>();
	private VariableStack varStack = new VariableStack();
	private Stack<VSOPMethod> methodStack = new Stack<>();

	/**
	 * Creates a new {@link SemanticVisitor} given a {@link Map} of defined classes and
	 * a {@link PrintStream} to print errors.
	 * @param classMap - The map of defined classes
	 * @param err - The stream where errors are printed
	 */
	public SemanticVisitor(Map<String, VSOPClass> classMap, PrintStream err) {
		this.classMap = classMap;
		this.err = err;
		
		dispatcher = Map.ofEntries(
			Map.entry(IfContext.class,  c -> visitIf((IfContext) c)),
			Map.entry(WhileContext.class, c -> visitWhile((WhileContext) c)),
			Map.entry(LetContext.class, c -> visitLet((LetContext) c)),
			Map.entry(AssContext.class, c -> visitAss((AssContext) c)),
			Map.entry(NotContext.class, c -> visitNot((NotContext) c)),
			Map.entry(BinopContext.class, c -> visitBinop((BinopContext) c)),
			Map.entry(MinusContext.class, c -> visitMinus((MinusContext) c)),
			Map.entry(IsnullContext.class, c -> visitIsnull((IsnullContext) c)),
			Map.entry(SelfcallContext.class, c -> visitSelfcall((SelfcallContext) c)),
			Map.entry(CallContext.class, c -> visitCall((CallContext) c)),
			Map.entry(NewContext.class, c -> visitNew((NewContext) c)),
			Map.entry(OiContext.class, c -> visitOi((OiContext) c)),
			Map.entry(SelfContext.class, c -> visitSelf((SelfContext) c)),
			Map.entry(BraceExprContext.class, c -> visitBraceExpr((BraceExprContext) c)),
			Map.entry(LitContext.class, c -> visitLit((LitContext) c)),
			Map.entry(BlContext.class, c -> visitBl((BlContext) c)),
			Map.entry(UnitContext.class, c -> visitUnit((UnitContext) c))
		);
	}

	/**
	 * Creates a new {@link SemanticVisitor} with the default error stream: {@link System#err}
	 * @param classMap - The map of defined classes
	 * @see SemanticVisitor#SemanticVisitor(Map, PrintStream)
	 */
	public SemanticVisitor(Map<String, VSOPClass> classMap) {
		this(classMap, System.err);
	}

	/**
	 * Prints and flushes any error in the {@link #errorQueue}
	 * @return <code>true</code> if there were no errors, <code>false</code> otherwise.
	 */
	private boolean flushErrorQueue() {
		if(errorQueue.isEmpty())
			return true;

		errorQueue.forEach(err::println);
		errorQueue.clear();

		return false;
	}

	/**
	 * Proceed to semantic checking of the program. It also create the AST using the Visitor methodology.
	 * @param ctx - The {@link ProgramContext}
	 * @return the {@link ASTProgram} corresponding to the given context if there is no error,
	 * <code>null</code> otherwise.
	 */
	public Triplet<ASTProgram, Map<VSOPField, Optional<ASTExpr>>, Map<VSOPMethod, ASTExpr>> check(ProgramContext ctx) {
		ASTProgram program = visitProgram(ctx);
		if(flushErrorQueue())
			return new Triplet<>(program, fields, methods);
		
		return null;
	}

	private ASTProgram visitProgram(ProgramContext ctx) {
		List<ASTClass> classes = new ArrayList<>(ctx.clazz().size());
		ctx.clazz().stream()
				.map(this::visitClass)
				.forEach(classes::add);

		return new ASTProgram(classes);
	}

	private ASTClass visitClass(ClazzContext ctx) {
		String id = ctx.id.getText();
		currentClass = classMap.get(id);
		var body = ctx.classBody();

		List<ASTField> fields = new ArrayList<>();
		body.field().stream().map(this::visitField).forEach(fields::add);

		List<ASTMethod> methods = new ArrayList<>();
		body.method().stream().map(this::visitMethod).forEach(methods::add);

		return new ASTClass(currentClass, fields, methods);
	}

	private ASTField visitField(FieldContext ctx) {
		inFieldInit = true;

		VSOPField field = currentClass.fields.get(ctx.id.getText());
		Optional<ASTExpr> value;

		if (ctx.expr() != null) {
			VSOPType fType = getType(ctx.type());
			ASTExpr exprType = visitExpr(ctx.expr());
			value = Optional.of(exprType);

			if (!exprType.type.canCast(fType))
				errorQueue.add(new SemanticError(ctx.expr(),
						String.format("cannot cast %s to %s", exprType.type.id, fType.id)));
		} else {
			value = Optional.empty();
		}

		inFieldInit = false;

		fields.put(field, value);

		return new ASTField(field, value);
	}

	private ASTMethod visitMethod(MethodContext ctx) {
		String id = ctx.id.getText();

		List<ASTFormal> formals = new ArrayList<>(ctx.formals().formal().size());
		ctx.formals().formal().stream().map(this::visitFormal).forEach(formals::add);

		VSOPMethod method = currentClass.methods().get(id);
		for (var field : method.args)
			varStack.push(field.id, field.type);

		ASTBlock block = visitBlock(ctx.block());
		
		/*
		 * TODO Check ça
		 * /!\ IMPORTANT /!\
		 */
		methods.put(method, block);

		for (var field : method.args)
			varStack.pop(field.id);

		if (!block.type.canCast(method.returnType))
			errorQueue.add(new SemanticError(ctx.block(),
					String.format("Method should return %s but got %s instead", method.returnType.id, block.type.id)));

		return new ASTMethod(method, formals, block);
	}

	private ASTFormal visitFormal(FormalContext ctx) {
		return new ASTFormal(ctx.id.getText(), getType(ctx.type()));
	}

	private ASTBlock visitBl(BlContext ctx) {
		return visitBlock(ctx.block());
	}

	private ASTBlock visitBlock(BlockContext ctx) {
		List<ASTExpr> expressions = new ArrayList<>(ctx.expr().size());

		ctx.expr().stream().map(this::visitExpr).forEach(expressions::add);

		VSOPType type = expressions.isEmpty() ? UNIT : expressions.get(expressions.size() - 1).type;

		return new ASTBlock(expressions, type);
	}

	private ASTExpr visitExpr(ExprContext ctx) {
		ASTExpr expr = dispatcher.get(ctx.getClass()).apply(ctx);
		return expr;
	}

	public List<ASTExpr> visitArgs(ArgsContext ctx) {
		VSOPMethod method = methodStack.peek();

		if (method != null && method.args.size() != ctx.expr().size()) {
			errorQueue.add(new SemanticError(ctx, String.format("method %s expected %d args but got %d", method.id,
					method.args.size(), ctx.expr().size())));
		}

		List<ASTExpr> args = new ArrayList<>(ctx.expr().size());

		int i = 0;
		for (var expr : ctx.expr()) {
			ASTExpr argType = visitExpr(expr);
			args.add(argType);

			if (method != null && i < method.args.size()) {
				if (!argType.type.canCast(method.args.get(i).type)) {
					errorQueue.add(new SemanticError(ctx.expr(i),
							String.format("cannot cast from %s to %s", argType.type.id, method.args.get(i).type.id)));
				}
			}
			i++;
		}
		return args;
	}

	private ASTLiteral visitLit(LitContext ctx) {
		return visitLiteral(ctx.literal());
	}

	private ASTLiteral visitUnit(UnitContext ctx) {
		return new ASTLiteral(UNIT, ctx.getText());
	}

	private ASTLiteral visitLiteral(LiteralContext ctx) {
		VSOPType type = UNIT;
		if (ctx.INTEGER_LITERAL() != null) {
			type = INT32;
		}

		if (ctx.STRING_LITERAL() != null) {
			type = STRING;
		}

		if (ctx.booleanLiteral() != null) {
			type = BOOL;
		}

		return new ASTLiteral(type, ctx.getText());
	}

	public VSOPType getType(TypeContext ctx) {
		String typeStr = ctx.getText();
		VSOPType type = primitiveTypeMap.get(typeStr);

		if (type != null) {
			return type;
		}

		type = classMap.get(typeStr);

		if (type == null) {
			errorQueue.add(new SemanticError(ctx, String.format("Type %s is undefined", typeStr)));
		}

		return type;
	}

	private ASTAss visitAss(AssContext ctx) {
		String id = ctx.id.getText();
		ASTExpr expr = visitExpr(ctx.expr());
		VSOPType varType = varStack.get(id);

		if (varType == null) {
			VSOPField f = currentClass.fields.get(id);
			if (f != null)
				varType = f.type;
		}

		if (varType == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Variable %s is not declared in this scope.", id)));

		if (varType != null && !expr.type.canCast(varType))
			errorQueue.add(new SemanticError(ctx.expr(), "Expression type does not match variable type"));

		return new ASTAss(varType, id, expr);
	}

	private ASTNew visitNew(NewContext ctx) {
		String id = ctx.id.getText();
		VSOPType type = classMap.get(id);
		if (type == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Invalid type indentifier %s", id)));

		return new ASTNew(type);
	}

	private ASTWhile visitWhile(WhileContext ctx) {
		ASTExpr condition = visitExpr(ctx.expr(0));
		ASTExpr body = visitExpr(ctx.expr(1));

		if (!condition.type.canCast(BOOL))
			errorQueue.add(new SemanticError(ctx.expr(0),
					String.format("Expected %s but got type %s", BOOL.id, condition.type.id)));

		return new ASTWhile(condition, body);
	}

	private ASTNot visitNot(NotContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (expr.type != BOOL) {
			errorQueue.add(new SemanticError(ctx, "not operator can only be used with BOOL"));
		}

		return new ASTNot(expr);
	}

	private ASTMinus visitMinus(MinusContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (expr.type != INT32) {
			errorQueue.add(new SemanticError(ctx, "minus operator can only be used with INT32"));
		}

		return new ASTMinus(expr);
	}

	private ASTIsnull visitIsnull(IsnullContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (primitiveTypes.contains(expr.type))
			errorQueue.add(new SemanticError(ctx, "isnull operator can only be used with non primitive types"));

		return new ASTIsnull(expr);
	}

	private ASTCall visitSelfcall(SelfcallContext ctx) {
		String id = ctx.id.getText();

		VSOPMethod method = currentClass.methods().get(id);
		if (inFieldInit) {
			errorQueue.add(new SemanticError(ctx, "illegal call to self in field initializer"));
		} else if (method == null) {
			errorQueue.add(
					new SemanticError(ctx, String.format("method %s is undefined for type %s", id, currentClass.id)));
		}

		methodStack.push(method);
		List<ASTExpr> args = visitArgs(ctx.args());
		methodStack.pop();

		return new ASTCall(method, new ASTSelf(currentClass), args);
	}

	private ASTCall visitCall(CallContext ctx) {
		String id = ctx.id.getText();

		ASTExpr object = visitExpr(ctx.expr());

		VSOPMethod method = null;
		if (object.type instanceof VSOPClass) {
			VSOPClass exprClass = (VSOPClass) object.type;
			method = exprClass.methods().get(id);

			if (method == null) {
				errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
						String.format("method %s is undefined for type %s", id, exprClass.id)));
			}

			methodStack.push(method);
		} else {
			methodStack.push(null);
		}

		List<ASTExpr> args = visitArgs(ctx.args());
		methodStack.pop();

		return new ASTCall(method, object, args);
	}

	private ASTSelf visitSelf(SelfContext ctx) {
		return new ASTSelf(currentClass);
	}

	private ASTLet visitLet(LetContext ctx) {

		String id = ctx.id.getText();
		VSOPType varType = getType(ctx.type());
		varStack.push(id, varType);

		Optional<ASTExpr> value = (ctx.as != null) ? Optional.of(visitExpr(ctx.as)) : Optional.empty();

		if (value.isPresent() && !value.get().type.canCast(varType)) {
			errorQueue.add(
					new SemanticError(ctx, String.format("Cannot convert %s to %s", value.get().type.id, varType.id)));
		}

		ASTExpr in = visitExpr(ctx.ex);
		varStack.pop(id);

		return new ASTLet(in.type, id, varType, value, in);
	}

	private ASTOi visitOi(OiContext ctx) {
		String id = ctx.id.getText();

		VSOPType type = varStack.get(id);

		if (type == null) {
			VSOPField f = currentClass.fields.get(id);

			if (f == null || inFieldInit) {
				errorQueue.add(new SemanticError(ctx, String.format("Variable %s is not declared in this scope", id)));
			} else {
				type = f.type;
			}
		}

		return new ASTOi(type, id);
	}

	private ASTIf visitIf(IfContext ctx) {

		ASTExpr condition = visitExpr(ctx.expr(0));

		if (condition.type != BOOL) {
			errorQueue.add(new SemanticError(ctx,
					String.format("Condition must be of type BOOL but got %s", condition.type.id)));
		}

		ASTExpr then = visitExpr(ctx.expr(1));
		Optional<ASTExpr> elze;

		if (ctx.expr(2) != null) {
			elze = Optional.of(visitExpr(ctx.expr(2)));
		} else {
			elze = Optional.empty();
		}

		VSOPType type;
		if (elze.isPresent()) {
			VSOPType type1 = then.type;
			VSOPType type2 = elze.get().type;

			if (type1 instanceof VSOPClass && type2 instanceof VSOPClass) {
				type = VSOPClass.commonAncestor((VSOPClass) type1, (VSOPClass) type2);
			} else if (type1 == UNIT || type2 == UNIT) {
				type = UNIT;
			} else if (type1 == type2) {
				type = type1;
			} else {
				errorQueue.add(
						new SemanticError(ctx, String.format("Type %s and %s does not match", type1.id, type2.id)));
				type = null;
			}
		} else {
			type = UNIT;
		}
		return new ASTIf(type, condition, then, elze);
	}

	private ASTExpr visitBraceExpr(BraceExprContext ctx) {
		return visitExpr(ctx.expr());
	}

	private ASTBinop visitBinop(BinopContext ctx) {
		String operatorId = ctx.op.getText();
		ASTExpr leftExpr = visitExpr(ctx.expr(0));
		ASTExpr rightExpr = visitExpr(ctx.expr(1));

		VSOPBinOp operator = binOpMap.get(operatorId);

		if (operator == null) {
			errorQueue.add(new SemanticError(ctx, "Unexpected error occur while trying to parse operator"));
			return new ASTBinop(operator, leftExpr, rightExpr);
		}

		VSOPType leftType = leftExpr.type;
		VSOPType rightType = rightExpr.type;

		if (operator == EQ) {
			if (leftType instanceof VSOPClass && !(rightType instanceof VSOPClass)
					|| !(leftType instanceof VSOPClass) && rightType instanceof VSOPClass) {
				errorQueue.add(new SemanticError(ctx, "Trying to compare primitive type with class type"));
			} else if (!(leftType instanceof VSOPClass) && !(rightType instanceof VSOPClass) && leftType != rightType) {
				errorQueue.add(new SemanticError(ctx, "Trying to compare primitive types that does not match"));
			}
		} else {
			if (leftType != operator.opType) {
				errorQueue
						.add(new SemanticError(ctx.expr(0), String.format("Expected %s but got %s type for operator %s",
								operator.opType.id, leftType.id, operator.id)));
			}
			if (rightType != operator.opType) {
				errorQueue
						.add(new SemanticError(ctx.expr(1), String.format("Expected %s but got %s type for operator %s",
								operator.opType.id, rightType.id, operator.id)));
			}
		}

		return new ASTBinop(operator, leftExpr, rightExpr);
	}
}
