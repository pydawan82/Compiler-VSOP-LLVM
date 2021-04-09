package vsop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class VSOPClass extends VSOPType {
	public VSOPClass superClass;
	public final Map<String, VSOPField> fields = new HashMap<>();
	public final Map<String, VSOPMethod> functions = new HashMap<>();
	
	public final int ln,col;
	
	public VSOPClass(String name, VSOPClass superClass, Map<String, VSOPField> fields,
			Map<String, VSOPMethod> functions, int ln, int col) {
		super(name, null);
		
		this.superClass = superClass;
		
		this.fields.putAll(fields);
		this.functions.putAll(functions);
		
		this.ln = ln;
		this.col = col;
	}
	
	public boolean isAncestor(VSOPClass c) {
		if(this==c)
			return true;
		if(c.superClass == null)
			return false;
		
		return isAncestor(c.superClass);
	}
	
	public List<SemanticError> checkFieldInheritance() {
		List<SemanticError> errors = new LinkedList<>();
		
		for(VSOPClass c = superClass; c!= null; c = c.superClass) {
			for(String key: c.fields.keySet()) {
				VSOPField f = c.fields.get(key);
				VSOPField old = fields.put(key, f);
				if(old != null)
					errors.add(new SemanticError(f.ln, f.col,
							String.format("field %s in class %s is redefined in %s", f.name, c.id, this.id)));
			}
		}
		
		return errors;
	}
	
	public List<SemanticError> checkMethodInheritance() {
		List<SemanticError> errors = new LinkedList<>();
		
		for(VSOPClass c = superClass; c!= null; c = c.superClass) {
			for(String key: c.functions.keySet()) {
				VSOPMethod m = c.functions.get(key);
				VSOPMethod old = functions.putIfAbsent(key, m);
				if(old!=null && (!m.args.equals(old.args) || m.ret != old.ret))
					errors.add(new SemanticError(m.ln, m.col,
							String.format("method %s in class %s is redefined in %s with wrong signature", m.name, c.id, this.id)));
					
			}
		}
		
		return errors;
	}
	
	public SemanticError checkCyclicInheritance() {
		Map<String, VSOPClass> map = new HashMap<>();
		
		for(VSOPClass c = superClass; c!= null; c=c.superClass) {
			if(c==this) {
				return new SemanticError(ln, col, String.format("Cyclic inheritance of class %s", id));
			}
			
			if(map.containsKey(c.id))
				return null;
			
			map.put(c.id, c);
		}
		
		return null;
	}
	
	@Override
	public boolean canCast(VSOPType target) {
		if(!(target instanceof VSOPClass))
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
		while(c1 != null) {
			s1.push(c1);
			c1 = c1.superClass;
		}
		
		Stack<VSOPClass> s2 = new Stack<>();
		while(c2 != null) {
			s2.push(c2);
			c2 = c2.superClass;
		}
		
		VSOPClass common = null;
		 while(!s1.isEmpty() && !s2.isEmpty() && s1.peek() == s2.peek()); {
			common = s1.pop();
			s2.pop();
		}
		
		return common;
	}
}
