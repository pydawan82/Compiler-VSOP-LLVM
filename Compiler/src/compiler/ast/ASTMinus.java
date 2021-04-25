package compiler.ast;

import java.io.PrintStream;

import static compiler.vsop.VSOPConstants.INT32;

public class ASTMinus extends ASTExpr {

    ASTExpr expr;

    public ASTMinus(ASTExpr expr) {
        super(INT32);
        this.expr = expr;
    }

    @Override
    public void visit() {

    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("UnOp(-, ");
        expr.print(pStream);
        pStream.printf("):%s", type.id);
    }

}