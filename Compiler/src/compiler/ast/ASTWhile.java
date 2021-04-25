package compiler.ast;

import java.io.PrintStream;

import static compiler.vsop.VSOPConstants.UNIT;

public class ASTWhile extends ASTExpr {

    ASTExpr cond;
    ASTExpr loop;

    public ASTWhile(
            ASTExpr cond,
            ASTExpr loop
        )
    {
        super(UNIT);
        this.cond = cond;
        this.loop = loop;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print("While(");
        cond.print(pStream);
		pStream.print(", ");
        loop.print(pStream);
		pStream.printf("):%s", UNIT.id);
    }
    
}