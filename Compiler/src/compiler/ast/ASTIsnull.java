package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import static compiler.vsop.VSOPConstants.BOOL;

public class ASTIsnull extends ASTExpr {

    ASTExpr expr;

    public ASTIsnull(ParserRuleContext context, ASTExpr expr) {
        super(context, BOOL);
        this.expr = expr;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("UnOp(isnull, ");
		expr.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}