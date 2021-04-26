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
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("%s:%s", id, type.id);
    }
    
}