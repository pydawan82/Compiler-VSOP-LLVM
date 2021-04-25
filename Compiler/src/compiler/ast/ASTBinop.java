package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPBinOp;

public class ASTBinop extends ASTExpr {

    VSOPBinOp operator;

    ASTExpr leftExpr;
    ASTExpr rightExpr;
    
    public ASTBinop(
            VSOPBinOp operator,
            ASTExpr leftExpr,
            ASTExpr rightExpr
        )
    {
        super(operator.opType);
        this.operator = operator;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {

		pStream.printf("BinOp(%s, ", operator.id);
		leftExpr.print(pStream);
		pStream.print(", ");
		rightExpr.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}