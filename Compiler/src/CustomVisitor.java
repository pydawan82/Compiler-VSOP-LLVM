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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitExpr(VSOPParser.ExprContext ctx) {
		// TODO Auto-generated method stub
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

}
