import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.antlr.v4.runtime.ParserRuleContext;

public class CustomVisitor implements VSOPParserVisitor<Void> {
	
	PrintStream out = System.out;
	
	private Map<Class<?>, Function<ParserRuleContext, Void>> map = new HashMap<Class<?>, Function<ParserRuleContext, Void>>();
	
	String defaultClazz = "Object";
	int tab = -1;
	String tabs = "\t";
	
	public CustomVisitor() {

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
	}
	
	private void printTab() {
		for(int i=0; i<tab; i++) {
			out.print(tabs);
		}
	}
	
	@Override
	public Void visit(ParseTree arg0) {
		VSOPParser.ProgramContext ctx = (VSOPParser.ProgramContext)arg0;
		visitProgram(ctx);
		
		return null;
	}

	@Override
	public Void visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
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
		tab++;
		
		out.printf("Class(%s, %s, ", ctx.id.getText(), ctx.idext!=null ? ctx.idext.getText() : defaultClazz);
		
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
		
		out.printf("Field(%s, %s", ctx.id.getText(), ctx.type().getText());
		
		if(ctx.expr() != null) {
			out.print(", ");
			visitExpr(ctx.expr());
		}
		
		out.print(')');
		tab--;
		return null;
	}

	@Override
	public Void visitMethod(VSOPParser.MethodContext ctx) {
		tab++;
		
		out.printf("Method(%s, ", ctx.id.getText());
		
		visitFormals(ctx.formals());
		
		out.print(", ");
		
		visitType(ctx.type());
		out.print(", ");
		
		visitBlock(ctx.block());
		
		out.print(')');
		
		tab--;
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
	public Void visitBlock(VSOPParser.BlockContext ctx) {
		tab++;
		
		out.print('[');
		
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
		
		out.print(']');
		
		tab--;
		return null;
	}

	public Void visitExpr(VSOPParser.ExprContext ctx) {
		map.get(ctx.getClass()).apply(ctx);
		return null;
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
	public Void visitLiteral(VSOPParser.LiteralContext ctx) {
		out.print(ctx.getText());
		return null;
	}

	@Override
	public Void visitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx) {
		out.print(ctx.getText());
		return null;
	}

	@Override
	public Void visitType(VSOPParser.TypeContext ctx) {
		out.print(ctx.getText());
		return null;
	}

	@Override
	public Void visitAss(VSOPParser.AssContext ctx) {
		out.print("Assign(" + ctx.id.getText() + ", " + ctx.expr().getText() + ")");
		return null;
	}
	
	@Override
	public Void visitNew(VSOPParser.NewContext ctx) {
		out.print("New(" + ctx.id.getText() + ")");
		return null;
	}
	
	@Override
	public Void visitBl(VSOPParser.BlContext ctx) {
		visitBlock(ctx.block());
		return null;
	}
	
	@Override
	public Void visitWhile(VSOPParser.WhileContext ctx) {
		out.print("While(" + visitExpr(ctx.expr(1)) + ", " + visitExpr(ctx.expr(2)) + ")");
		return null;
	}
	
	@Override
	public Void visitNot(VSOPParser.NotContext ctx) {
		out.print("UnOp(not, " + visitExpr(ctx.expr()) + ")");
		return null;
	}

	@Override
	public Void visitMinus(VSOPParser.MinusContext ctx) {
		out.print("UnOp(-, " + visitExpr(ctx.expr()) + ")");
		return null;
	}

	@Override
	public Void visitIsnull(VSOPParser.IsnullContext ctx) {
		out.print("UnOp(isnull, " + visitExpr(ctx.expr()) + ")");
		return null;
	}

	@Override
	public Void visitSelfcall(VSOPParser.SelfcallContext ctx) {
		out.print("Call(self, " + ctx.id.getText() + ", ");
		
		visitArgs(ctx.args());
		
		out.print(')');
		
		return null;
	}

	@Override
	public Void visitCall(VSOPParser.CallContext ctx) {
		out.print("Call(");
		
		visitExpr(ctx.expr());
		
		out.print(", " + ctx.id.getText() + ", ");
		
		visitArgs(ctx.args());
		
		out.print(')');
		
		return null;
	}
	
	@Override
	public Void visitUnit(VSOPParser.UnitContext ctx) {
		out.print("()");
		return null;
	}
	
	@Override
	public Void visitLit(VSOPParser.LitContext ctx) {
		visitLiteral(ctx.literal());
		return null;
	}
	
	@Override
	public Void visitSelf(VSOPParser.SelfContext ctx) {
		out.print("self");
		return null;
	}
	
	@Override
	public Void visitLet(VSOPParser.LetContext ctx) {
		out.print("Let(" + ctx.id.getText() + ", " +ctx.type().getText() + ", ");
		if(ctx.as != null){
			visitExpr(ctx.as);
			out.print(", ");
		}
		visitExpr(ctx.ex);
		out.print(")");
		return null;
	}
	
	@Override
	public Void visitOi(VSOPParser.OiContext ctx) {
		out.print(ctx.id.getText());
		return null;
	}
	
	@Override
	public Void visitIf(VSOPParser.IfContext ctx) {
		out.print("If("+visitExpr(ctx.expr(0)) + ", " + visitExpr(ctx.expr(1)));
		if(ctx.expr().size() == 3){
			out.print(", " + visitExpr(ctx.expr(2)));
		}
		out.print(")");
		return null;
	}
	
	@Override
	public Void visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		visitExpr(ctx.expr());
		return null;
	}
	
	@Override
	public Void visitBinop(VSOPParser.BinopContext ctx) {
		out.print("BinOp("+ctx.op.getText()+", ");
		visitExpr(ctx.expr(0));
		out.print(", ");
		visitExpr(ctx.expr(1));
		out.print(")");
		
		return null;
	}
}
