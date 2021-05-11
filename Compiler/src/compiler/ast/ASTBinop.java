package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
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
        super(operator.retType);
        this.operator = operator;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String format = "";
        
        return String.format(format);
    }

    @Override
    public void print(PrintStream pStream, int indent) {

		pStream.printf("BinOp(%s, ", operator.id);
		leftExpr.print(pStream, indent+1);
		pStream.print(", ");
		rightExpr.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}