import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CustomVisitor<T> implements VSOPParserVisitor<T> {
	
	
	
	@Override
	public T visit(ParseTree arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitProgram(VSOPParser.ProgramContext ctx) {
		
		return null;
	}

	@Override
	public T visitClazz(VSOPParser.ClazzContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitClassBody(VSOPParser.ClassBodyContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitField(VSOPParser.FieldContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitMethod(VSOPParser.MethodContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitType(VSOPParser.TypeContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitFormals(VSOPParser.FormalsContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitFormal(VSOPParser.FormalContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitBlock(VSOPParser.BlockContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitExpr(VSOPParser.ExprContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitArgs(VSOPParser.ArgsContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitLiteral(VSOPParser.LiteralContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
