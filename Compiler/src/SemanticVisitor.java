import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import vsop.SemanticError;
import vsop.VSOPBinOp;
import vsop.VSOPClass;
import vsop.VSOPField;
import vsop.VSOPMethod;
import vsop.VSOPType;

import org.antlr.v4.runtime.ParserRuleContext;

import static vsop.VSOPConstants.*;

public class SemanticVisitor implements VSOPParserVisitor<Object> {
	
	private Map<Class<?>, Function<ParserRuleContext, VSOPType>> map = new HashMap<>();
	
	PrintStream out = System.out;
	int tab = -1;
	String tabs = "\t";
	
	Map<String, VSOPClass> classMap = new HashMap<>();
	VSOPClass currentClass;
	
	Queue<SemanticError> errorList = new LinkedList<>();

	Stack<Map<String, VSOPType>> vars = new Stack<>();
	Stack<VSOPMethod> methodStack = new Stack<>();
	
	public SemanticVisitor(Map<String, VSOPClass> classMap) {
		map.put(VSOPParser.IfContext.class, (ParserRuleContext c) -> visitIf((VSOPParser.IfContext)c));
		map.put(VSOPParser.WhileContext.class, (ParserRuleContext c) -> visitWhile((VSOPParser.WhileContext)c));
		map.put(VSOPParser.LetContext.class, (ParserRuleContext c) -> visitLet((VSOPParser.LetContext)c));
		map.put(VSOPParser.AssContext.class, (ParserRuleContext c) -> visitAss((VSOPParser.AssContext)c));
		map.put(VSOPParser.NotContext.class, (ParserRuleContext c) -> visitNot((VSOPParser.NotContext)c));
		map.put(VSOPParser.BinopContext.class, (ParserRuleContext c) -> visitBinop((VSOPParser.BinopContext)c));
		map.put(VSOPParser.MinusContext.class, (ParserRuleContext c) -> visitMinus((VSOPParser.MinusContext)c));
		map.put(VSOPParser.IsnullContext.class, (ParserRuleContext c) -> visitIsnull((VSOPParser.IsnullContext)c));
		map.put(VSOPParser.SelfcallContext.class, (ParserRuleContext c) -> visitSelfcall((VSOPParser.SelfcallContext)c));
		map.put(VSOPParser.CallContext.class, (ParserRuleContext c) -> visitCall((VSOPParser.CallContext)c));
		map.put(VSOPParser.NewContext.class, (ParserRuleContext c) -> visitNew((VSOPParser.NewContext)c));
		map.put(VSOPParser.OiContext.class, (ParserRuleContext c) -> visitOi((VSOPParser.OiContext)c));
		map.put(VSOPParser.LitContext.class, (ParserRuleContext c) -> visitLit((VSOPParser.LitContext)c));
		map.put(VSOPParser.SelfContext.class, (ParserRuleContext c) -> visitSelf((VSOPParser.SelfContext)c));
		map.put(VSOPParser.UnitContext.class, (ParserRuleContext c) -> visitUnit((VSOPParser.UnitContext)c));
		map.put(VSOPParser.BraceExprContext.class, (ParserRuleContext c) -> visitBraceExpr((VSOPParser.BraceExprContext)c));
		map.put(VSOPParser.BlContext.class, (ParserRuleContext c) -> visitBl((VSOPParser.BlContext)c));
		
		this.classMap = classMap;
	}
	
	private void printTab() {
		for(int i=0; i<tab; i++) {
			out.print(tabs);
		}
	}
	
	public void flushErrorQueue() {
		while(!errorList.isEmpty())
			errorList.poll().printError();
	}
	
	@Override
	public Void visit(ParseTree arg0) {
		return null;
	}

	@Override
	public Void visitChildren(RuleNode arg0) {
		return null;
	}

	@Override
	public Void visitErrorNode(ErrorNode arg0) {
		return null;
	}

	@Override
	public Void visitTerminal(TerminalNode arg0) {
		return null;
	}

	@Override
	public Void visitProgram(VSOPParser.ProgramContext ctx) {
		tab++;
		
		List<VSOPParser.ClazzContext> clazzList = ctx.clazz();
		out.print('[');
		
		int i = 0;
		for(var clazz: clazzList) {
			i++;
			visitClazz(clazz);
			if(i!=clazzList.size()) {
				out.println(',');
				printTab();
			}
		}
		
		out.println(']');
		
		tab--;
		
		
		
		return null;
	}

	@Override
	public Void visitClazz(VSOPParser.ClazzContext ctx) {
		final String id = ctx.id.getText();
		currentClass = classMap.get(id);
		
		final String superId = ctx.idext!=null ? ctx.idext.getText() : OBJECT.id;

		tab++;
		
		out.printf("Class(%s, %s, ", id, superId);
		
		visitClassBody(ctx.classBody());
		
		out.print(')');
		
		tab--;
		
		return null;
	}

	@Override
	public Void visitClassBody(VSOPParser.ClassBodyContext ctx) {
		tab++;
		
		out.print('[');
		int i=0;
		for(var field: ctx.field()) {
			i++;
			out.println();
			printTab();
			
			visitField(field);
			
			if(i!=ctx.field().size()) {
				out.print(",");
			}
		}
		out.print("], ");
		
		if(ctx.field().size()!=0) {
			out.println();
			printTab();
		}
		
		out.print('[');
		
		i=0;
		for(var method: ctx.method()) {
			i++;
			out.println();
			printTab();
			
			visitMethod(method);
			
			if(i!=ctx.method().size()) {
				out.print(",");
			}
		}
		out.print(']');
		
		tab--;
		
		return null;
	}

	@Override
	public Void visitField(VSOPParser.FieldContext ctx) {
		tab++;
		
		String id = ctx.id.getText();
		
		out.printf("Field(%s, ", id);
		visitType(ctx.type());
		
		if(ctx.expr() != null) {
			out.print(", ");
			vars.push(new HashMap<>());
			visitExpr(ctx.expr());
			vars.pop();
		}
		
		out.print(')');
		tab--;
		
		return null;
	}

	@Override
	public Void visitMethod(VSOPParser.MethodContext ctx) {
		
		String id = ctx.id.getText();
		
		tab++;
		
		out.printf("Method(%s, ", id);
		
		visitFormals(ctx.formals());
		
		out.print(", ");
		
		visitType(ctx.type());
		out.print(", ");
		
		Map<String, VSOPType> params = new HashMap<>();
		VSOPMethod method = currentClass.functions.get(id);
		for(var field: method.args)
			params.put(field.name, field.type);
		
		vars.push(params);
		VSOPType ret = visitBlock(ctx.block());
		out.printf(":%s", ret.id);
		vars.pop();
		
		out.print(')');
		
		tab--;
		
		if(!ret.canCast(method.ret))
			errorList.add(new SemanticError(ctx.block().start.getLine(), ctx.block().start.getCharPositionInLine(),
					String.format("Method should return %s but got %s instead", method.ret.id, ret.id)));
		
		return null;
	}

	@Override
	public Void visitFormals(VSOPParser.FormalsContext ctx) {
		out.print('[');
		
		int i=0;
		for(var formal: ctx.formal()) {
			i++;
			
			out.println();
			printTab();
			
			visitFormal(formal);
			
			if(i != ctx.formal().size()) {
				out.print(",");
			}
		}
		
		if(ctx.formal().size()!=0) {
			out.println();
			printTab();
		}
		
		out.print(']');
		
		return null;
	}

	@Override
	public Void visitFormal(VSOPParser.FormalContext ctx) {
		out.print(ctx.id.getText()+" : ");
		visitType(ctx.type());
		return null;
	}

	@Override
	public VSOPType visitBlock(VSOPParser.BlockContext ctx) {
		
		Map<String, VSOPType> localVars = new HashMap<>();
		if(!vars.isEmpty())
			localVars.putAll(vars.peek());
		vars.push(localVars);
		
		tab++;
		
		out.print('[');
		VSOPType type = UNIT;
		
		int i=0;
		for(var expr: ctx.expr()) {
			i++;
			
			out.println();
			printTab();
			
			type = visitExpr(expr);
			
			if(i != ctx.expr().size()) {
				out.print(",");
			}
		}
		
		if(ctx.expr().size()!=0) {
			out.println();
			printTab();
		}
		
		out.print(']');
		tab--;
		
		vars.pop();
		
		return type;
	}

	public VSOPType visitExpr(VSOPParser.ExprContext ctx) {
		VSOPType type = map.get(ctx.getClass()).apply(ctx);
		
		if(ctx.getClass() != VSOPParser.BraceExprContext.class)
			out.printf(":%s", (type != null) ? type.id : "null");
		return type;
	}

	@Override
	public Void visitArgs(VSOPParser.ArgsContext ctx) {
		tab++;
		
		out.print("[");
		
		int i=0;
		for(var expr: ctx.expr()) {
			i++;
			
			out.println();
			printTab();
			
			visitExpr(expr);
			
			if(i != ctx.expr().size()) {
				out.print(",");
			}
		}
		
		if(ctx.expr().size()!=0) {
			out.println();
			printTab();
		}
		
		out.print("]");
		
		tab--;
		return null;
	}
	
	@Override
	public VSOPType visitLiteral(VSOPParser.LiteralContext ctx) {
		out.print(ctx.getText());
		
		if(ctx.INTEGER_LITERAL() != null) {
			return INT32;
		}
		
		if(ctx.STRING_LITERAL() != null) {
			return STRING;
		}
		
		if(ctx.booleanLiteral() != null) {
			return BOOL;
		}
		
		if(ctx.LPAR() != null) {
			return UNIT;
		}
		
		System.err.println("UNEXPECTED BRANCH!");
		
		return null;
	}

	@Override
	public Void visitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx) {
		System.err.println("UNEXPECTED BRANCH! 2");
		
		return null;
	}

	public VSOPType getType(VSOPParser.TypeContext ctx) {
		String txt = ctx.getText();
		VSOPType type = primitiveTypeMap.get(txt);
		
		if(type != null) {
			return type;
		}
		
		type = classMap.get(txt);
		
		if(type == null) {
			errorList.add(null);
		}
		
		return type;
	}
	
	@Override
	public Void visitType(VSOPParser.TypeContext ctx) {
		out.print(ctx.getText());
		return null;
	}
	
	@Override
	public VSOPType visitAss(VSOPParser.AssContext ctx) {
		String id = ctx.id.getText();
		
		out.printf("Assign(%s, ", id);
		VSOPType exprType = visitExpr(ctx.expr());
		out.print(")");
		
		VSOPType varType = (vars.isEmpty()) ? null : vars.peek().get(id);
		
		if(varType == null) {
			VSOPField f = currentClass.fields.get(id);
			if(f != null)
				varType = f.type;
		}
		
		if(varType == null)
			errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("Variable %s is not declared in this scope.", id)));
		
		if(varType != exprType)
			errorList.add(new SemanticError(ctx.expr().getStart().getLine(), ctx.expr().getStart().getCharPositionInLine(),
					"Expression type does not match variable type"));
		
		return varType;
	}
	
	@Override
	public VSOPType visitNew(VSOPParser.NewContext ctx) {
		String id = ctx.id.getText();
		
		out.printf("New(%s)", id);
		
		VSOPType type = classMap.get(id);
		if(type == null)
			errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(), 
					String.format("Invalid type indentifier %s", id)));
		
		return type;
	}
	
	@Override
	public VSOPType visitBl(VSOPParser.BlContext ctx) {
		return visitBlock(ctx.block());
	}
	
	@Override
	public VSOPType visitWhile(VSOPParser.WhileContext ctx) {
		out.print("While(");
		visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));
		out.print(")");
		
		return UNIT;
	}
	
	@Override
	public VSOPType visitNot(VSOPParser.NotContext ctx) {
		out.print("UnOp(not, ");
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");
		
		if(type != BOOL)
			errorList.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), 
					"not operator can only be used with BOOL"));
		
		return BOOL;
	}

	@Override
	public VSOPType visitMinus(VSOPParser.MinusContext ctx) {
		out.print("UnOp(-, " );
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");
		
		if(type != INT32)
			errorList.add(new SemanticError(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), 
					"minus operator can only be used with INT32"));
		
		return INT32;
	}

	@Override
	public VSOPType visitIsnull(VSOPParser.IsnullContext ctx) {
		out.print("UnOp(isnull, ");
		VSOPType type = visitExpr(ctx.expr());
		out.print(")");
		
		if(primitiveTypes.contains(type))
			errorList.add(new SemanticError(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
					"isnull operator can only be used with non primitive types"));
		
		return BOOL;
	}

	@Override
	public VSOPType visitSelfcall(VSOPParser.SelfcallContext ctx) {
		String id = ctx.id.getText();
		
		VSOPMethod method = currentClass.functions.get(id);
		if(method == null) {
			errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
					String.format("method %s is undefined for type %s", id, currentClass.id)));
		}
		
		
		out.print("Call(self, " + ctx.id.getText() + ", ");

		methodStack.push(method);
		visitArgs(ctx.args());
		methodStack.pop();
		
		out.print(')');
		
		return (method!=null) ? method.ret : UNIT;
	}

	@Override
	public VSOPType visitCall(VSOPParser.CallContext ctx) {
		
		String id = ctx.id.getText();
		
		
		out.print("Call(");
		
		VSOPType exprType = visitExpr(ctx.expr());
		
		out.print(", " + ctx.id.getText() + ", ");

		VSOPType retType = UNIT;
		if(exprType instanceof VSOPClass) {
			VSOPClass exprClass = (VSOPClass) exprType;
			VSOPMethod method = exprClass.functions.get(id);
			if(method == null)
				errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getCharPositionInLine(),
						String.format("method %s is undefined for type %s", id, exprClass.id)));
			retType = (method!=null) ? method.ret : UNIT;
			
			methodStack.push(method);
		} else {
			methodStack.push(null);
		}
		visitArgs(ctx.args());
		methodStack.pop();
		
		out.print(')');
		
		return retType;
	}
	
	@Override
	public VSOPType visitUnit(VSOPParser.UnitContext ctx) {
		out.print("()");
		return UNIT;
	}
	
	@Override
	public VSOPType visitLit(VSOPParser.LitContext ctx) {
		return visitLiteral(ctx.literal());
	}
	
	@Override
	public VSOPType visitSelf(VSOPParser.SelfContext ctx) {
		out.print("self");
		return currentClass;
	}
	
	@Override
	public VSOPType visitLet(VSOPParser.LetContext ctx) {
		
		String id = ctx.id.getText();
		VSOPType varType = getType(ctx.type());
		if(!vars.isEmpty())
			vars.peek().put(id, varType);
		
		out.printf("Let(%s, ", id);
		visitType(ctx.type());
		out.print(", ");
		
		if(ctx.as != null){
			VSOPType asType = visitExpr(ctx.as);
			out.print(", ");
			
			if(!asType.canCast(varType))
				errorList.add(new SemanticError(ctx.as.start.getLine(), ctx.as.start.getCharPositionInLine(), 
						String.format("Cannot convert %s to %s", asType.id, varType.id)));
		}
		
		VSOPType inType = visitExpr(ctx.ex);
		out.print(")");
		
		return inType;
	}
	
	@Override
	public VSOPType visitOi(VSOPParser.OiContext ctx) {
		String id = ctx.id.getText();
		
		VSOPType type = (vars.isEmpty()) ? null : vars.peek().get(id);
		
		if(type == null) {
			VSOPField f = currentClass.fields.get(id);
			if(f == null)
				errorList.add(new SemanticError(ctx.id.getLine(), ctx.id.getChannel(), 
						String.format("Variable %s was not declared in this scope.", id)));
			else {
				type = f.type;
			}
		}
			
		out.print(id);
		
		return (type != null) ? type : UNIT;
	}
	
	@Override
	public VSOPType visitIf(VSOPParser.IfContext ctx) {
		out.print("If(");
		
		VSOPType type0 = visitExpr(ctx.expr(0));
		if(type0 != BOOL) {
			errorList.add(new SemanticError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
					String.format("Condition must be of type BOOL but got %s", type0.id)));
		}
		
		out.print(", ");
		
		VSOPType type1 = visitExpr(ctx.expr(1));
		VSOPType type2;
		if(ctx.expr(2) != null) {
			out.print(", ");
			type2 = visitExpr(ctx.expr(2));
		} else {
			type2 = UNIT;
		}
		
		out.print(")");
			
		if(type1 instanceof VSOPClass && type2 instanceof VSOPClass) {
			return VSOPClass.commonAncestor((VSOPClass) type1, (VSOPClass) type2);
		} else if(type1 == UNIT || type2 == UNIT) {
			return UNIT;
		} else if(type1==type2) {
			return type1;
		} else {
			errorList.add(new SemanticError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), 
					String.format("Type %s and %s does not match", type1.id, type2.id)));
			return UNIT;
		}
	}
	
	@Override
	public VSOPType visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		return visitExpr(ctx.expr());
	}
	
	@Override
	public VSOPType visitBinop(VSOPParser.BinopContext ctx) {
		String op = ctx.op.getText();
		
		out.printf("BinOp(%s, ",op);
		VSOPType type1 = visitExpr(ctx.expr(0));
		out.print(", ");
		VSOPType type2 = visitExpr(ctx.expr(1));
		out.print(")");
		
		VSOPBinOp binOp = binOpMap.get(op);
		if(binOp == null) {
			throw new RuntimeException("Operator not found");
		}
		
		if(binOp == EQ) {
			if(type1 instanceof VSOPClass && !(type2 instanceof VSOPClass) ||
					!(type1 instanceof VSOPClass) && type2 instanceof VSOPClass) {
				errorList.add(new SemanticError(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
						"Trying to compare primitive type with class type"));
			} else if(!(type1 instanceof VSOPClass) && !(type2 instanceof VSOPClass) &&
					type1 != type2) {
				errorList.add(new SemanticError(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
						"Trying to compare primitive types that does not match"));
			}
		} else {
			if(type1!=binOp.opType) {
				errorList.add(new SemanticError(ctx.expr(0).start.getLine(), ctx.expr(0).start.getCharPositionInLine(),
						String.format("Expected %s but got %s type for operator %s", binOp.opType.id, type1.id, binOp.id)));
			}
			if(type2!=binOp.opType) {
				errorList.add(new SemanticError(ctx.expr(1).start.getLine(), ctx.expr(1).start.getCharPositionInLine(),
						String.format("Expected %s but got %s type for operator %s", binOp.opType.id, type2.id, binOp.id)));
			}
		}
		
		return binOp.retType;
	}
}
