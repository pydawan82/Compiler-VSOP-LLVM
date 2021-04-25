package compiler.vsop;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class SemanticError {
	private static final PrintStream DEFAULT_ERR = System.err;
	public static String fName = "";

	public final int ln, col;
	public final String msg;

	public SemanticError(int ln, int col, String msg) {
		this.ln = ln;
		this.col = col;
		this.msg = msg;
	}

	public SemanticError(Token token, String msg) {
		this(token.getLine(), token.getCharPositionInLine(), msg);
	}

	public SemanticError(ParserRuleContext ctx, String msg) {
		this(ctx.getStart(), msg);
	}

	public SemanticError(String msg) {
		this(0, -1, msg);
	}

	public void print(PrintStream out) {
		out.print(toString());
		out.println();
	}

	public void print() {
		print(DEFAULT_ERR);
	}

	@Override
	public String toString() {
		return String.format("%s:%d:%d: semantic error: %s", fName, ln, col + 1, msg);
	}
}
