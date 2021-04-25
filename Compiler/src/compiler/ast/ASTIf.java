package compiler.ast;

import java.io.PrintStream;

import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTIf extends ASTExpr {

    ASTExpr condExpr;
    ASTExpr thenExpr;
    Optional<ASTExpr> elseExpr;

    public ASTIf(
            ParserRuleContext context,
            VSOPType type,
            ASTExpr condExpr,
            ASTExpr thenExpr,
            Optional<ASTExpr> elseExpr
        )
    {
        super(context, type);
        this.condExpr = condExpr;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("If(");
		condExpr.print(pStream);
		pStream.print(", ");
		thenExpr.print(pStream);

		if (elseExpr.isPresent()) {
			pStream.print(", ");
			elseExpr.get().print(pStream);
        }

		pStream.printf("):%s", type.id);
    }
    
}