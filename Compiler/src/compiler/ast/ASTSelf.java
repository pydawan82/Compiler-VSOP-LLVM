package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPClass;

public class ASTSelf extends ASTExpr {

    public ASTSelf(VSOPClass vsopClass) {
        super(vsopClass);
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.printf("self:%s", type.id);
    }
    
}