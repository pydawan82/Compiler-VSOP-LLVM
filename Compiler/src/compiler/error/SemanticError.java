package compiler.error;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public record SemanticError(int ln, int col, String message) {
    public static String FILE_NAME = "undefined";

	public SemanticError {

	}

	public SemanticError(Token token, String message) {
		this(token.getLine(), token.getCharPositionInLine(), message);
	}

	public SemanticError(ParserRuleContext ctx, String message) {
		this(ctx.getStart(), message);
	}

	public SemanticError(String msg) {
		this(0, -1, msg);
	}

	public void print(PrintStream out) {
		out.print(toString());
		out.println();
	}

	@Override
	public String toString() {
		return String.format("%s:%d:%d: semantic error: %s", FILE_NAME, ln, col + 1, message);
	}
}
