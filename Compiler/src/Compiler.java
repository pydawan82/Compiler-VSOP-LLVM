import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

public class Compiler {

	private PrintStream out = System.out;
	private PrintStream err = System.err;
	private VSOPLexer lexer;
	private VSOPParser parser;
	private String fName;
	private List<String> textTypes = Arrays.asList("INTEGER_LITERAL", "STRING_LITERAL",
			"OBJECT_IDENTIFIER", "TYPE_IDENTIFIER");

	private boolean success = false;
	
	/**
	 * Creates a new Compiler that will comile the given file.
	 * @param fName - The name of the file
	 * @throws IOException if an I/O error occurs. 
	 */
	public Compiler(String fName) throws IOException {
		CharStream input = CharStreams.fromFileName(fName);
		lexer = new VSOPLexer(input);
		this.fName = fName;
		
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				success = false;
				printTokenError(line, charPositionInLine+1, msg);
			}
		});
		
		parser = new VSOPParser(new CommonTokenStream(lexer));
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				success = false;
				printSyntaxError(line, charPositionInLine+1, msg);
			}
		});
		
	}
	
	/**
	 * Lex the file given to the Compiler,
	 * Outputs tokens on System.out and the lexical errors on System.err
	 * @return <code>true</code> if the lexing terminated withou any error, <code>false</code> otherwise.
	 */
	public boolean lex() {
		success = true;
		Vocabulary voc = lexer.getVocabulary();
		while(true) {
			Token t = lexer.nextToken();
			if(t.getType() == -1)
				break;
			
			String txt = null;
			if(textTypes.contains(voc.getSymbolicName(t.getType())))
					txt = t.getText();
			
			printToken(t.getLine(), t.getCharPositionInLine()+1, toLowerCase(voc.getSymbolicName(t.getType())), txt);
		}
	
		return success;
	}
	
	/**
	 * Returns the lowercase version of the token name in order to
	 * respect naming convetions.
	 * @param tokenName - The name of the token
	 * @return The lower caser version of the token
	 */
	private static String toLowerCase(String tokenName) {
		String lower = tokenName.toLowerCase();
		return lower.replace('_', '-');
	}
	
	/**
	 * Prints the token on System.out
	 * @param ln - The line of the first char of the token
	 * @param cl - The column of the first char of the token
	 * @param type - The name of the type of the token
	 * @param text - The text of the token or null if nothing should not be printed
	 */
	private void printToken(int ln, int cl, String type, String text) {
			out.printf("%d,%d,%s", ln, cl, type);
			if(text != null)
				out.printf(",%s", text);
			out.println();
	}
	
	/**
	 * Prints the token error on System.err
	 * @param ln - The line of the error
	 * @param cl - The column of the error
	 * @param msg - The message representing the error
	 */
	private void printTokenError(int ln, int cl, String msg) {
		err.printf("%s:%d:%d: lexical error: %s", fName, ln, cl, msg);
		err.println();
	}
	
	private void printSyntaxError(int ln, int cl, String msg) {
		err.printf("%s:%d:%d: syntax error: %s", fName, ln, cl, msg);
		err.println();
	}
	
	public void parse() {
		
		ParseTree tree = parser.program();
		CustomVisitor visitor = new CustomVisitor();
		try(Chrono c = new Chrono()) {
			visitor.visit(tree);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void printTree() {
		
	}
	
	public static void main(String[] args) throws IOException {
		String fName = "vsop-examples/main.vsop";
		Compiler c = new Compiler(fName);
		c.parse();
	}
}
