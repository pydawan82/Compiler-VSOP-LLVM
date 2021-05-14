package compiler.ast;

import static compiler.llvm.LLVMFormatter.*;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.llvm.LLVMConstants;
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
        String llvmLeft = leftExpr.emitLLVM(ctx);
        String left = ctx.getLastValue();

        String llvmRight = rightExpr.emitLLVM(ctx);
        String right = ctx.getLastValue();
        
        String op = op(LLVMConstants.binOp(operator), Generator.toLLVMType(operator.opType), left, right);
        String operation = assign(var(ctx.unnamed()), op);

        return String.join(System.lineSeparator(), llvmLeft, llvmRight, operation);
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