package compiler.ast;

import java.io.PrintStream;

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
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("UnOp(not, ");
		expr.print(pStream);
		pStream.printf("):%s", BOOL.id);
    }
    
}
