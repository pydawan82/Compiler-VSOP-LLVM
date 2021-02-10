import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;

public class Compiler {

	PrintStream out = System.out;
	PrintStream err = System.err;
	
	public Compiler(String fName) throws IOException {
		CharStream input = new ANTLRFileStream(fName);
		VSOPLexer lexer = new VSOPLexer(input);
		
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
	}
}
