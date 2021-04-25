package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

public class ASTFormal extends ASTNode {
    String id;
    ASTType type;
    
    public ASTFormal(
            ParserRuleContext context,
            ASTType type
        )
    {
        super(context);
        this.type = type;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print(id + " : ");
		type.print(pStream);
    }
    
}