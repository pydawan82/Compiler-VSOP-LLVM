
public class SemanticError {
	public static String fName = "";
	
	public final int ln, col;
	public final String msg;
	
	public SemanticError(int ln, int col, String msg) {
		this.ln = ln;
		this.col = col;
		this.msg = msg;
	}
	
	public void printError() {
		System.err.print(toString());
		System.err.println();
	}
	
	@Override
	public String toString() {
		return String.format("%s:%d:%d: semantic error: %s", fName, ln, col, msg);
	}
}
