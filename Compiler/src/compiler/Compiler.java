package compiler;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import compiler.error.LexicalError;
import compiler.error.SemanticError;
import compiler.error.SyntaxError;
import compiler.llvm.Generator;
import compiler.parsing.VSOPLexer;
import compiler.parsing.VSOPParser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * A VSOP to LLVM compiler
 */
public class Compiler {

	private static final int SUCCESS = 0;
	private static final int FAIL = -1;
	private static final int IO_ERROR = -2;
	private static final int CLANG_FAIL = -3;

	private String fileName;
	private PrintStream out;
	private boolean mustCloseOut = false;

	/**
	 * Creates a new Compiler that will comile the given file.
	 * 
	 * @param fileName - The name of the file
	 * @throws IOException if an I/O error occurs.
	 */
	public Compiler(String fileName, PrintStream out) {
		this.fileName = fileName;
		this.out = Objects.requireNonNull(out);

		mustCloseOut = out!=System.out;

		LexicalError.FILE_NAME = fileName;
		SyntaxError.FILE_NAME = fileName;
		SemanticError.FILE_NAME = fileName;
	}

	public Compiler(String fileName) {
		this(fileName, System.out);
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

	public VSOPLexer rawLexer() {
		return new VSOPLexer(input());
	}

	public VSOPLexer lexer() {
		VSOPLexer lexer = rawLexer();
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> r, Object o, int ln, int col, String msg, RecognitionException e) {
				System.err.println(new LexicalError(ln, col+1, msg));
			}
		});

		return lexer;
	}

	public VSOPParser rawParser() {
		return new VSOPParser(new CommonTokenStream(rawLexer()));
	}

	public VSOPParser parser() {
		VSOPParser parser = rawParser();
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> r, Object o, int ln, int col, String msg, RecognitionException e) {
                System.err.println(new SyntaxError(ln, col+1, msg));
			}
		});
		return parser;
	}

	/**
	 * Lex the file given to the Compiler, Outputs tokens on System.out and the
	 * lexical errors on System.err
	 * 
	 * @return <code>true</code> if the lexing terminated withou any error,
	 * <code>false</code> otherwise.
	 */
	public boolean lex() {
		return new LexicalAnalyzer(rawLexer()).lex();
	}

	/**
	 * Proceed to the syntax analysis and previous phases of compiling.
	 * The file parsed is the one given to this {@link Compiler}.
	 * @return <code>true</code> if parsing terminated without any error,
	 * <code>false</code> otherwise.
	 */
	public boolean parse() {
		return new SyntaxAnalyzer(rawParser()).parse();
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
		
		result.first().print(out);

		return true;
	}

	/**
	 * Compiles the VSOP file to LLVM IR
	 * @return <code>true</code> if compilation was successful; <code>false</code> otherwise.
	 */
	public boolean compile() {
		SemanticChecker checker = new SemanticChecker(parser());
		var result = checker.check();

		if(result == null) {
			System.err.println("Failed semantic checking, aborting");
			return false;
		}

		Generator generator = new Generator(result.second(), result.third(), result.fourth(), out);
		generator.emitLLVM();
		
		return true;
	}

	private static final String cmdFormat = "clang %s -o %s -w";
	private static String compileCmd(String fileName, String output) {
		return cmdFormat.formatted(fileName, output);
	}

	public boolean compileBin(String llName, String outName) throws IOException, InterruptedException {

		if(!compile())
			return false;

		if(mustCloseOut)
			out.close();

		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(compileCmd(llName, outName));
		
		int ret = process.waitFor();

		try(Scanner scan = new Scanner(process.getInputStream())) {
			scan.useDelimiter(System.lineSeparator());
			scan.forEachRemaining(System.out::println);
		}
		
		try(Scanner scan = new Scanner(process.getErrorStream())) {
			scan.useDelimiter(System.lineSeparator());
			scan.forEachRemaining(System.err::println);
		}

		return ret == SUCCESS;
	}

	public static void main(String[] args) {

		args = "-l Compiler/vsop-examples/list.vsop".split(" ");

		List<String> argList = Arrays.asList(args);

		int size = argList.size();

		if (!(size>=1 && size <= 3)) {
			System.err.println("Usage: vsopc [-e]? [-l|-p|-c|-i]? [input_file]");
			System.exit(-1);
			return;
		}

		if(argList.contains("-e")) {
			System.out.println("extensions are not supported");
			System.exit(FAIL);
		}
		
		try {
			boolean success;

			if(size == 1) {
				Pattern pattern = Pattern.compile("(.*)\\.vsop");
				String extension = ".ll";

				String fileName = argList.get(0);
				Matcher matcher = pattern.matcher(fileName);
				if(!matcher.matches()) {
					System.err.println("Expected a filename of format "+pattern.pattern());
					System.exit(FAIL);
				}

				String outName = matcher.group(1);
				String llName = outName + extension;
				PrintStream out = new PrintStream(llName);
				Compiler c = new Compiler(fileName, out);

				success = c.compileBin(llName, outName);
			} else {

				String fileName = argList.get(1);
			
				Compiler c = new Compiler(fileName);

				success = switch (args[0]) {
					case "-l" -> c.lex();
					case "-p" -> c.parse();
					case "-c" -> c.check();
					case "-i" -> c.compile();
					default -> false;
				};
			}

			System.exit(success ? SUCCESS : FAIL);
			
		} catch(IOException e) {
			System.exit(IO_ERROR);
		} catch(InterruptedException e) {
			System.exit(CLANG_FAIL);
		} catch(Exception e) {
			//System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(FAIL);
		}
	}
}
