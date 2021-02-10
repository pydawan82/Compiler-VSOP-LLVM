import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class Compiler {

	PrintStream out = System.out;
	PrintStream err = System.err;
	VSOPLexer lexer;
	
	public Compiler(String fName) throws IOException {
		CharStream input = CharStreams.fromFileName("vsop/main.vsop");
		lexer = new VSOPLexer(input);
		
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
		while(true) {
			Token t = lexer.nextToken();
			if(t.getType() == -1)
				break;
			print(t.getLine(), t.getCharPositionInLine()+1, ""+t.getType(), t.getText());
		}
	}
	
	private void print(int ln, int cl, String type, String text) {
			out.printf("%d,%d,%s", ln, cl, type);
			if(text != null)
				out.printf(",%s", text);
			out.println();
	}
	
	private void printError(int ln, int cl, String msg) {
		err.printf("%d:%d: lexical error: %s", ln, cl, msg);
		err.println();
	}
	
	public static void main(String[] args) throws IOException {
		Compiler c = new Compiler("vsop/main.vsop");
		c.lex();
	}
}
