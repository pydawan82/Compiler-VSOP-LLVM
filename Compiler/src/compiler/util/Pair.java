package compiler.util;

/**
 * A tuple class that can group two objects.
 */
public record Pair<A, B>(A first, B second) {

	@Override
	public String toString() {
		return String.format("[%s, %s]", first, second);
	}
}
