package compiler;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

import compiler.error.LexicalError;
import compiler.error.SemanticError;
import compiler.error.SyntaxError;
import compiler.llvm.Generator;
import compiler.parsing.VSOPLexer;
import compiler.parsing.VSOPParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * A VSOP to LLVM compiler
 */
public class Compiler {

	private static int SUCCESS = 0;
	private static int FAIL = -1;
	private static int IO_ERROR = -2;

	private String fileName;
	private PrintStream out;

	/**
	 * Creates a new Compiler that will comile the given file.
	 * 
	 * @param fileName - The name of the file
	 * @throws IOException if an I/O error occurs.
	 */
	public Compiler(String fileName, PrintStream out) throws IOException {
		this.fileName = fileName;
		this.out = Objects.requireNonNull(out);

		LexicalError.FILE_NAME = fileName;
		SyntaxError.FILE_NAME = fileName;
		SemanticError.FILE_NAME = fileName;
	}

	public CharStream input() {
		try {
			return CharStreams.fromFileName(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(IO_ERROR);
			return null;
		}
	}

	public VSOPLexer lexer() {
		return new VSOPLexer(input());
	}

	public VSOPParser parser() {
		return new VSOPParser(new CommonTokenStream(lexer()));
	}

	/**
	 * Lex the file given to the Compiler, Outputs tokens on System.out and the
	 * lexical errors on System.err
	 * 
	 * @return <code>true</code> if the lexing terminated withou any error,
	 * <code>false</code> otherwise.
	 */
	public boolean lex() {
		return new LexicalAnalyzer(lexer()).lex();
	}

	/**
	 * Proceed to the syntax analysis and previous phases of compiling.
	 * The file parsed is the one given to this {@link Compiler}.
	 * @return <code>true</code> if parsing terminated without any error,
	 * <code>false</code> otherwise.
	 */
	public boolean parse() {
		return new SyntaxAnalyzer(parser()).parse();
	}

	/**
	 * Proceed to the semantic checking and previous phases of compiling.
	 * The file parsed is the one given to this {@link Compiler}
	 * @return
	 */
	public boolean check() {
		var result = new SemanticChecker(parser()).check();

		if(result == null)
			return false;
		
		result.second().print(out);

		return true;
	}

	public boolean compile() {
		SemanticChecker checker = new SemanticChecker(parser());
		var result = checker.check();

		if(result == null) {
			System.err.println("Failed semantic checking, aborting");
			return false;
		}

		Generator generator = new Generator(result.second(), result.first(), result.third(), out);
		generator.emitLLVM();

		return true;
	}

	public static void main(String[] args) throws IOException {

		args = "-o Compiler/vsop-examples/main.vsop".split(" ");

		if (args.length != 2) {
			System.err.println("Usage: vsopc [-l|-p|-c] *input_file*");
			System.exit(-1);
			return;
		}

		String fileName = args[1];
		Compiler c = new Compiler(fileName, new PrintStream("output/program.ll"));

		boolean success = switch (args[0]) {
			case "-l" -> c.lex();
			case "-p" -> c.parse();
			case "-c" -> c.check();
			case "-o" -> c.compile();
			default -> false;
		};

		System.out.println("Sucess:" + success);
		System.exit(success ? SUCCESS : FAIL);
	}
}
