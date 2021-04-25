package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPMethod;
import compiler.vsop.VSOPType;

public class ASTCall extends ASTExpr {
    
    VSOPMethod vsopMethod;
    ASTExpr expr;
    List<ASTExpr> args;

    public ASTCall(
            ParserRuleContext context,
            VSOPMethod vsopMethod,
            ASTExpr expr,
            List<ASTExpr> args
        )
    {
        super(context, vsopMethod.ret);
        this.vsopMethod = vsopMethod;
        this.expr = expr;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.print("Call(");
		expr.print(pStream);
		pStream.printf(", %s, ", vsopMethod.id);

		pStream.print("[");
		int i = 0;
		for (var arg : args) {
			pStream.println();
			arg.print(pStream);
			
            i++;

			if (i != args.size()) {
				pStream.print(",");
			}
		}

		if (args.size() != 0) {
			pStream.println();
		}
		pStream.print("]");

		pStream.printf("):%s", vsopMethod.ret.id);
    }
    
}