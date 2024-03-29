package compiler.vsop;


/**
 * Class representing a field in the VSOP language.
 */

public class VSOPField implements Comparable<VSOPField> {

	public final String id;
	public VSOPType type;

	public final int ln, col;

	public int ord = -1;

	public VSOPField(String name, VSOPType type, int ln, int col) {
		this.id = name;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public int compareTo(VSOPField f) {
		return this.ord - f.ord;
	}

}
