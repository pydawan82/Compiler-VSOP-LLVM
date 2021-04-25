package compiler.ast;

import java.io.PrintStream;

import static compiler.vsop.VSOPConstants.BOOL;

public class ASTIsnull extends ASTExpr {

    ASTExpr expr;

    public ASTIsnull(ASTExpr expr) {
        super(BOOL);
        this.expr = expr;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("UnOp(isnull, ");
		expr.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}