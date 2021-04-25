package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTNew extends ASTExpr {
    String id;
    
    public ASTNew(
            VSOPType type
        )
    {
        super(type);
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