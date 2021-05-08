package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
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
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.printf("%s: %s",id, type.id);
    }
    
}