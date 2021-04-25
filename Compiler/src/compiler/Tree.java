import java.util.Stack;
import java.util.function.Consumer;

public class Tree<T> {

	public Node<T> root;

	public Tree(T root) {
		this.root = new Node<>(root);
	}

	public void visit(Consumer<T> consumer) {
		final Stack<Node<T>> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node<T> n = stack.pop();
			consumer.accept(n.value);
			n.childs.forEach((child) -> stack.push(child));
		}
	}
}
