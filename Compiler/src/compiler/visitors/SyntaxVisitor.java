package compiler.visitors;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import compiler.parsing.VSOPParser.*;

/**
 * A class that prints the parse tree of a given context.
 */
public class SyntaxVisitor {

	private static final String TAB = "\t";

	private final PrintStream out;
	private final Map<Class<?>, Consumer<ExprContext>> map = new HashMap<>();
	private final String defaultClazz = "Object";

	private int tab = -1;

	public SyntaxVisitor() {
		this(System.out);
	}

	public SyntaxVisitor(PrintStream out) {
		this.out = Objects.requireNonNull(out);

		map.put(IfContext.class, (ExprContext c) -> visitIf((IfContext) c));
		map.put(WhileContext.class, (ExprContext c) -> visitWhile((WhileContext) c));
		map.put(LetContext.class, (ExprContext c) -> visitLet((LetContext) c));
		map.put(AssContext.class, (ExprContext c) -> visitAss((AssContext) c));
		map.put(NotContext.class, (ExprContext c) -> visitNot((NotContext) c));
		map.put(BinopContext.class, (ExprContext c) -> visitBinop((BinopContext) c));
		map.put(MinusContext.class, (ExprContext c) -> visitMinus((MinusContext) c));
		map.put(IsnullContext.class, (ExprContext c) -> visitIsnull((IsnullContext) c));
		map.put(SelfcallContext.class, (ExprContext c) -> visitSelfcall((SelfcallContext) c));
		map.put(CallContext.class, (ExprContext c) -> visitCall((CallContext) c));
		map.put(NewContext.class, (ExprContext c) -> visitNew((NewContext) c));
		map.put(OiContext.class, (ExprContext c) -> visitOi((OiContext) c));
		map.put(LitContext.class, (ExprContext c) -> visitLit((LitContext) c));
		map.put(SelfContext.class, (ExprContext c) -> visitSelf((SelfContext) c));
		map.put(UnitContext.class, (ExprContext c) -> visitUnit((UnitContext) c));
		map.put(BraceExprContext.class,
				(ExprContext c) -> visitBraceExpr((BraceExprContext) c));
		map.put(BlContext.class, (ExprContext c) -> visitBl((BlContext) c));
	}

	/**
	 * Print tabulation in order to make the output a bit more readable.
	 */
	private void printTab() {
		for (int i = 0; i < tab; i++) {
			out.print(TAB);
		}
	}


	/**
	 * Print out the AST for the -c options of the compiler using the visitor methodology.
	 * @param ctx - the program context.
	 */
	public void visitProgram(ProgramContext ctx) {
		tab++;

		List<ClazzContext> clazzList = ctx.clazz();
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
	}

	public void visitClazz(ClazzContext ctx) {
		tab++;
		
		out.printf("Class(%s, %s, ", ctx.id.getText(), ctx.idext != null ? ctx.idext.getText() : defaultClazz);

		visitClassBody(ctx.classBody());

		out.print(')');

		tab--;
	}

	public void visitClassBody(ClassBodyContext ctx) {
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
	}

	public void visitField(FieldContext ctx) {
		tab++;

		out.printf("Field(%s, %s", ctx.id.getText(), ctx.type().getText());

		if (ctx.expr() != null) {
			out.print(", ");
			visitExpr(ctx.expr());
		}

		out.print(')');
		tab--;
	}

	public void visitMethod(MethodContext ctx) {
		tab++;

		out.printf("Method(%s, ", ctx.id.getText());

		visitFormals(ctx.formals());

		out.print(", ");

		visitType(ctx.type());
		out.print(", ");

		visitBlock(ctx.block());

		out.print(')');

		tab--;
	}

	public void visitFormals(FormalsContext ctx) {
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
	}

	public void visitFormal(FormalContext ctx) {
		out.print(ctx.id.getText() + " : ");
		visitType(ctx.type());
	}

	public void visitBlock(BlockContext ctx) {
		tab++;

		out.print('[');

		int i = 0;
		for (var expr : ctx.expr()) {
			i++;

			out.println();
			printTab();

			visitExpr(expr);

			if (i != ctx.expr().size()) {
				out.print(",");
			}
		}

		if (ctx.expr().size() != 0) {
			out.println();
			printTab();
		}

		out.print(']');

		tab--;
	}

	public void visitExpr(ExprContext ctx) {
		map.get(ctx.getClass()).accept(ctx);
	}

	public void visitArgs(ArgsContext ctx) {
		tab++;

		out.print("[");

		int i = 0;
		for (var expr : ctx.expr()) {
			i++;

			out.println();
			printTab();

			visitExpr(expr);

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
	}

	public void visitLiteral(LiteralContext ctx) {
		out.print(ctx.getText());
	}

	public void visitBooleanLiteral(BooleanLiteralContext ctx) {
		out.print(ctx.getText());
	}

	public void visitType(TypeContext ctx) {
		out.print(ctx.getText());
	}

	public void visitAss(AssContext ctx) {
		out.print("Assign(" + ctx.id.getText() + ", ");
		visitExpr(ctx.expr());
		out.print(")");
	}

	public void visitNew(NewContext ctx) {
		out.print("New(" + ctx.id.getText() + ")");
	}

	public void visitBl(BlContext ctx) {
		visitBlock(ctx.block());
	}

	public void visitWhile(WhileContext ctx) {
		out.print("While(");
		visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));
		out.print(")");
	}

	public void visitNot(NotContext ctx) {
		out.print("UnOp(not, ");
		visitExpr(ctx.expr());
		out.print(")");
	}

	public void visitMinus(MinusContext ctx) {
		out.print("UnOp(-, ");
		visitExpr(ctx.expr());
		out.print(")");
	}

	public void visitIsnull(IsnullContext ctx) {
		out.print("UnOp(isnull, ");
		visitExpr(ctx.expr());
		out.print(")");
	}

	public void visitSelfcall(SelfcallContext ctx) {
		out.print("Call(self, " + ctx.id.getText() + ", ");

		visitArgs(ctx.args());

		out.print(')');
	}

	public void visitCall(CallContext ctx) {
		out.print("Call(");

		visitExpr(ctx.expr());

		out.print(", " + ctx.id.getText() + ", ");

		visitArgs(ctx.args());

		out.print(')');
	}

	public void visitUnit(UnitContext ctx) {
		out.print("()");
	}

	public void visitLit(LitContext ctx) {
		visitLiteral(ctx.literal());
	}

	public void visitSelf(SelfContext ctx) {
		out.print("self");
	}

	public void visitLet(LetContext ctx) {
		out.print("Let(" + ctx.id.getText() + ", ");
		visitType(ctx.type());
		out.print(", ");

		if (ctx.as != null) {
			visitExpr(ctx.as);
			out.print(", ");
		}
		visitExpr(ctx.ex);
		out.print(")");
	}

	public void visitOi(OiContext ctx) {
		out.print(ctx.id.getText());
	}

	public void visitIf(IfContext ctx) {
		out.print("If(");
		visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));

		if (ctx.expr(2) != null) {
			out.print(", ");
			visitExpr(ctx.expr(2));
		}

		out.print(")");
	}

	public void visitBraceExpr(BraceExprContext ctx) {
		visitExpr(ctx.expr());
	}

	public void visitBinop(BinopContext ctx) {
		out.print("BinOp(" + ctx.op.getText() + ", ");
		visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));
		out.print(")");
	}
}
