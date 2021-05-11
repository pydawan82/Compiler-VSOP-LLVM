package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

public class ASTLiteral extends ASTExpr {
    
    String value;
    
    public ASTLiteral(
        VSOPType type,
        String value
    ) {
        super(type);
        this.value = value;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String format = "";
        
        return String.format(format);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print(value);
		pStream.printf(":%s", type.id);
    }
    
}