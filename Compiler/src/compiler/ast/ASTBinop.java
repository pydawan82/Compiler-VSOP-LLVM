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
        String left = var(ctx.getLastValue());

        String llvmRight = leftExpr.emitLLVM(ctx);
        String right = var(ctx.getLastValue());
        
        String op = op(LLVMConstants.binOp(operator), Generator.toLLVMType(operator.retType), var(left), var(right));
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