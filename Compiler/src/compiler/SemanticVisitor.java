package compiler;

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

import compiler.parsing.VSOPParser;
import compiler.vsop.SemanticError;
import compiler.vsop.VSOPBinOp;
import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPField;
import compiler.vsop.VSOPMethod;
import compiler.vsop.VSOPType;

import org.antlr.v4.runtime.ParserRuleContext;

import static compiler.vsop.VSOPConstants.*;

public class SemanticVisitor {

	private Map<Class<?>, Function<ParserRuleContext, ASTExpr>> exprDispatcher = new HashMap<>();

	private Map<String, VSOPClass> classMap = new HashMap<>();
	private VSOPClass currentClass;
	private boolean inFieldInit = false;

	public Queue<SemanticError> errorQueue = new LinkedList<>();
	private VariableStack varStack = new VariableStack();
	private Stack<VSOPMethod> methodStack = new Stack<>();

	public SemanticVisitor(Map<String, VSOPClass> classMap) {
		exprDispatcher.put(VSOPParser.IfContext.class, (ParserRuleContext c) -> visitIf((VSOPParser.IfContext) c));
		exprDispatcher.put(VSOPParser.WhileContext.class,
				(ParserRuleContext c) -> visitWhile((VSOPParser.WhileContext) c));
		exprDispatcher.put(VSOPParser.LetContext.class, (ParserRuleContext c) -> visitLet((VSOPParser.LetContext) c));
		exprDispatcher.put(VSOPParser.AssContext.class, (ParserRuleContext c) -> visitAss((VSOPParser.AssContext) c));
		exprDispatcher.put(VSOPParser.NotContext.class, (ParserRuleContext c) -> visitNot((VSOPParser.NotContext) c));
		exprDispatcher.put(VSOPParser.BinopContext.class,
				(ParserRuleContext c) -> visitBinop((VSOPParser.BinopContext) c));
		exprDispatcher.put(VSOPParser.MinusContext.class,
				(ParserRuleContext c) -> visitMinus((VSOPParser.MinusContext) c));
		exprDispatcher.put(VSOPParser.IsnullContext.class,
				(ParserRuleContext c) -> visitIsnull((VSOPParser.IsnullContext) c));
		exprDispatcher.put(VSOPParser.SelfcallContext.class,
				(ParserRuleContext c) -> visitSelfcall((VSOPParser.SelfcallContext) c));
		exprDispatcher.put(VSOPParser.CallContext.class,
				(ParserRuleContext c) -> visitCall((VSOPParser.CallContext) c));
		exprDispatcher.put(VSOPParser.NewContext.class, (ParserRuleContext c) -> visitNew((VSOPParser.NewContext) c));
		exprDispatcher.put(VSOPParser.OiContext.class, (ParserRuleContext c) -> visitOi((VSOPParser.OiContext) c));
		exprDispatcher.put(VSOPParser.SelfContext.class,
				(ParserRuleContext c) -> visitSelf((VSOPParser.SelfContext) c));
		exprDispatcher.put(VSOPParser.BraceExprContext.class,
				(ParserRuleContext c) -> visitBraceExpr((VSOPParser.BraceExprContext) c));

		this.classMap = classMap;
	}

	public void flushErrorQueue() {
		while (!errorQueue.isEmpty())
			errorQueue.poll().print();

		errorQueue.forEach((error) -> error.print());
	}

	public ASTProgram visitProgram(VSOPParser.ProgramContext ctx) {
		List<ASTClass> classes = new ArrayList<>(ctx.clazz().size());

		ctx.clazz().stream().map(this::visitClass).forEach(classes::add);

		return new ASTProgram(classes);
	}

	public ASTClass visitClass(VSOPParser.ClazzContext ctx) {
		String id = ctx.id.getText();
		currentClass = classMap.get(id);
		var body = ctx.classBody();

		List<ASTField> fields = new ArrayList<>();
		body.field().stream().map(this::visitField).forEach(fields::add);

		List<ASTMethod> methods = new ArrayList<>();
		body.method().stream().map(this::visitMethod).forEach(methods::add);

		return new ASTClass(currentClass, fields, methods);
	}

	public ASTField visitField(VSOPParser.FieldContext ctx) {
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

		return new ASTField(field, value);
	}

	public ASTMethod visitMethod(VSOPParser.MethodContext ctx) {
		String id = ctx.getText();

		List<ASTFormal> formals = new ArrayList<>(ctx.formals().formal().size());
		ctx.formals().formal().stream().map(this::visitFormal).forEach(formals::add);

		VSOPMethod method = currentClass.functions.get(id);
		for (var field : method.args)
			varStack.push(field.id, field.type);

		ASTBlock block = visitBlock(ctx.block());

		for (var field : method.args)
			varStack.pop(field.id);

		if (!block.type.canCast(method.ret))
			errorQueue.add(new SemanticError(ctx.block(),
					String.format("Method should return %s but got %s instead", method.ret.id, block.type.id)));

		return new ASTMethod(formals, block);
	}

	public ASTFormal visitFormal(VSOPParser.FormalContext ctx) {
		return null;
	}

	public ASTBlock visitBlock(VSOPParser.BlockContext ctx) {
		List<ASTExpr> expressions = new ArrayList<>(ctx.expr().size());

		ctx.expr().stream().map(this::visitExpr).forEach(expressions::add);

		VSOPType type = expressions.isEmpty() ? UNIT : expressions.get(expressions.size() - 1).type;

		return new ASTBlock(expressions, type);
	}

	public ASTExpr visitExpr(VSOPParser.ExprContext ctx) {
		ASTExpr expr = exprDispatcher.get(ctx.getClass()).apply(ctx);
		return expr;
	}

	public List<ASTExpr> visitArgs(VSOPParser.ArgsContext ctx) {
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

	public ASTLiteral visitLiteral(VSOPParser.LiteralContext ctx) {
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

	public VSOPType getType(VSOPParser.TypeContext ctx) {
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

	public ASTAss visitAss(VSOPParser.AssContext ctx) {
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

		return new ASTAss(varType, expr);
	}

	public ASTNew visitNew(VSOPParser.NewContext ctx) {
		String id = ctx.id.getText();
		VSOPType type = classMap.get(id);
		if (type == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Invalid type indentifier %s", id)));

		return new ASTNew(type);
	}

	public ASTWhile visitWhile(VSOPParser.WhileContext ctx) {
		ASTExpr condition = visitExpr(ctx.expr(0));
		ASTExpr body = visitExpr(ctx.expr(1));

		if (!condition.type.canCast(BOOL))
			errorQueue.add(new SemanticError(ctx.expr(0),
					String.format("Expected %s but got type %s", BOOL.id, condition.type.id)));

		return new ASTWhile(condition, body);
	}

	public ASTNot visitNot(VSOPParser.NotContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (expr.type != BOOL) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"not operator can only be used with BOOL"));
		}

		return new ASTNot(expr);
	}

	public ASTMinus visitMinus(VSOPParser.MinusContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (expr.type != INT32) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"minus operator can only be used with INT32"));
		}

		return new ASTMinus(expr);
	}

	public ASTIsnull visitIsnull(VSOPParser.IsnullContext ctx) {
		ASTExpr expr = visitExpr(ctx.expr());

		if (primitiveTypes.contains(expr.type))
			errorQueue.add(new SemanticError(ctx, "isnull operator can only be used with non primitive types"));

		return new ASTIsnull(expr);
	}

	public ASTCall visitSelfcall(VSOPParser.SelfcallContext ctx) {
		String id = ctx.id.getText();

		VSOPMethod method = currentClass.functions.get(id);
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

	public ASTCall visitCall(VSOPParser.CallContext ctx) {
		String id = ctx.id.getText();

		ASTExpr object = visitExpr(ctx.expr());

		VSOPMethod method = null;
		if (object.type instanceof VSOPClass) {
			VSOPClass exprClass = (VSOPClass) object.type;
			method = exprClass.functions.get(id);

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

	public ASTSelf visitSelf(VSOPParser.SelfContext ctx) {
		return new ASTSelf(currentClass);
	}

	public ASTLet visitLet(VSOPParser.LetContext ctx) {

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

		return new ASTLet(varType, id, value, in);
	}

	public ASTOi visitOi(VSOPParser.OiContext ctx) {
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

	public ASTIf visitIf(VSOPParser.IfContext ctx) {

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

	public ASTExpr visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		return visitExpr(ctx.expr());
	}

	public ASTBinop visitBinop(VSOPParser.BinopContext ctx) {
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
