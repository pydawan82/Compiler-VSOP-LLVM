package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTOi extends ASTExpr {

    String id;

    public ASTOi(
            ParserRuleContext context,
            VSOPType type,
            String id
        )
    {
        super(context, type);
        this.id = id;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.printf("%s:%s", id, type.id);
    }
    
}