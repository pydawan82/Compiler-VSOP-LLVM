package vsop;

public class VSOPField {

	public final String name;
	public VSOPType type;

	public final int ln, col;

	public VSOPField(String name, VSOPType type, int ln, int col) {
		this.name = name;
		this.type = type;

		this.ln = ln;
		this.col = col;
	}

	public VSOPField(String name, VSOPType type) {
		this(name, type, 0, 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VSOPField other = (VSOPField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
