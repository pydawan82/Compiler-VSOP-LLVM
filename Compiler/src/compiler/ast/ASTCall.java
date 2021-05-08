package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.llvm.Context;
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
        this.args = args;
    }

    @Override
    public void emitLLVM(PrintStream pStream, Context ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.print("Call(");
		object.print(pStream, indent+1);
		pStream.printf(", %s, ", vsopMethod.id);

		pStream.print("[");
		int i = 0;
		for (var arg : args) {
			pStream.println();
            indent(pStream, indent);
			arg.print(pStream, indent+1);
			
            i++;

			if (i != args.size()) {
				pStream.print(",");
			}
		}

		if (args.size() != 0) {
			pStream.println();
            indent(pStream, indent);
		}
		pStream.print("]");

		pStream.printf("):%s", vsopMethod.ret.id);
    }
    
}