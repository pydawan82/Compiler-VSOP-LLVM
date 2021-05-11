package compiler.util;

public record Triplet<A,B,C> (A first, B second, C third) {

	@Override
	public String toString() {
		return String.format("[%s, %s]", first, second, third);
	}
}
