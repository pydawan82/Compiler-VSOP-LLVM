package compiler.ast;

import java.io.PrintStream;

import java.util.Optional;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

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
        String format = "";
        
        return String.format(format);
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