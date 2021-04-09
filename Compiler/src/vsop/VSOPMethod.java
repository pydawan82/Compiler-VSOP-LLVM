package vsop;

import java.util.List;

public class VSOPMethod {
	
	public final String name;
	public List<VSOPField> args;
	public VSOPType ret;
	
	public final int ln,col;
	
	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret, int ln, int col) {
		this.name = name;
		this.args = args;
		this.ret = ret;
		this.ln = ln;
		this.col = col;
	}
	
	
}
