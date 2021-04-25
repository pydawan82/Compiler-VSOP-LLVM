package compiler.ast;

import java.io.PrintStream;


public class ASTFormal extends ASTNode {
    String id;
    ASTType type;
    
    public ASTFormal(
            ASTType type
        )
    {
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