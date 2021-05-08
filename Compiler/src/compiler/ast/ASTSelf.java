package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPClass;

public class ASTSelf extends ASTExpr {

    public ASTSelf(VSOPClass vsopClass) {
        super(vsopClass);
    }

    @Override
    public void emitLLVM(PrintStream pStream, Context ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.printf("self:%s", type.id);
    }
    
}