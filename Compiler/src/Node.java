import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	public T value;
	public List<Node<T>> childs = new ArrayList<>();
	
	public Node(T value) {
		this.value = value;
	}
}
