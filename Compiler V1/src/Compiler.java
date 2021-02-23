import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;


import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

public class Compiler {

	private PrintStream out = System.out;
	private PrintStream err = System.err;
	private VSOPLexer lexer;
	private String fName;
	private List<String> textTypes = Arrays.asList("INTEGER_LITERAL", "STRING_LITERAL",
			"OBJECT_IDENTIFIER", "TYPE_IDENTIFIER");
	private String integerLiteral = "INTEGER_LITERAL";
	
	public Compiler(String fName) throws IOException {
		CharStream input = CharStreams.fromFileName(fName);
		lexer = new VSOPLexer(input);
		this.fName = fName;
		
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				
				printError(line, charPositionInLine+1, msg);
			}
		});
		
	}
	
	public void lex() {
		Vocabulary voc = lexer.getVocabulary();
		while(true) {
			Token t = lexer.nextToken();
			if(t.getType() == -1)
				break;
			
			String txt = null;
			if(integerLiteral.equals(voc.getSymbolicName(t.getType()))) {
				txt = toDecimal(t.getText());
			}
			else if(textTypes.contains(voc.getSymbolicName(t.getType())))
					txt = t.getText();
			
			print(t.getLine(), t.getCharPositionInLine()+1, toLowerCase(voc.getSymbolicName(t.getType())), txt);
		}
	}
	
	private String toDecimal(String integer) {
		if(integer.startsWith("0x")) {
			return Integer.toString(Integer.parseInt(integer.substring(2),16));
		}
		else
			return integer;
	}
	
	private static String toLowerCase(String s) {
		String lower = s.toLowerCase();
		return lower.replace('_', '-');
	}
	
	private void print(int ln, int cl, String type, String text) {
			out.printf("%d,%d,%s", ln, cl, type);
			if(text != null)
				out.printf(",%s", text);
			out.println();
	}
	
	private void printError(int ln, int cl, String msg) {
		err.printf("%s:%d:%d: lexical error: %s", fName, ln, cl, msg);
		err.println();
	}
	
	public static void main(String[] args) throws IOException {
		String fName = null;
		
		if(args.length==0) {
			System.err.println("Usage: vsopc -lex <SOURCE-FILE>");
			System.exit(-1);
		}
		
		for(int i=0; i< args.length; i++) {
			if(args[i]=="-lex" && i<args.length-1) {
				fName = args[i+1];
				i++;
			}
			else {
				System.err.println("Unrecognized parameter. Usage: vsopc -lex <SOURCE-FILE>");
				System.exit(-1);
				return;
			}
		}
		
		if(fName == null) {
			System.err.println("No input file. Usage: vsopc -lex <SOURCE-FILE>");
		}
		
		Compiler c = new Compiler(fName);
		c.lex();
	}
}
