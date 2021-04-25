package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTType extends ASTNode {

    VSOPType type;

    public ASTType(
            ParserRuleContext context,
            VSOPType type
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
        pStream.print(type.id);
    }
    
}
