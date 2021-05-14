package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.LLVMConstants;

import static compiler.vsop.VSOPConstants.BOOL;
import static compiler.llvm.LLVMFormatter.*;

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
    public String emitLLVM(Context ctx) {
        String instr = expr.emitLLVM(ctx);
        String ord = ctx.getLastValue();

        String minus = op("xor", LLVMConstants.BOOL, "1", ord);
        
        return String.join(System.lineSeparator(), instr, minus);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("UnOp(not, ");
		expr.print(pStream, indent+1);
		pStream.printf("):%s", BOOL.id);
    }
    
}
