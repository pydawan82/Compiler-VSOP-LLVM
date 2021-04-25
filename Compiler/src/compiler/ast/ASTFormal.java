package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;


public class ASTFormal extends ASTNode {
    String id;
    VSOPType type;
    
    public ASTFormal(
            String id,
            VSOPType type
        )
    {
        this.type = type;
        this.id = id;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.printf("%s: %s",id, type);
    }
    
}