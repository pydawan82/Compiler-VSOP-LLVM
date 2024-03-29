package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

import static compiler.vsop.VSOPConstants.*;
import static compiler.llvm.LLVMFormatter.*;

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
        String val;
        if(type == STRING) {
            var pair = ctx.declareConstString(value);
            val = getString(pair.first(), pair.second());
        } else {
            val = value;
        }
        ctx.setLastValue(val);
        return "";
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print(value);
		pStream.printf(":%s", type.id);
    }
    
}