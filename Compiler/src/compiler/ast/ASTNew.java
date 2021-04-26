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
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("New(%s)", id);
		pStream.printf(":%s", type.id);
    }
    
}