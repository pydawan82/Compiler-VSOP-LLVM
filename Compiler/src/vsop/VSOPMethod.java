package vsop;

import java.util.List;

public class VSOPMethod {
	
	public final String name;
	public List<VSOPField> args;
	public VSOPType ret;
	
	public VSOPMethod(String name, List<VSOPField> args, VSOPType ret) {
		this.name = name;
		this.args = args;
		this.ret = ret;
	}
	
	
}
