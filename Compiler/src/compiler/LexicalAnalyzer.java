package compiler;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import compiler.error.LexicalError;
import compiler.parsing.VSOPLexer;

/**
 * A class that can perform lexical analysis given a {@link VSOPLexer}.
 */
public class LexicalAnalyzer {
    private static final Set<String> textTypes = Set.of(
        "INTEGER_LITERAL",
        "STRING_LITERAL",
        "OBJECT_IDENTIFIER",
        "TYPE_IDENTIFIER");
        
    private final VSOPLexer lexer;

    private PrintStream out = System.out;
    private PrintStream err = System.err;

    private final Queue<LexicalError> errorQueue = new LinkedList<>();

	/**
	 * Creates a new {@link LexicalAnalyzer} that can perform lexical analysis of 
	 * the given {@link VSOPLexer}.
	 * @param lexer - The lexer
	 */
    public LexicalAnalyzer(VSOPLexer lexer) {
        this.lexer = Objects.requireNonNull(lexer);

        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> r, Object o, int ln, int col, String msg, RecognitionException e) {
				errorQueue.add(new LexicalError(ln, col+1, msg));
			}
		});
    }

	/**
	 * Sets the {@link PrintStream} where tokens are printed.
	 * @param out - The new output
	 */
    public void setOutputStream(PrintStream out) {
        this.out = out;
    }

	/**
	 * Sets the {@link PrintStream} where errors are printed.
	 * @param err - The new error output.
	 */
    public void setErrorStream(PrintStream err) {
        this.err = err;
    }

	/**
	 * Performs the lexical analysis.
	 * @return <code>true</code> if the analysis terminated withoud error,
	 * <code>false</code> otherwise.
	 */
    public boolean lex() {
		Vocabulary voc = lexer.getVocabulary();

		while (true) {
			Token t = lexer.nextToken();
			if (t.getType() == -1)
				break;

			String txt = null;
			if (textTypes.contains(voc.getSymbolicName(t.getType())))
				txt = t.getText();

			printToken(t.getLine(), t.getCharPositionInLine() + 1, toLowerCase(voc.getSymbolicName(t.getType())), txt);
		}

		return flushErrorQueue();
    }

    /**
     * Prints all the errors on the error stream.
     * @return - <code>true</code> if the error queue was empty, <code>false</code> otherwise.
     */
    private boolean flushErrorQueue() {
        boolean success = errorQueue.isEmpty();
        errorQueue.forEach(err::print);
        errorQueue.clear();

        return success;
    }

    /**
	 * Returns the lowercase version of the token name in order to respect naming
	 * convetions.
	 * 
	 * @param tokenName - The name of the token
	 * @return The lower caser version of the token
	 */
	private static String toLowerCase(String tokenName) {
		String lower = tokenName.toLowerCase();
		return lower.replace('_', '-');
	}

    /**
	 * Prints the token on System.out
	 * 
	 * @param ln   - The line of the first char of the token
	 * @param cl   - The column of the first char of the token
	 * @param type - The name of the type of the token
	 * @param text - The text of the token or null if nothing should not be printed
	 */
	private void printToken(int ln, int cl, String type, String text) {
		out.printf("%d,%d,%s", ln, cl, type);
		if (text != null)
			out.printf(",%s", text);
		out.println();
	}
}
