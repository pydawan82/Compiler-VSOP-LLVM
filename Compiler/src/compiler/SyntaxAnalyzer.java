package compiler;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import compiler.error.SyntaxError;
import compiler.parsing.VSOPParser;
import compiler.visitors.SyntaxVisitor;

/**
 * A class used to perform syntax analysis of a VSOP source file. 
 */
public class SyntaxAnalyzer {
    
    private final VSOPParser parser;

    private PrintStream out = System.out;
    private PrintStream err = System.err;

    private final Queue<SyntaxError> errorQueue = new LinkedList<>();

    /**
     * Creates a new {@link SyntaxAnalyzer} given a {@link VSOPParser}
     * @param parser - the parser
     */
    public SyntaxAnalyzer(VSOPParser parser) {
        this.parser = Objects.requireNonNull(parser);

        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> r, Object o, int ln, int col, String msg, RecognitionException e) {
                errorQueue.add(new SyntaxError(ln, col, msg));
			}
		});
    }

    /**
     * Sets the {@link PrintStream} where the syntax tree is printed.
     * @param out - the new output
     */
    public void setOutputStream(PrintStream out) {
        this.out = out;
    }

    /**
     * Sets the {@link PrintStream} where syntax errors are printed.
     * @param err - the new error output
     */
    public void setErrorStream(PrintStream err) {
        this.err = err;
    }

    /**
     * Performs parsing
     * @return <code>true</code> if the parsing terminated without error,
     * <code>false</code> otherwise.
     */
    public boolean parse() {
		SyntaxVisitor visitor = new SyntaxVisitor(out);
        visitor.visitProgram(parser.program());

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
}
