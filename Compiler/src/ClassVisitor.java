import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import vsop.VSOPClass;
import vsop.VSOPField;
import vsop.VSOPMethod;
import vsop.VSOPType;


import static vsop.VSOPConstants.*;

public class ClassVisitor {
	private Queue<Runnable> taskq = new LinkedList<>();
	private Map<String, VSOPClass> classMap = new HashMap<>();
	private Queue<SemanticError> errorList = new LinkedList<SemanticError>();
	
	public ClassVisitor() {
		classMap.put(OBJECT.id, OBJECT);
	}
	
	private void flushTaskQueue() {
		while(!taskq.isEmpty())
			taskq.poll().run();
	}
	
	public void flushErrorQueue() {
		while(!errorList.isEmpty())
			errorList.poll().printError();
	}
	
	public Map<String, VSOPClass> classMap(VSOPParser.ProgramContext ctx) {
		
		visitProgram(ctx);	
		flushTaskQueue();
		flushErrorQueue();
		
		return classMap;
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
		
		final VSOPClass clazz = new VSOPClass(id, null, pair.first, pair.second);
		VSOPClass old = classMap.putIfAbsent(id, clazz);
		if(old != null)
			errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(), 
					String.format("Class %s is redefined", id)));
		
		taskq.add(() -> {
			clazz.setSuper(classMap.get(superId));
			
			if(clazz.getSuper() == null)
				errorList.add(new SemanticError(ctx.idext.getLine(), ctx.idext.getCharPositionInLine(), 
						String.format("Class %s is undefined", superId)));
		});
		
		return clazz;
	}

	private Pair<Map<String, VSOPField>, Map<String, VSOPMethod>> visitClassBody(VSOPParser.ClassBodyContext ctx) {
		
		Map<String, VSOPField> fields = new HashMap<>();
		
		for(var field: ctx.field()) {
			VSOPField f = visitField(field);
			fields.put(f.name, f);
		}
		
		Map<String, VSOPMethod> methods = new HashMap<>();
		
		for(var method: ctx.method()) {
			VSOPMethod m = visitMethod(method);
			methods.put(m.name, m);
		}
		
		return new Pair<>(fields, methods);
	}

	private VSOPField visitField(VSOPParser.FieldContext ctx) {
		String id = ctx.id.getText();
		
		final VSOPField field = new VSOPField(id, null);
		taskq.add(() -> {
			field.type = getType(ctx.type());
			if(field.type == null) {
				//TODO GENERER UN VRAIE ERREUR
				System.err.print("type undefined");
			}
		});
		
		return field;
	}

	private VSOPMethod visitMethod(VSOPParser.MethodContext ctx) {
		
		String id = ctx.id.getText();
		VSOPMethod method = new VSOPMethod(id, null, null);
		taskq.add(() -> {
			method.args = getFormals(ctx.formals());
			method.ret = getType(ctx.type());
		});
		
		return method;
	}
	
	private List<VSOPField> getFormals(VSOPParser.FormalsContext ctx) {
		List<VSOPField> args = new ArrayList<VSOPField>();
		
		for(var formal: ctx.formal())
			args.add(getFormal(formal));
		
		return args;
	}
	
	public VSOPField getFormal(VSOPParser.FormalContext ctx) {
		return new VSOPField(ctx.id.getText(), getType(ctx.type()));
	}
	
	private VSOPType getType(VSOPParser.TypeContext ctx) {
		String txt = ctx.getText();
		VSOPType type = primitiveTypeMap.get(txt);
		
		if(type != null)
			return type;
		
		type = classMap.get(txt);
		
		if(type == null)
			errorList.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), 
					String.format("Type %s is undefined", ctx.getText())));
		
		return type;
	}
}
