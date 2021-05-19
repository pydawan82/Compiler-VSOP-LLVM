package compiler.util;

/**
 * A tuble class used to pack 3 objects.
 */
public record Triplet<A,B,C> (A first, B second, C third) {

	@Override
	public String toString() {
		return String.format("[%s, %s]", first, second, third);
	}
}
