package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.vsop.VSOPMethod;

public class ASTCall extends ASTExpr {
    
    VSOPMethod vsopMethod;
    ASTExpr object;
    List<ASTExpr> args;

    public ASTCall(
            VSOPMethod vsopMethod,
            ASTExpr object,
            List<ASTExpr> args
        )
    {
        super(vsopMethod.ret);
        this.vsopMethod = vsopMethod;
        this.object = object;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.print("Call(");
		object.print(pStream);
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