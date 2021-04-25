package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTMinus extends ASTExpr {

    ASTExpr expr;

    public ASTMinus(
            VSOPType type,
            ASTExpr expr
        )
    {
        super(type);
        this.expr = expr;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("UnOp(-, ");
		expr.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}