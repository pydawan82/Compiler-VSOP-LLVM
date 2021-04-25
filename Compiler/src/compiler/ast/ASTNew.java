package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTNew extends ASTExpr {
    String id;
    
    public ASTNew(
            ParserRuleContext context,
            VSOPType type
        )
    {
        super(context, type);
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.printf("New(%s)", id);
		pStream.printf(":%s", type.id);
    }
    
}