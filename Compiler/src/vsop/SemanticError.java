package vsop;

import org.antlr.v4.runtime.ParserRuleContext;

public class SemanticError {
	public static String fName = "";

	public final int ln, col;
	public final String msg;

	public SemanticError(int ln, int col, String msg) {
		this.ln = ln;
		this.col = col;
		this.msg = msg;
	}

	public SemanticError(ParserRuleContext ctx, String msg) {
		this(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), msg);
	}

	public void printError() {
		System.err.print(toString());
		System.err.println();
	}

	@Override
	public String toString() {
		return String.format("%s:%d:%d: semantic error: %s", fName, ln, col + 1, msg);
	}
}
