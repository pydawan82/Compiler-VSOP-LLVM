package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

import static compiler.vsop.VSOPConstants.BOOL;

import static compiler.llvm.LLVMFormatter.*;

public class ASTIsnull extends ASTExpr {

    ASTExpr expr;

    public ASTIsnull(ASTExpr expr) {
        super(BOOL);
        this.expr = expr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String exprStr = expr.emitLLVM(ctx);
        String value = ctx.getLastValue();
        
        String isnull = assign(ctx.unnamed(), icmp("eq", "i64", value, "0"));

        return String.join(System.lineSeparator(), exprStr, isnull);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("UnOp(isnull, ");
		expr.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}