package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

import static compiler.vsop.VSOPConstants.BOOL;

public class ASTNot extends ASTExpr {
    ASTExpr expr;
    
    public ASTNot(
            ASTExpr expr
        )
    {
        super(BOOL);
        this.expr = expr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String format = "";
        
        return String.format(format);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("UnOp(not, ");
		expr.print(pStream, indent+1);
		pStream.printf("):%s", BOOL.id);
    }
    
}
