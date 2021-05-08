package compiler.util;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import compiler.vsop.VSOPType;

/**
 * A class used to handle declared variables in a context.
 * Maps for each id a stack of variables.
 * This class is probably useless in VSOP since a stack
 * would probably be enough.
 */
public class VariableStack {

	private Map<String, Stack<VSOPType>> vars = new HashMap<>();

	/**
	 * Push a new variable to its corresponding stack.
	 * @param id - The id of the variable
	 * @param type - The type of the variable
	 */
	public void push(String id, VSOPType type) {
		Stack<VSOPType> stack = vars.get(id);
		if (stack == null) {
			stack = new Stack<>();
			vars.put(id, stack);
		}

		stack.push(type);
	}

	/**
	 * Gets the {@link VSOPType} of a variable
	 * @param id - The id of the variable
	 * @return the {@link VSOPType} of the corresponding to the variable
	 * if it exists, <code>false</code> otherwise.
	 */
	public VSOPType get(String id) {
		Stack<VSOPType> stack = vars.get(id);

		if (stack == null || stack.isEmpty())
			return null;

		return stack.peek();
	}

	/**
	 * Pop the given variable from the stack and returns its {@link VSOPType}
	 * @param id - The id of the variable
	 * @return The {@link VSOPType} of the variable.
	 * @throws EmptyStackException if the stack linked to this id is empty.
	 */
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
