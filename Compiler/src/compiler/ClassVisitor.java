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
	private Queue<Runnable> taskQueue = new LinkedList<>();
	private Map<String, Node<VSOPClass>> classMap = new HashMap<>();
	private Queue<SemanticError> errorQueue = new LinkedList<SemanticError>();
	private Tree<VSOPClass> classTree = new Tree<>(OBJECT);

	public ClassVisitor() {
		classMap.put(OBJECT.id, classTree.root);
	}

	private void flushTaskQueue() {
		taskQueue.forEach((task) -> task.run());
		taskQueue.clear();
	}

	private void flushErrorQueue() {
		errorQueue.forEach((err) -> err.print());
		errorQueue.clear();
	}

	private boolean checkCyclicInheritance() {
		/*
		 * boolean ok = true;
		 * 
		 * for (var key : classMap.keySet()) { VSOPClass cl = classMap.get(key).value;
		 * SemanticError e = cl.checkCyclicInheritance(); if (e != null) {
		 * errorQueue.add(e); ok = false; } }
		 */

		// STREAM Version
		return classMap.values().stream().map((node) -> node.value.checkCyclicInheritance())
				.filter((error) -> error != null).map(errorQueue::add).reduce(true, Boolean::logicalAnd);

	}

	private void checkInheritance() {
		classTree.visit(cl -> {
			errorQueue.addAll(cl.checkFieldInheritance());
			errorQueue.addAll(cl.checkMethodInheritance());
		});
	}

	private void checkMain() {
		Node<VSOPClass> main = classMap.get(MAIN_CLASS);
		if (main == null) {
			errorQueue.add(new SemanticError(String.format("Could not find class %s", MAIN_CLASS)));
			return;
		}

		VSOPMethod m = main.value.functions.get(MAIN_METHOD);
		if (m == null) {
			errorQueue.add(
					new SemanticError(String.format("Could not find %s method in class %s", MAIN_METHOD, MAIN_CLASS)));
			return;
		}

		if (!m.args.equals(MAIN_ARGS) || m.ret != MAIN_RET) {
			errorQueue.add(new SemanticError(String.format(
					"%s method has wrong signature, expected no args and return type %s", MAIN_METHOD, MAIN_RET.id)));
			return;
		}
	}

	public Map<String, VSOPClass> classMap(VSOPParser.ProgramContext ctx) {
		visitProgram(ctx);
		flushTaskQueue();

		if (checkCyclicInheritance())
			checkInheritance();

		checkMain();

		boolean hasError = errorQueue.isEmpty();

		flushErrorQueue();

		final Map<String, VSOPClass> map = new HashMap<>();
		classMap.forEach((key, node) -> {
			map.put(key, node.value);
		});

		return hasError ? map : null;
	}

	private Void visitProgram(VSOPParser.ProgramContext ctx) {
		ctx.clazz().forEach(this::visitClazz);

		return null;
	}

	private VSOPClass visitClazz(VSOPParser.ClazzContext ctx) {

		String id = ctx.id.getText();
		String superId = ctx.idext != null ? ctx.idext.getText() : OBJECT.id;

		Pair<Map<String, VSOPField>, Map<String, VSOPMethod>> pair = visitClassBody(ctx.classBody());

		VSOPClass clazz = new VSOPClass(id, null, pair.first, pair.second, ctx);
		Node<VSOPClass> node = new Node<>(clazz);
		Node<VSOPClass> old = classMap.putIfAbsent(id, node);

		if (old != null)
			errorQueue.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Class %s is redefined", id)));

		taskQueue.add(() -> {
			Node<VSOPClass> parent = classMap.get(superId);

			if (parent == null)
				errorQueue.add(new SemanticError(ctx.idext.getLine(), ctx.idext.getCharPositionInLine(),
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

		for (var field : ctx.field()) {
			VSOPField f = visitField(field);
			VSOPField old = fields.putIfAbsent(f.name, f);
			if (old != null) {
				errorQueue.add(new SemanticError(field.start.getLine(), field.start.getCharPositionInLine(),
						String.format("redefinition of field %s", field.id.getText())));
			}
		}

		Map<String, VSOPMethod> methods = new HashMap<>();

		for (var method : ctx.method()) {
			VSOPMethod m = visitMethod(method);
			VSOPMethod old = methods.putIfAbsent(m.name, m);
			if (old != null) {
				errorQueue.add(new SemanticError(method.start.getLine(), method.start.getCharPositionInLine(),
						String.format("redefinition of method %s", method.id.getText())));
			}
		}

		return new Pair<>(fields, methods);
	}

	private VSOPField visitField(VSOPParser.FieldContext ctx) {
		String id = ctx.id.getText();
		VSOPField field = new VSOPField(id, null, ctx.start.getLine(), ctx.start.getCharPositionInLine());

		taskQueue.add(() -> {
			field.type = getType(ctx.type());
			if (field.type == null) {
				errorQueue.add(new SemanticError(field.ln, field.col,
						String.format("Undefined type %s for field %s", ctx.type().getText(), field.name)));
			}
		});

		return field;
	}

	private VSOPMethod visitMethod(VSOPParser.MethodContext ctx) {
		String id = ctx.id.getText();
		VSOPMethod method = new VSOPMethod(id, null, null, ctx.start.getLine(), ctx.start.getCharPositionInLine());

		taskQueue.add(() -> {
			method.args = getFormals(ctx.formals());
			method.ret = getType(ctx.type());
		});

		return method;
	}

	private List<VSOPField> getFormals(VSOPParser.FormalsContext ctx) {
		Map<String, VSOPField> formalNames = new HashMap<>();
		List<VSOPField> args = new ArrayList<VSOPField>();

		for (var formal : ctx.formal()) {
			VSOPField field = getFormal(formal);

			if (formalNames.containsKey(field.name)) {
				errorQueue.add(new SemanticError(formal, String.format("Duplicate formal name %s", field.name)));
			}

			formalNames.put(field.name, field);
			args.add(field);
		}

		return args;
	}

	public VSOPField getFormal(VSOPParser.FormalContext ctx) {
		return new VSOPField(ctx.id.getText(), getType(ctx.type()), ctx.start.getLine(), ctx.start.getChannel());
	}

	private VSOPType getType(VSOPParser.TypeContext ctx) {
		String txt = ctx.getText();
		VSOPType type = primitiveTypeMap.get(txt);

		if (type != null)
			return type;

		Node<VSOPClass> node = classMap.get(txt);

		if (node == null || (type = node.value) == null)
			errorQueue.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
					String.format("Type %s is undefined", ctx.getText())));

		return type;
	}
}
