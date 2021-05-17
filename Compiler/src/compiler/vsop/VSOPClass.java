package compiler.vsop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.error.SemanticError;

//TODO Comment class

public class VSOPClass extends VSOPType {
	public VSOPClass superClass;
	public final Map<String, VSOPField> fields = new HashMap<>();

	private final Map<String, VSOPMethod> methods = new HashMap<>();

	public final int ln, col;

	public VSOPClass(String name, VSOPClass superClass, Map<String, VSOPField> fields,
			Map<String, VSOPMethod> methods, int ln, int col) {
		super(name, null);
		
		this.superClass = superClass;

		fields().putAll(fields);
		methods().putAll(methods);

		this.ln = ln;
		this.col = col;

		methods.values().forEach(m -> m.setParent(this));
	}

	public VSOPClass(String name, VSOPClass superClass, Map<String, VSOPField> fields,
			Map<String, VSOPMethod> functions, ParserRuleContext ctx) {
		this(name, superClass, fields, functions, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	public VSOPClass(String name, VSOPClass superClass, Map<String, VSOPField> fields,
			Map<String, VSOPMethod> functions) {
		this(name, superClass, fields, functions, 0, 0);
	}

	public Map<String, VSOPField> fields() {
		return fields;
	}

	public List<VSOPField> fieldList() {
		List<VSOPField> list = fields.values().stream()
				.collect(Collectors.toList());
		list.sort(null);

		return list;
	}

	public Map<String, VSOPMethod> methods() {
		return methods;
	}

	public List<VSOPMethod> methodList() {
		List<VSOPMethod> list = methods.values().stream()
				.collect(Collectors.toList());
		list.sort(null);

		return list;
	}

	public boolean isAncestor(VSOPClass c) {
		if (this == c)
			return true;
		if (c.superClass == null)
			return false;

		return isAncestor(c.superClass);
	}

	public List<SemanticError> checkFieldInheritance() {
		List<SemanticError> errors = new LinkedList<>();

		if (superClass == null)
			return errors;

		int max = -1;
		for (String key : superClass.fields.keySet()) {
			VSOPField f = superClass.fields.get(key);
			max = Math.max(max, f.ord);
			VSOPField old = fields.put(key, f);

			if (old != null)
				errors.add(
						new SemanticError(f.ln, f.col, String.format("field %s is redefined in %s", f.id, this.id)));
		}

		for(VSOPField f: fields().values()) {
			if(f.ord == -1) {
				max++;
				f.ord = max;
			}
		}

		return errors;
	}

	public List<SemanticError> checkMethodInheritance() {
		List<SemanticError> errors = new LinkedList<>();

		if (superClass == null)
			return errors;
		
		int max = -1;

		for (String key : superClass.methods.keySet()) {
			VSOPMethod m = superClass.methods.get(key);
			max = Math.max(max, m.ord);
			VSOPMethod old = methods.putIfAbsent(key, m);

			if(old != null)
				old.ord = m.ord;
				
			if (old != null && (!m.args.equals(old.args) || m.returnType != old.returnType))
				errors.add(new SemanticError(m.ln, m.col,
						String.format("method %s is redefined in class %s with wrong signature", m.id, this.id)));

		}

		for(VSOPMethod m: methods().values()) {
			if(m.ord == -1) {
				max++;
				m.ord = max;
			}
		}

		return errors;
	}

	public SemanticError checkCyclicInheritance() {
		Map<String, VSOPClass> map = new HashMap<>();

		for (VSOPClass c = superClass; c != null; c = c.superClass) {
			if (c == this) {
				return new SemanticError(ln, col, String.format("Cyclic inheritance of class %s", id));
			}

			if (map.containsKey(c.id))
				return null;

			map.put(c.id, c);
		}

		return null;
	}

	@Override
	public boolean canCast(VSOPType target) {
		if (!(target instanceof VSOPClass))
			return false;

		VSOPClass targetClass = (VSOPClass) target;
		return targetClass.isAncestor(this);
	}

	@Override
	public String toString() {
		return String.format("VSOPClass(%s)", id);
	}

	public static VSOPClass commonAncestor(VSOPClass c1, VSOPClass c2) {
		Stack<VSOPClass> s1 = new Stack<>();
		while (c1 != null) {
			s1.push(c1);
			c1 = c1.superClass;
		}

		Stack<VSOPClass> s2 = new Stack<>();
		while (c2 != null) {
			s2.push(c2);
			c2 = c2.superClass;
		}

		VSOPClass common = null;
		while (!s1.isEmpty() && !s2.isEmpty() && s1.peek() == s2.peek()) {
			common = s1.pop();
			s2.pop();
		}

		return common;
	}
}
