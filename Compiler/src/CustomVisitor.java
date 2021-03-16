import java.io.PrintStream;
import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CustomVisitor implements VSOPParserVisitor<Void> {
	
	PrintStream out = System.out;
	
	String defaultClazz = "Object";
	int tab = -1;
	String tabs = "\t";
	
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
			visitField(field);
			if(i!=ctx.field().size()) {
				out.println(",");
			}
		}
		out.print(']');
		
		out.print(", ");
		
		out.print('[');
		i=0;
		for(var method: ctx.method()) {
			i++;
			visitMethod(method);
			if(i!=ctx.field().size()) {
				out.println(",");
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
		
		out.print(ctx.type().getText()+", ");
		
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
			visitFormal(formal);
			if(i != ctx.formal().size()) {
				out.println(",");
			}
		}
		
		out.print(']');
		
		return null;
	}

	@Override
	public Void visitFormal(VSOPParser.FormalContext ctx) {
		out.printf("%s : %s", ctx.id.getText(), ctx.type().getText());
		return null;
	}

	@Override
	public Void visitBlock(VSOPParser.BlockContext ctx) {
		Boolean notNull = false;
		if(ctx.ex2 != null){
			notNull = true;
			out.print('[');
		}
		visitExpr(ctx.ex1);
		if(notNull){
			for(var expr : ctx.ex2){
				out.print(", ");
				visitExpr(expr);
			}
			out.print(']');
		}
		return null;
	}

	public Void visitExpr(VSOPParser.ExprContext ctx) {
		//TODO trouver un moyen de switch case ou quoi les contexts pour aller dans les bons visit :)
		return null;
	}

	@Override
	public Void visitArgs(VSOPParser.ArgsContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitLiteral(VSOPParser.LiteralContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitType(VSOPParser.TypeContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitAss(VSOPParser.AssContext ctx) {
		out.print("Assign(" + ctx.id.getText() + ", " + ctx.ex.getText() + ")");
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
		out.print("While(" + visitExpr(ctx.ex1) + ", " + visitExpr(ctx.ex2) + ")");
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
		out.print("Call(self, " + ctx.id.getText() + ", [");
		for(var ex : ctx.args().ex){
			visitExpr(ex);
			out.print(", ");
		}
		out.print("]");
		return null;
	}

	@Override
	public Void visitCall(VSOPParser.CallContext ctx) {
		out.print("Call(" + visitExpr(ctx.expr()) + ", " + ctx.id.getText() + ", [");
		for(var ex : ctx.args().ex){
			visitExpr(ex);
			out.print(", ");
		}
		out.print("]");
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
		out.print("If(" visitExpr(ctx.ex.get(0)) + ", " + visitExpr(ctx.ex.get(1)));
		if(len(ctx.ex) == 3){
			out.print(", " + visitExpr(ctx.ex.get(2)));
		}
		out.print(")");
		return null;
	}
	
	@Override
	public Void visitBraceExpr(VSOPParser.BraceExprContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Void visitBinop(VSOPParser.BinopContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}
