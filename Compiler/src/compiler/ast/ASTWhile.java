package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

import static compiler.vsop.VSOPConstants.UNIT;

public class ASTWhile extends ASTExpr {

    ASTExpr cond;
    ASTExpr loop;

    public ASTWhile(
            ParserRuleContext context,
            VSOPType type,
            ASTExpr cond,
            ASTExpr loop
        )
    {
        super(context, type);
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