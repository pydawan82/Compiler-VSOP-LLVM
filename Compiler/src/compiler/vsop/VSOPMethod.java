package compiler.vsop;

import java.util.List;

//TODO Comment class

public class VSOPMethod {

	public final String id;
	public List<VSOPField> args;
	public VSOPType returnType;

	private VSOPClass parent;

	public final int ln, col;

	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret) {
		this(name, args, ret, 0, 0);
	}

	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret, int ln, int col) {
		this.id = name;
		this.args = args;
		this.returnType = ret;
		this.ln = ln;
		this.col = col;
	}

	public VSOPClass getParent() {
		return parent;
	}

	void setParent(VSOPClass parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return String.format("VSOPMethod(%s)", id);
	}
}
