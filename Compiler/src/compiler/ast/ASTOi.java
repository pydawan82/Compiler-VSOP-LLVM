package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTOi extends ASTExpr {

    String id;

    public ASTOi(
            VSOPType type,
            String id
        )
    {
        super(type);
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