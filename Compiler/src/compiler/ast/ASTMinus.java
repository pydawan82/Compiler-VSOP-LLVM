package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.LLVMConstants;
import compiler.llvm.LLVMFormatter;

import static compiler.vsop.VSOPConstants.INT32;
import static compiler.llvm.LLVMFormatter.*;

public class ASTMinus extends ASTExpr {

    ASTExpr expr;

    public ASTMinus(ASTExpr expr) {
        super(INT32);
        this.expr = expr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String instr = expr.emitLLVM(ctx);
        String lastVar = ctx.getLastValue();

        String minus = op("sub", LLVMConstants.INT32, "0", lastVar);
        
        return String.join(System.lineSeparator(), instr, minus);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("UnOp(-, ");
        expr.print(pStream, indent+1);
        pStream.printf("):%s", type.id);
    }

}