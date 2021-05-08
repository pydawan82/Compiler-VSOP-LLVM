package compiler;

import java.io.IOException;

import compiler.error.LexicalError;
import compiler.error.SemanticError;
import compiler.error.SyntaxError;
import compiler.parsing.VSOPLexer;
import compiler.parsing.VSOPParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * A VSOP to LLVM compiler
 */
public class Compiler {
	private CharStream input;

	/**
	 * Creates a new Compiler that will comile the given file.
	 * 
	 * @param fileName - The name of the file
	 * @throws IOException if an I/O error occurs.
	 */
	public Compiler(String fileName) throws IOException {
		input = CharStreams.fromFileName(fileName);

		LexicalError.FILE_NAME = fileName;
		SyntaxError.FILE_NAME = fileName;
		SemanticError.FILE_NAME = fileName;
	}

	/**
	 * Lex the file given to the Compiler, Outputs tokens on System.out and the
	 * lexical errors on System.err
	 * 
	 * @return <code>true</code> if the lexing terminated withou any error,
	 * <code>false</code> otherwise.
	 */
	public boolean lex() {
		VSOPLexer lexer = new VSOPLexer(input);
		return new LexicalAnalyzer(lexer).lex();
	}

	/**
	 * Proceed to the syntax analysis and previous phases of compiling.
	 * The file parsed is the one given to this {@link Compiler}.
	 * @return <code>true</code> if parsing terminated without any error,
	 * <code>false</code> otherwise.
	 */
	public boolean parse() {
		VSOPLexer lexer = new VSOPLexer(input);
		VSOPParser parser = new VSOPParser(new CommonTokenStream(lexer));
		return new SyntaxAnalyzer(parser).parse();
	}

	/**
	 * Proceed to the semantic checking and previous phases of compiling.
	 * The file parsed is the one given to this {@link Compiler}
	 * @return
	 */
	public boolean check() {
		VSOPLexer lexer = new VSOPLexer(input);
		VSOPParser parser = new VSOPParser(new CommonTokenStream(lexer));
		return new SemanticChecker(parser).check();
	}

	public static void main(String[] args) throws IOException {

		args = "-c Compiler/vsop-examples/main.vsop".split(" ");

		if (args.length != 2) {
			System.err.println("Usage: vsopc [-l|-p|-c] *input_file*");
			System.exit(-1);
			return;
		}

		String fileName = args[1];
		Compiler c = new Compiler(fileName);

		boolean success = switch (args[0]) {
			case "-l" -> c.lex();
			case "-p" -> c.parse();
			case "-c" -> c.check();
			default -> false;
		};

		System.out.println("Sucess:" + success);
		System.exit(success ? 0 : -1);
	}
}
