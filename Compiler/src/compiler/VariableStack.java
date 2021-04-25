package compiler;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import compiler.vsop.VSOPType;

public class VariableStack {

	private Map<String, Stack<VSOPType>> vars = new HashMap<>();

	public void push(String id, VSOPType type) {
		Stack<VSOPType> stack = vars.get(id);
		if (stack == null) {
			stack = new Stack<>();
			vars.put(id, stack);
		}

		stack.push(type);
	}

	public VSOPType get(String id) {
		Stack<VSOPType> stack = vars.get(id);

		if (stack == null || stack.isEmpty())
			return null;

		return stack.peek();
	}

	public VSOPType pop(String id) {
		Stack<VSOPType> stack = vars.get(id);
		if (stack == null)
			throw new EmptyStackException();

		try {
			return stack.pop();
		} finally {
			if (stack.isEmpty())
				vars.remove(id);
		}
	}
}
