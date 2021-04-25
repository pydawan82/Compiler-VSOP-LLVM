package compiler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

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

	private Map<Class<?>, Function<ParserRuleContext, VSOPType>> exprDispatcher = new HashMap<>();

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
		exprDispatcher.put(VSOPParser.LitContext.class, (ParserRuleContext c) -> visitLit((VSOPParser.LitContext) c));
		exprDispatcher.put(VSOPParser.SelfContext.class,
				(ParserRuleContext c) -> visitSelf((VSOPParser.SelfContext) c));
		exprDispatcher.put(VSOPParser.UnitContext.class,
				(ParserRuleContext c) -> visitUnit((VSOPParser.UnitContext) c));
		exprDispatcher.put(VSOPParser.BraceExprContext.class,
				(ParserRuleContext c) -> visitBraceExpr((VSOPParser.BraceExprContext) c));
		exprDispatcher.put(VSOPParser.BlContext.class, (ParserRuleContext c) -> visitBl((VSOPParser.BlContext) c));

		this.classMap = classMap;
	}

	public void flushErrorQueue() {
		while (!errorQueue.isEmpty())
			errorQueue.poll().print();

		errorQueue.forEach((error) -> error.print());
	}

	public ASTProgram visitProgram(VSOPParser.ProgramContext ctx) {
		List<ASTClass> classes = new ArrayList<>(ctx.clazz().size());
		
		ctx.clazz().stream()
			.map(this::visitClass)
			.forEach(classes::add);

		return new ASTProgram(classes);
	}

	public ASTClass visitClass(VSOPParser.ClazzContext ctx) {
		String id = ctx.id.getText();
		currentClass = classMap.get(id);
		var body = ctx.classBody();

		List<ASTField> fields = new ArrayList<>();
		body.field().stream()
			.map(this::visitField)
			.forEach(fields::add);

		List<ASTMethod> methods = new ArrayList<>();
		body.method().stream()
			.map(this::visitMethod)
			.forEach(methods::add);

		return new ASTClass(currentClass, fields, methods);
	}

	public ASTField visitField(VSOPParser.FieldContext ctx) {
		inFieldInit = true;

		if (ctx.expr() != null) {
			VSOPType fType = getType(ctx.type());
			VSOPType exprType = visitExpr(ctx.expr());
			if (!exprType.canCast(fType))
				errorQueue.add(
						new SemanticError(ctx.expr(), String.format("cannot cast %s to %s", exprType.id, fType.id)));
		}
		
		inFieldInit = false;
		return new ASTField();
	}

	public ASTMethod visitMethod(VSOPParser.MethodContext ctx) {
		String id = ctx.getText();
		visitFormals(ctx.formals());
		
		VSOPMethod method = currentClass.functions.get(id);
		for (var field : method.args)
			varStack.push(field.id, field.type);

		VSOPType ret = visitBlock(ctx.block());

		for (var field : method.args)
			varStack.pop(field.id);

		if (!ret.canCast(method.ret))
			errorQueue.add(new SemanticError(ctx.block(),
					String.format("Method should return %s but got %s instead", method.ret.id, ret.id)));

		return new ASTMethod();
	}

	public ASTFormal visitFormal(VSOPParser.FormalContext ctx) {
		return null;
	}

	public ASTBlock visitBlock(VSOPParser.BlockContext ctx) {
		VSOPType type = UNIT;

		for (var expr : ctx.expr()) {
			type = visitExpr(expr);

		}
		return type;
	}

	public ASTExpr visitExpr(VSOPParser.ExprContext ctx) {
		VSOPType type = exprDispatcher.get(ctx.getClass()).apply(ctx);
		return type;
	}

	public ASTArgs visitArgs(VSOPParser.ArgsContext ctx) {
		VSOPMethod method = methodStack.peek();

		if (method != null && method.args.size() != ctx.expr().size()) {
			errorQueue.add(new SemanticError(ctx, String.format("method %s expected %d args but got %d", method.id,
					method.args.size(), ctx.expr().size())));
		}

		i = 0;

		for (var expr : ctx.expr()) {
			VSOPType argType = visitExpr(expr);
			if (method != null && i < method.args.size()) {
				if (!argType.canCast(method.args.get(i).type)) {
					errorQueue.add(new SemanticError(ctx.expr(i),
							String.format("cannot cast from %s to %s", argType.id, method.args.get(i).type.id)));
				}
			}
			i++;
		}
		return null;
	}

	public ASTLiteral visitLiteral(VSOPParser.LiteralContext ctx) {
		if (ctx.INTEGER_LITERAL() != null) {
			return INT32;
		}

		if (ctx.STRING_LITERAL() != null) {
			return STRING;
		}

		if (ctx.booleanLiteral() != null) {
			return BOOL;
		}

		if (ctx.LPAR() != null) {
			return UNIT;
		}
		
		errorQueue.add(new SemanticError(ctx, "Unexpected error occured while trying to parse literal type"));
		return null;
	}

	public ASTType getType(VSOPParser.TypeContext ctx) {
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
		VSOPType exprType = visitExpr(ctx.expr());
		VSOPType varType = varStack.get(id);

		if (varType == null) {
			VSOPField f = currentClass.fields.get(id);
			if (f != null)
				varType = f.type;
		}

		if (varType == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Variable %s is not declared in this scope.", id)));

		if (varType != null && !exprType.canCast(varType))
			errorQueue.add(new SemanticError(ctx.expr(), "Expression type does not match variable type"));

		return (varType != null) ? varType : exprType;
	}

	public ASTNew visitNew(VSOPParser.NewContext ctx) {
		String id = ctx.id.getText();
		VSOPType type = classMap.get(id);
		if (type == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Invalid type indentifier %s", id)));

		return (type != null) ? type : OBJECT;
	}

	public ASTWhile visitWhile(VSOPParser.WhileContext ctx) {
		VSOPType condType = visitExpr(ctx.expr(0));
		visitExpr(ctx.expr(1));

		if (!condType.canCast(BOOL))
			errorQueue.add(
					new SemanticError(ctx.expr(0), String.format("Expected %s but got type %s", BOOL.id, condType.id)));

		return UNIT;
	}

	public ASTNot visitNot(VSOPParser.NotContext ctx) {
		VSOPType type = visitExpr(ctx.expr());

		if (type != BOOL) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"not operator can only be used with BOOL"));
		}

		return BOOL;
	}

	public ASTMinus visitMinus(VSOPParser.MinusContext ctx) {
		VSOPType type = visitExpr(ctx.expr());

		if (type != INT32) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"minus operator can only be used with INT32"));
		}

		return INT32;
	}

	public ASTIsnull visitIsnull(VSOPParser.IsnullContext ctx) {
		VSOPType type = visitExpr(ctx.expr());

		if (primitiveTypes.contains(type))
			errorQueue.add(new SemanticError(ctx, "isnull operator can only be used with non primitive types"));

		return BOOL;
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
		visitArgs(ctx.args());
		methodStack.pop();

		return (method != null) ? method.ret : UNIT;
	}

	public ASTCall visitCall(VSOPParser.CallContext ctx) {
		String id = ctx.id.getText();

		VSOPType exprType = visitExpr(ctx.expr());

		VSOPType retType = UNIT;
		if (exprType instanceof VSOPClass) {
			VSOPClass exprClass = (VSOPClass) exprType;
			VSOPMethod method = exprClass.functions.get(id);

			if (method == null) {
				errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
						String.format("method %s is undefined for type %s", id, exprClass.id)));
			}

			retType = (method != null) ? method.ret : UNIT;

			methodStack.push(method);
		} else {
			methodStack.push(null);
		}

		visitArgs(ctx.args());
		methodStack.pop();

		return retType;
	}

	public VSOPType visitUnit(VSOPParser.UnitContext ctx) {
		return UNIT;
	}

	public ASTLiteral visitLit(VSOPParser.LitContext ctx) {
		return visitLiteral(ctx.literal());
	}

	public ASTSelf visitSelf(VSOPParser.SelfContext ctx) {
		return currentClass;
	}

	public ASTLet visitLet(VSOPParser.LetContext ctx) {

		String id = ctx.id.getText();
		VSOPType varType = getType(ctx.type());
		varStack.push(id, varType);

		if (ctx.as != null) {
			VSOPType asType = visitExpr(ctx.as);

			if (!asType.canCast(varType)) {
				errorQueue.add(new SemanticError(ctx, String.format("Cannot convert %s to %s", asType.id, varType.id)));
			}
		}

		VSOPType inType = visitExpr(ctx.ex);

		varStack.pop(id);

		return inType;
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

		return (type != null) ? type : UNIT;
	}

	public ASTIf visitIf(VSOPParser.IfContext ctx) {

		VSOPType type0 = visitExpr(ctx.expr(0));

		if (type0 != BOOL) {
			errorQueue
					.add(new SemanticError(ctx, String.format("Condition must be of type BOOL but got %s", type0.id)));
		}

		VSOPType type1 = visitExpr(ctx.expr(1));
		VSOPType type2;

		if (ctx.expr(2) != null) {
			type2 = visitExpr(ctx.expr(2));
		} else {
			type2 = UNIT;
		}

		if (type1 instanceof VSOPClass && type2 instanceof VSOPClass) {
			return VSOPClass.commonAncestor((VSOPClass) type1, (VSOPClass) type2);
		} else if (type1 == UNIT || type2 == UNIT) {
			return UNIT;
		} else if (type1 == type2) {
			return type1;
		} else {
			errorQueue.add(new SemanticError(ctx, String.format("Type %s and %s does not match", type1.id, type2.id)));
			return UNIT;
		}
	}

	public ASTExpr visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		return visitExpr(ctx.expr());
	}

	public ASTBinop visitBinop(VSOPParser.BinopContext ctx) {
		String op = ctx.op.getText();
		VSOPType type1 = visitExpr(ctx.expr(0));
		VSOPType type2 = visitExpr(ctx.expr(1));

		VSOPBinOp binOp = binOpMap.get(op);

		if (binOp == null) {
			errorQueue.add(new SemanticError(ctx, "Unexpected error occur while trying to parse operator"));
			return UNIT;
		}

		if (binOp == EQ) {
			if (type1 instanceof VSOPClass && !(type2 instanceof VSOPClass)
					|| !(type1 instanceof VSOPClass) && type2 instanceof VSOPClass) {
				errorQueue.add(new SemanticError(ctx, "Trying to compare primitive type with class type"));
			} else if (!(type1 instanceof VSOPClass) && !(type2 instanceof VSOPClass) && type1 != type2) {
				errorQueue.add(new SemanticError(ctx, "Trying to compare primitive types that does not match"));
			}
		} else {
			if (type1 != binOp.opType) {
				errorQueue.add(new SemanticError(ctx.expr(0), String
						.format("Expected %s but got %s type for operator %s", binOp.opType.id, type1.id, binOp.id)));
			}
			if (type2 != binOp.opType) {
				errorQueue.add(new SemanticError(ctx.expr(1), String
						.format("Expected %s but got %s type for operator %s", binOp.opType.id, type2.id, binOp.id)));
			}
		}

		return binOp.retType;
	}
}
