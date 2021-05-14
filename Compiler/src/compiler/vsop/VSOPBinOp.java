package compiler.vsop;

//TODO Comment class

public class VSOPBinOp {
	public final String id;
	public final VSOPType opType;
	public final VSOPType retType;

	public VSOPBinOp(String id, VSOPType opType, VSOPType retType) {
		this.id = id;
		this.opType = opType;
		this.retType = retType;
	}
}
