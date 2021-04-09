package vsop;

public class VSOPType {
	public final String id;
	public final Object defaultValue;

	public VSOPType(String id, Object defaultValue) {
		this.id = id;
		this.defaultValue = defaultValue;
	}
	
	public boolean canCast(VSOPType target) {
		return this==target;
	}
}
