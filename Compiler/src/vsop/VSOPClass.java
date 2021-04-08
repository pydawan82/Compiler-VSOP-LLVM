package vsop;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VSOPClass extends VSOPType {
	private VSOPClass superClass;
	public final Map<String, VSOPField> fields = new HashMap<>();
	public final Map<String, VSOPMethod> functions = new HashMap<>();
	
	public VSOPClass(String name, VSOPClass superClass, Map<String, VSOPField> fields, Map<String, VSOPMethod> functions) {
		super(name, null);
		
		setSuper(superClass);
		
		this.fields.putAll(fields);
		this.functions.putAll(functions);
	}
	
	public void setSuper(VSOPClass superClass) {
		if(this.superClass != null)
			return;
		
		this.superClass = superClass;
		if(superClass != null) {
			this.fields.putAll(superClass.fields);
			this.functions.putAll(superClass.functions);
		}
	}
	
	public VSOPClass getSuper() {
		return superClass;
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
