package compiler.util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A node of a {@link Tree}. Each node is associated with an object of
 * type {@link T}. Each node can also have multiple childs.
 */
public class Node<T> {
	/**
	 * The object associated to this {@link Node}.
	 */
	private final T value;

	/**
	 * The set of childs
	 */
	private Set<Node<T>> childs = new HashSet<>();

	/**
	 * Creates a new {@link Node} with a given associated value
	 */
	public Node(T value) {
		this.value = Objects.requireNonNull(value);
	}

	/**
	 * 
	 * @return the value associated with this {@link Node}
	 */
	public T value() {
		return value;
	}

	/**
	 * Adds a new child to this {@link Node}
	 * @param child - the child
	 */
	public void addChild(Node<T> child) {
		childs.add(child);
	}

	/**
	 * A top down, depth first visit of the subtree from this {@link Node}.
	 * @param consumer - The consumer applied to the value of each {@link Node}.
	 */
	public void visit(Consumer<T> consumer) {
		consumer.accept(value);
		childs.forEach(child -> child.visit(consumer));
	}
}
