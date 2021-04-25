package compiler.vsop;

import java.util.List;

public class VSOPMethod {

	public final String id;
	public List<VSOPField> args;
	public VSOPType ret;

	public final int ln, col;

	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret) {
		this(name, args, ret, 0, 0);
	}

	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret, int ln, int col) {
		this.id = name;
		this.args = args;
		this.ret = ret;
		this.ln = ln;
		this.col = col;
	}

}
