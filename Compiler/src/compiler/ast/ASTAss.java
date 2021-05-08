package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

public class ASTAss extends ASTExpr {
    String id;
    ASTExpr value;
    VSOPType type;

    public ASTAss(
            VSOPType type,
            ASTExpr value
        ) 
    {
        super(type);
        this.value = value;
    }

    @Override
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("Assign(%s, ", id);
		value.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}