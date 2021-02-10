import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Compiler {

	PrintStream out = System.out;
	PrintStream err = System.err;
	VSOPLexer lexer;
	
	public Compiler(String fName) throws IOException {
		CharStream input = CharStreams.fromFileName("vsop/main.vsop");
		lexer = new VSOPLexer(input);
	}
	
	public void compile() {
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
	
	private void printError() {
		err.printf("");
	}
	
	public static void main(String[] args) throws IOException {
		Compiler c = new Compiler("vsop/main.vsop");
		c.compile();
	}
}
