package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
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
    public String emitLLVM(Context ctx) {
        String format = "";
        
        return String.format(format);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("New(%s)", id);
		pStream.printf(":%s", type.id);
    }
    
}