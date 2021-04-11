import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import vsop.SemanticError;
import vsop.VSOPClass;
import vsop.VSOPField;
import vsop.VSOPMethod;
import vsop.VSOPType;


import static vsop.VSOPConstants.*;

public class ClassVisitor {
	private Queue<Runnable> taskq = new LinkedList<>();
	private Map<String, Node<VSOPClass>> classMap = new HashMap<>();
	private Queue<SemanticError> errorList = new LinkedList<SemanticError>();
	private Tree<VSOPClass> classTree = new Tree<>(OBJECT);
	
	public ClassVisitor() {
		classMap.put(OBJECT.id, classTree.root);
	}
	
	private void flushTaskQueue() {
		while(!taskq.isEmpty())
			taskq.poll().run();
	}
	
	public void flushErrorQueue() {
		while(!errorList.isEmpty())
			errorList.poll().printError();
	}
	
	private boolean checkCyclicInheritance() {
		boolean ok = true;
		
		for(var key: classMap.keySet()) {
			VSOPClass cl = classMap.get(key).value;
			SemanticError e = cl.checkCyclicInheritance();
			if(e!=null) {
				errorList.add(e);
				ok = false;
			}
		}
		
		return ok;
	}
	
	private void checkInheritance() {
		classTree.visit(cl -> {
			errorList.addAll(cl.checkFieldInheritance());
			errorList.addAll(cl.checkMethodInheritance());
		});
	}
	
	private void checkMain() {
		Node<VSOPClass> main = classMap.get(MAIN_CLASS);
		if(main == null) {
			errorList.add(new SemanticError(1, 1,
					String.format("Could not find class %s", MAIN_CLASS)));
			return;
		}
		
		VSOPMethod m = main.value.functions.get(MAIN_METHOD);
		if(m == null) {
			errorList.add(new SemanticError(1, 1,
					String.format("Could not find %s method in class %s", MAIN_METHOD, MAIN_CLASS)));
			return;
		}
		
		if(!m.args.equals(MAIN_ARGS) || m.ret != MAIN_RET) {
			errorList.add(new SemanticError(1, 1,
					String.format("%s method has wrong signature, expected no args and return type %s", MAIN_METHOD, MAIN_RET.id)));
			return;
		}
	}
	
	public Map<String, VSOPClass> classMap(VSOPParser.ProgramContext ctx) {
		
		visitProgram(ctx);	
		flushTaskQueue();
		if(checkCyclicInheritance())
			checkInheritance();
		checkMain();
		
		boolean ok = errorList.size() == 0;
		
		flushErrorQueue();
		
		final Map<String, VSOPClass> map = new HashMap<>();
		classMap.forEach((key, node) -> {map.put(key, node.value);});
		
		return ok ? map : null;
	}

	private Void visitProgram(VSOPParser.ProgramContext ctx) {
		
		List<VSOPParser.ClazzContext> clazzList = ctx.clazz();
		
		for(var clazz: clazzList)
			visitClazz(clazz);
		
		return null;
	}

	private VSOPClass visitClazz(VSOPParser.ClazzContext ctx) {
		
		final String id = ctx.id.getText();
		final String superId = ctx.idext!=null ? ctx.idext.getText() : OBJECT.id;

		Pair<Map<String, VSOPField>, Map<String, VSOPMethod>> pair = visitClassBody(ctx.classBody());
		
		final VSOPClass clazz = new VSOPClass(id, null, pair.first, pair.second, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		Node<VSOPClass> node = new Node<>(clazz);
		Node<VSOPClass> old = classMap.putIfAbsent(id, node);
		if(old != null)
			errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(), 
					String.format("Class %s is redefined", id)));
		
		taskq.add(() -> {
			Node<VSOPClass> parent = classMap.get(superId);
			
			if(parent == null)
				errorList.add(new SemanticError(ctx.idext.getLine(), ctx.idext.getCharPositionInLine(), 
						String.format("Class %s is undefined", superId)));
			else {
				clazz.superClass = parent.value;
				parent.childs.add(node);
			}
		});
		
		return clazz;
	}

	private Pair<Map<String, VSOPField>, Map<String, VSOPMethod>> visitClassBody(VSOPParser.ClassBodyContext ctx) {
		
		Map<String, VSOPField> fields = new HashMap<>();
		
		for(var field: ctx.field()) {
			VSOPField f = visitField(field);
			VSOPField old = fields.putIfAbsent(f.name, f);
			if(old != null) {
				errorList.add(new SemanticError(field.start.getLine(), field.start.getCharPositionInLine(), 
						String.format("redefinition of field %s", field.id.getText())));
			}
		}
		
		Map<String, VSOPMethod> methods = new HashMap<>();
		
		for(var method: ctx.method()) {
			VSOPMethod m = visitMethod(method);
			VSOPMethod old = methods.putIfAbsent(m.name, m);
			if(old != null) {
				errorList.add(new SemanticError(method.start.getLine(), method.start.getCharPositionInLine(), 
						String.format("redefinition of method %s", method.id.getText())));
			}
		}
		
		return new Pair<>(fields, methods);
	}

	private VSOPField visitField(VSOPParser.FieldContext ctx) {
		String id = ctx.id.getText();
		
		final VSOPField field = new VSOPField(id, null, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		taskq.add(() -> {
			field.type = getType(ctx.type());
			if(field.type == null) {
				errorList.add(new SemanticError(field.ln, field.col,
						String.format("Undefined type %s for field %s", ctx.type().getText(), field.name)));
			}
		});
		
		return field;
	}

	private VSOPMethod visitMethod(VSOPParser.MethodContext ctx) {
		
		String id = ctx.id.getText();
		VSOPMethod method = new VSOPMethod(id, null, null, ctx.start.getLine(), ctx.start.getCharPositionInLine());
		taskq.add(() -> {
			method.args = getFormals(ctx.formals());
			method.ret = getType(ctx.type());
		});
		
		return method;
	}
	
	private List<VSOPField> getFormals(VSOPParser.FormalsContext ctx) {
		Map<String,VSOPField> fnames = new HashMap<>();
		List<VSOPField> args = new ArrayList<VSOPField>();
		
		for(var formal: ctx.formal()) {
			VSOPField f = getFormal(formal);
			
			if(fnames.containsKey(f.name)) {
				errorList.add(new SemanticError(formal.start.getLine(), formal.start.getCharPositionInLine(),
						String.format("Duplicate formal %s", f.name)));
			}
			
			fnames.put(f.name, f);
			
			args.add(f);
		}
		
		return args;
	}
	
	public VSOPField getFormal(VSOPParser.FormalContext ctx) {
		return new VSOPField(ctx.id.getText(), getType(ctx.type()), ctx.start.getLine(), ctx.start.getChannel());
	}
	
	private VSOPType getType(VSOPParser.TypeContext ctx) {
		String txt = ctx.getText();
		VSOPType type = primitiveTypeMap.get(txt);
		
		if(type != null)
			return type;

		Node<VSOPClass> node = classMap.get(txt);
		
		if(node == null || (type=node.value) == null)
			errorList.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), 
					String.format("Type %s is undefined", ctx.getText())));
		
		return type;
	}
}
