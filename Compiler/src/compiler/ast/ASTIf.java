package compiler.ast;

import java.io.PrintStream;

import java.util.Optional;

import compiler.llvm.Context;
import compiler.util.PrintUtil;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

public class ASTIf extends ASTExpr {

    ASTExpr condExpr;
    ASTExpr thenExpr;
    Optional<ASTExpr> elseExpr;

    public ASTIf(
            VSOPType type,
            ASTExpr condExpr,
            ASTExpr thenExpr,
            Optional<ASTExpr> elseExpr
        )
    {
        super(type);
        this.condExpr = condExpr;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String condStr = condExpr.emitLLVM(ctx);
        String condVar = var(ctx.getLastValue());
        
        String branch = "";

        String then = thenExpr.emitLLVM(ctx);
        String thenVar = var(ctx.getLastValue());

        if(elseExpr.isPresent()) {
            int elseLabel = ctx.unnamed(); 
            String elze = elseExpr.get().emitLLVM(ctx);
            String elzeVar = var(ctx.getLastValue());
        }

        int label = ctx.unnamed();

        
        return String.join(System.lineSeparator(), condStr);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("If(");
		condExpr.print(pStream, indent+1);
		pStream.print(", ");
		thenExpr.print(pStream, indent+1);

		if (elseExpr.isPresent()) {
			pStream.print(", ");
			elseExpr.get().print(pStream, indent+1);
        }

		pStream.printf("):%s", type.id);
    }
    
}