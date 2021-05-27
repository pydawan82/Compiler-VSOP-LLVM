package compiler.visitors;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.parsing.VSOPParser.*;
import compiler.vsop.VSOPConstants;

import static compiler.util.PrintUtil.*;

/**
 * A class that prints the parse tree of a given context.
 */
public class SyntaxVisitor {

	private static final String TAB = " ";

	private static final Map<Class<?>, BiConsumer<PrintStream, ParserRuleContext>> dispatcher;

	private static int tab = 0;

	static {

		dispatcher = Map.ofEntries(
			Map.entry(ProgramContext.class, (out, ctx) -> print(out, (ProgramContext) ctx)),
			Map.entry(ClazzContext.class, (out, ctx) -> print(out, (ClazzContext) ctx)),
			Map.entry(ClassBodyContext.class, (out, ctx) -> print(out, (ClassBodyContext)ctx)),
			Map.entry(FieldContext.class, (out, ctx) -> print(out, (FieldContext) ctx)),
			Map.entry(MethodContext.class, (out, ctx) -> print(out, (MethodContext) ctx)),
			Map.entry(FormalsContext.class, (out, ctx) -> print(out, (FormalsContext) ctx)),
			Map.entry(FormalContext.class, (out, ctx) -> print(out, (FormalContext) ctx)),
			Map.entry(BlockContext.class, (out, ctx) -> print(out, (BlockContext) ctx)),
			Map.entry(IfContext.class, (out, ctx) -> print(out, (IfContext) ctx)),
			Map.entry(WhileContext.class, (out, ctx) -> print(out, (WhileContext) ctx)),
			Map.entry(LetContext.class, (out, ctx) -> print(out, (LetContext) ctx)),
			Map.entry(AssContext.class, (out, ctx) -> print(out, (AssContext) ctx)),
			Map.entry(NotContext.class, (out, ctx) -> print(out, (NotContext) ctx)),
			Map.entry(BinopContext.class, (out, ctx) -> print(out, (BinopContext) ctx)),
			Map.entry(MinusContext.class, (out, ctx) -> print(out, (MinusContext) ctx)),
			Map.entry(IsnullContext.class, (out, ctx) -> print(out, (IsnullContext) ctx)),
			Map.entry(SelfcallContext.class, (out, ctx) -> print(out, (SelfcallContext) ctx)),
			Map.entry(CallContext.class, (out, ctx) -> print(out, (CallContext) ctx)),
			Map.entry(NewContext.class, (out, ctx) -> print(out, (NewContext) ctx)),
			Map.entry(OiContext.class, (out, ctx) -> print(out, (OiContext) ctx)),
			Map.entry(LitContext.class, (out, ctx) -> print(out, (LitContext) ctx)),
			Map.entry(SelfContext.class, (out, ctx) -> print(out, (SelfContext) ctx)),
			Map.entry(UnitContext.class, (out, ctx) -> print(out, (UnitContext) ctx)),
			Map.entry(BraceExprContext.class, (out, ctx) -> print(out, (BraceExprContext) ctx)),
			Map.entry(BlContext.class, (out, ctx) -> print(out, (BlContext) ctx))
		);
	}

	private static String listFormat = "[#n%s#p#n%s]";
	private static String emptyList = "[]";
	private static String listSep = ","+System.lineSeparator();

	private static <Context extends ParserRuleContext> void printList(PrintStream out, List<Context> contexts) {
		List<Consumer<PrintStream>> printers = contexts.stream()
				.map(c -> (Consumer<PrintStream>) (stream -> dispatch(stream, c)))
				.toList();


		if(printers.size() == 0) {
			out.print(emptyList);
		} else {
			String format = listFormat.formatted(TAB.repeat(tab), TAB.repeat(tab));
			pformat(out, format, (stream) -> join(stream, listSep+TAB.repeat(tab), printers));
		}
	}

	private static void dispatch(PrintStream out, ParserRuleContext ctx) {
		dispatcher.get(ctx.getClass()).accept(out, ctx);
	}


	/**
	 * Print out the AST for the -c options of the compiler using the visitor methodology.
	 * @param ctx - the program context.
	 */
	public static void print(PrintStream out, ProgramContext ctx) {
		tab++;

		printList(out, ctx.clazz());

		tab--;
	}

	public static void print(PrintStream out, ClazzContext ctx) {
		tab++;

		String id = ctx.id.getText();
		String superId = ctx.idext != null ? ctx.idext.getText() : VSOPConstants.OBJECT.id;
		String format = "Class(%s, %s, #p)".formatted(id, superId);

		pformat(out, format, (stream) -> print(stream, ctx.classBody()));

		tab--;
	}

	public static void print(PrintStream out, ClassBodyContext ctx) {
		tab++;

		String format = "#p, #p";

		pformat(out, format,
			stream -> printList(stream, ctx.field()),
			stream -> printList(stream, ctx.method())
		);

		tab--;
	}

	public static void print(PrintStream out, FieldContext ctx) {
		tab++;

		String id = ctx.id.getText();

		if(ctx.expr() == null) {
			String format  = "Field(%s, #p)".formatted(id);

			pformat(out, format, stream -> print(stream, ctx.type()));
		} else {
			String format  = "Field(%s, #p, #p)".formatted(id);

			pformat(out, format,
				stream -> print(stream, ctx.type()),
				stream -> dispatchExpr(stream, ctx.expr())
			);
		}
		tab--;
	}

	public static void print(PrintStream out, MethodContext ctx) {
		tab++;

		String id = ctx.id.getText();
		String format = "Method(%s, #p, #p, #p)".formatted(id);

		pformat(out, format,
				stream -> print(out, ctx.formals()),
				stream -> print(out, ctx.type()),
				stream -> print(out, ctx.block())
			);

		tab--;
	}

	public static void print(PrintStream out, FormalsContext ctx) {
		printList(out, ctx.formal());
	}

	public static void print(PrintStream out, FormalContext ctx) {
		String id = ctx.id.getText();
		String format = "%s: #p".formatted(id);

		pformat(out, format, stream -> print(stream, ctx.type()));
	}

	public static void print(PrintStream out, BlockContext ctx) {
		tab++;

		printList(out, ctx.expr());

		tab--;
	}

	public static void dispatchExpr(PrintStream out, ExprContext ctx) {
		dispatch(out, ctx);
	}

	public static void print(PrintStream out, ArgsContext ctx) {
		tab++;

		printList(out, ctx.expr());

		tab--;
	}

	public static void print(PrintStream out, LiteralContext ctx) {
		out.print(ctx.getText());
	}

	public static void print(PrintStream out, BooleanLiteralContext ctx) {
		out.print(ctx.getText());
	}

	public static void print(PrintStream out, TypeContext ctx) {
		out.print(ctx.getText());
	}

	public static void print(PrintStream out, AssContext ctx) {
		String id = ctx.id.getText();
		String format = "Assign(%s, #p)".formatted(id);
		pformat(out, format, stream -> dispatchExpr(stream, ctx.expr())); 
	}

	public static void print(PrintStream out, NewContext ctx) {
		String id = ctx.id.getText();
		out.printf("New(%s)", id);
	}

	public static void print(PrintStream out, BlContext ctx) {
		print(out, ctx.block());
	}

	public static void print(PrintStream out, WhileContext ctx) {
		String format = "While(#p, #p)";
		
		pformat(out, format, 
			stream -> dispatchExpr(stream, ctx.expr(0)),
			stream -> dispatchExpr(stream, ctx.expr(1))
		);
	}

	public static void print(PrintStream out, NotContext ctx) {
		String format = "UnOp(not, #p)";
		
		pformat(out, format, stream -> dispatchExpr(out, ctx.expr()));
	}

	public static void print(PrintStream out, MinusContext ctx) {
		String format = "UnOp(-, #p)";
		
		pformat(out, format, stream -> dispatchExpr(out, ctx.expr()));
	}

	public static void print(PrintStream out, IsnullContext ctx) {
		String format = "UnOp(isnull, #p)";
		
		pformat(out, format, stream -> dispatchExpr(out, ctx.expr()));
	}

	public static void print(PrintStream out, SelfcallContext ctx) {
		String id = ctx.id.getText();
		String  format = "Call(self, %s, #p)".formatted(id);
		
		pformat(out, format, stream -> print(stream, ctx.args()));
	}

	public static void print(PrintStream out, CallContext ctx) {
		String id = ctx.id.getText();
		String  format = "Call(#p, %s, #p)".formatted(id);
		
		pformat(out, format,
			stream -> dispatchExpr(stream, ctx.expr()),
			stream -> print(stream, ctx.args())
		);
	}

	public static void print(PrintStream out, UnitContext ctx) {
		out.print("()");
	}

	public static void print(PrintStream out, LitContext ctx) {
		print(out, ctx.literal());
	}

	public static void print(PrintStream out, SelfContext ctx) {
		out.print("self");
	}

	public static void print(PrintStream out, LetContext ctx) {
		String id = ctx.id.getText();

		if(ctx.as == null) {
			String format = "Let(%s, #p, #p)".formatted(id);
			pformat(out, format,
				stream -> print(stream, ctx.type()),
				stream -> dispatchExpr(stream, ctx.ex)
			);
		} else {
			String format = "Let(%s, #p, #p, #p)".formatted(id);
			pformat(out, format,
				stream -> print(stream, ctx.type()),
				stream -> dispatchExpr(stream, ctx.as),
				stream -> dispatchExpr(stream, ctx.ex)
			);
		}
	}

	public static void print(PrintStream out, OiContext ctx) {
		out.print(ctx.id.getText());
	}

	public static void print(PrintStream out, IfContext ctx) {

		if(ctx.expr(2) == null) {
			String format = "If(#p, #p)";
			pformat(out, format,
				stream -> dispatchExpr(stream, ctx.expr(0)),
				stream -> dispatchExpr(stream, ctx.expr(1))
			);
		} else {
			String format = "If(#p, #p, #p)";
			pformat(out, format,
				stream -> dispatchExpr(stream, ctx.expr(0)),
				stream -> dispatchExpr(stream, ctx.expr(1)),
				stream -> dispatchExpr(stream, ctx.expr(2))
			);
		}
	}

	public static void print(PrintStream out, BraceExprContext ctx) {
		dispatchExpr(out, ctx.expr());
	}

	public static void print(PrintStream out, BinopContext ctx) {
		String op = ctx.op.getText();
		String format = "BinOp(%s, #p, #p)".formatted(op);
		
		pformat(out, format, 
			stream -> dispatchExpr(out, ctx.expr(0)),
			stream -> dispatchExpr(out, ctx.expr(1))
		);
	}
}
