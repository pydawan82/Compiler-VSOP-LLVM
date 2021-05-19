package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPClass;

public class ASTSelf extends ASTExpr {

    public ASTSelf(VSOPClass vsopClass) {
        super(vsopClass);
    }

    @Override
    public String emitLLVM(Context ctx) {
        ctx.setLastValue(ctx.valueOf("self"));
        
        return "";
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.printf("self:%s", type.id);
    }
    
}