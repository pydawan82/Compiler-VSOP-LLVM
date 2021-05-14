package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
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
    public String emitLLVM(Context ctx) {
        ctx.push(id);
        
        return "";
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("%s:%s", id, type.id);
    }
    
}