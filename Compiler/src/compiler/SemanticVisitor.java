package compiler;

import java.io.PrintStream;
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

	private PrintStream out = System.out;
	private int tab = -1;
	private String tabs = "\t";

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

	private void printTab() {
		for (int i = 0; i < tab; i++)
			out.print(tabs);
	}

	public void flushErrorQueue() {
		while (!errorQueue.isEmpty())
			errorQueue.poll().print();

		errorQueue.forEach((error) -> error.print());
	}

	public Void visit(ParseTree arg0) {
		return null;
	}

	public Void visitChildren(RuleNode arg0) {
		return null;
	}

	public Void visitErrorNode(ErrorNode arg0) {
		return null;
	}

	public Void visitTerminal(TerminalNode arg0) {
		return null;
	}

	public Void visitProgram(VSOPParser.ProgramContext ctx) {
		tab++;

		List<VSOPParser.ClazzContext> clazzList = ctx.clazz();
		out.print('[');

		int i = 0;
		for (var clazz : clazzList) {
			i++;
			visitClazz(clazz);
			if (i != clazzList.size()) {
				out.println(',');
				printTab();
			}
		}

		out.println(']');

		tab--;

		return null;
	}

	public Void visitClazz(VSOPParser.ClazzContext ctx) {
		String id = ctx.id.getText();
		currentClass = classMap.get(id);
		String superId = ctx.idext != null ? ctx.idext.getText() : OBJECT.id;

		tab++;

		out.printf("Class(%s, %s, ", id, superId);

		visitClassBody(ctx.classBody());

		out.print(')');

		tab--;

		return null;
	}

	public Void visitClassBody(VSOPParser.ClassBodyContext ctx) {
		tab++;

		out.print('[');

		int i = 0;
		for (var field : ctx.field()) {
			i++;
			out.println();
			printTab();

			visitField(field);

			if (i != ctx.field().size()) {
				out.print(",");
			}
		}

		out.print("], ");

		if (ctx.field().size() != 0) {
			out.println();
			printTab();
		}

		out.print('[');

		i = 0;
		for (var method : ctx.method()) {
			i++;
			out.println();
			printTab();

			visitMethod(method);

			if (i != ctx.method().size()) {
				out.print(",");
			}
		}
		out.print(']');

		tab--;

		return null;
	}

	public Void visitField(VSOPParser.FieldContext ctx) {
		tab++;
		inFieldInit = true;

		String id = ctx.id.getText();

		out.printf("Field(%s, ", id);
		visitType(ctx.type());

		if (ctx.expr() != null) {
			out.print(", ");
			VSOPType fType = getType(ctx.type());
			VSOPType exprType = visitExpr(ctx.expr());
			if (!exprType.canCast(fType))
				errorQueue.add(
						new SemanticError(ctx.expr(), String.format("cannot cast %s to %s", exprType.id, fType.id)));
		}

		out.print(')');
		tab--;

		inFieldInit = false;
		return null;
	}

	public Void visitMethod(VSOPParser.MethodContext ctx) {

		String id = ctx.id.getText();

		tab++;

		out.printf("Method(%s, ", id);

		visitFormals(ctx.formals());

		out.print(", ");

		visitType(ctx.type());
		out.print(", ");

		VSOPMethod method = currentClass.functions.get(id);
		for (var field : method.args)
			varStack.push(field.id, field.type);

		VSOPType ret = visitBlock(ctx.block());
		out.printf(":%s", ret.id);

		for (var field : method.args)
			varStack.pop(field.id);

		out.print(')');

		tab--;

		if (!ret.canCast(method.ret))
			errorQueue.add(new SemanticError(ctx.block(),
					String.format("Method should return %s but got %s instead", method.ret.id, ret.id)));

		return null;
	}

	public Void visitFormals(VSOPParser.FormalsContext ctx) {
		out.print('[');

		int i = 0;
		for (var formal : ctx.formal()) {
			i++;

			out.println();
			printTab();

			visitFormal(formal);

			if (i != ctx.formal().size()) {
				out.print(",");
			}
		}

		if (ctx.formal().size() != 0) {
			out.println();
			printTab();
		}

		out.print(']');

		return null;
	}

	public Void visitFormal(VSOPParser.FormalContext ctx) {
		out.print(ctx.id.getText() + " : ");
		visitType(ctx.type());
		return null;
	}

	public VSOPType visitBlock(VSOPParser.BlockContext ctx) {

		tab++;

		out.print('[');
		VSOPType type = UNIT;

		int i = 0;
		for (var expr : ctx.expr()) {
			i++;

			out.println();
			printTab();

			type = visitExpr(expr);

			if (i != ctx.expr().size()) {
				out.print(",");
			}
		}

		if (ctx.expr().size() != 0) {
			out.println();
			printTab();
		}

		out.print(']');
		out.printf(":%s", ctx.expr(ctx.expr().size()-1));
		tab--;

		return type;
	}

	public VSOPType visitExpr(VSOPParser.ExprContext ctx) {
		VSOPType type = exprDispatcher.get(ctx.getClass()).apply(ctx);

		if (ctx.getClass() != VSOPParser.BraceExprContext.class)
			out.printf(":%s", (type != null) ? type.id : "null");
		return type;
	}

	public Void visitArgs(VSOPParser.ArgsContext ctx) {
		tab++;

		out.print("[");

		VSOPMethod method = methodStack.peek();

		if (method != null && method.args.size() != ctx.expr().size()) {
			errorQueue.add(new SemanticError(ctx, String.format("method %s expected %d args but got %d", method.id,
					method.args.size(), ctx.expr().size())));
		}

		int i = 0;
		for (var expr : ctx.expr()) {

			out.println();
			printTab();

			VSOPType argType = visitExpr(expr);
			if (method != null && i < method.args.size()) {
				if (!argType.canCast(method.args.get(i).type)) {
					errorQueue.add(new SemanticError(ctx.expr(i),
							String.format("cannot cast from %s to %s", argType.id, method.args.get(i).type.id)));
				}
			}

			i++;

			if (i != ctx.expr().size()) {
				out.print(",");
			}
		}

		if (ctx.expr().size() != 0) {
			out.println();
			printTab();
		}

		out.print("]");

		tab--;

		return null;
	}

	public VSOPType visitLiteral(VSOPParser.LiteralContext ctx) {
		out.print(ctx.getText());

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

	public Void visitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx) {
		System.err.println("UNEXPECTED BRANCH!");

		return null;
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

	public Void visitType(VSOPParser.TypeContext ctx) {
		out.print(ctx.getText());
		return null;
	}

	public VSOPType visitAss(VSOPParser.AssContext ctx) {
		String id = ctx.id.getText();

		out.printf("Assign(%s, ", id);
		VSOPType exprType = visitExpr(ctx.expr());
		out.print(")");

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

	public VSOPType visitNew(VSOPParser.NewContext ctx) {
		String id = ctx.id.getText();

		out.printf("New(%s)", id);

		VSOPType type = classMap.get(id);
		if (type == null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Invalid type indentifier %s", id)));

		return (type != null) ? type : OBJECT;
	}

	public VSOPType visitBl(VSOPParser.BlContext ctx) {
		return visitBlock(ctx.block());
	}

	public VSOPType visitWhile(VSOPParser.WhileContext ctx) {
		out.print("While(");
		VSOPType condType = visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));
		out.print(")");

		if (!condType.canCast(BOOL))
			errorQueue.add(
					new SemanticError(ctx.expr(0), String.format("Expected %s but got type %s", BOOL.id, condType.id)));

		return UNIT;
	}

	public VSOPType visitNot(VSOPParser.NotContext ctx) {
		out.print("UnOp(not, ");
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");

		if (type != BOOL) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"not operator can only be used with BOOL"));
		}

		return BOOL;
	}

	public VSOPType visitMinus(VSOPParser.MinusContext ctx) {
		out.print("UnOp(-, ");
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");

		if (type != INT32) {
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					"minus operator can only be used with INT32"));
		}

		return INT32;
	}

	public VSOPType visitIsnull(VSOPParser.IsnullContext ctx) {
		out.print("UnOp(isnull, ");
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");

		if (primitiveTypes.contains(type))
			errorQueue.add(new SemanticError(ctx, "isnull operator can only be used with non primitive types"));

		return BOOL;
	}

	public VSOPType visitSelfcall(VSOPParser.SelfcallContext ctx) {
		String id = ctx.id.getText();

		VSOPMethod method = currentClass.functions.get(id);
		if (inFieldInit) {
			errorQueue.add(new SemanticError(ctx, "illegal call to self in field initializer"));
		} else if (method == null) {
			errorQueue.add(
					new SemanticError(ctx, String.format("method %s is undefined for type %s", id, currentClass.id)));
		}

		out.printf("Call(self: %s, %s, ", currentClass.id, ctx.id.getText());

		methodStack.push(method);
		visitArgs(ctx.args());
		methodStack.pop();

		out.print(')');

		return (method != null) ? method.ret : UNIT;
	}

	public VSOPType visitCall(VSOPParser.CallContext ctx) {

		String id = ctx.id.getText();

		out.print("Call(");

		VSOPType exprType = visitExpr(ctx.expr());

		out.print(", " + ctx.id.getText() + ", ");

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

		out.print(')');

		return retType;
	}

	public VSOPType visitUnit(VSOPParser.UnitContext ctx) {
		out.print("()");
		return UNIT;
	}

	public VSOPType visitLit(VSOPParser.LitContext ctx) {
		return visitLiteral(ctx.literal());
	}

	public VSOPType visitSelf(VSOPParser.SelfContext ctx) {
		out.print("self");
		return currentClass;
	}

	public VSOPType visitLet(VSOPParser.LetContext ctx) {

		String id = ctx.id.getText();
		VSOPType varType = getType(ctx.type());
		varStack.push(id, varType);

		out.printf("Let(%s, ", id);
		visitType(ctx.type());
		out.print(", ");

		if (ctx.as != null) {
			VSOPType asType = visitExpr(ctx.as);
			out.print(", ");

			if (!asType.canCast(varType)) {
				errorQueue.add(new SemanticError(ctx, String.format("Cannot convert %s to %s", asType.id, varType.id)));
			}
		}

		VSOPType inType = visitExpr(ctx.ex);
		out.print(")");

		varStack.pop(id);

		return inType;
	}

	public VSOPType visitOi(VSOPParser.OiContext ctx) {
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

		out.print(id);

		return (type != null) ? type : UNIT;
	}

	public VSOPType visitIf(VSOPParser.IfContext ctx) {
		out.print("If(");

		VSOPType type0 = visitExpr(ctx.expr(0));

		if (type0 != BOOL) {
			errorQueue
					.add(new SemanticError(ctx, String.format("Condition must be of type BOOL but got %s", type0.id)));
		}

		out.print(", ");

		VSOPType type1 = visitExpr(ctx.expr(1));
		VSOPType type2;

		if (ctx.expr(2) != null) {
			out.print(", ");
			type2 = visitExpr(ctx.expr(2));
		} else {
			type2 = UNIT;
		}

		out.print(")");

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

	public VSOPType visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		return visitExpr(ctx.expr());
	}

	public VSOPType visitBinop(VSOPParser.BinopContext ctx) {
		String op = ctx.op.getText();

		out.printf("BinOp(%s, ", op);
		VSOPType type1 = visitExpr(ctx.expr(0));
		out.print(", ");
		VSOPType type2 = visitExpr(ctx.expr(1));
		out.print(")");

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
