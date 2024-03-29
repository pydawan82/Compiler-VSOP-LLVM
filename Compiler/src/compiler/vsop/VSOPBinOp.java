package compiler.vsop;

/**
 * Class representing a Binary operator.
 */

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
