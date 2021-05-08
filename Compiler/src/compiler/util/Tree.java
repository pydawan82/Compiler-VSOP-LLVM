package compiler.util;

import java.util.function.Consumer;

/**
 * A class used to represent a Tree. A tree has a root {@link Node}.
 * Each node maps to an object of type {@link T} and can have multiple childs {@link Node}s.
 */
public class Tree<T> {

	/**
	 * The root node of the tree.
	 */
	private final Node<T> root;

	/**
	 * Creates a new Tree with a given root value.
	 * @param root
	 */
	public Tree(T root) {
		this.root = new Node<>(root);
	}

	/**
	 * Returns the root of this tree.
	 */
	public Node<T> root() {
		return root;
	}

	/**
	 * Visit the tree in depth first to consume the object associated with each node.
	 * @param consumer - The consumer called for each object in the tree
	 */
	public void visit(Consumer<T> consumer) {
		root.visit(consumer);
	}
}
