package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTType extends ASTNode {

    VSOPType type;

    public ASTType(
            VSOPType type
        )
    {
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
