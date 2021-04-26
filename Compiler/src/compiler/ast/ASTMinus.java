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
    public void emitLLVM(PrintStream pStream, Context ctx) {
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("UnOp(-, ");
        expr.print(pStream, indent+1);
        pStream.printf("):%s", type.id);
    }

}