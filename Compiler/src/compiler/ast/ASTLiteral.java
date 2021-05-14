package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.llvm.LLVMConstants;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;
import static compiler.vsop.VSOPConstants.*;

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
        if(type == STRING)
            val = LLVMConstants.stringLiteral(value);
        else
            val = value;

        ctx.setLastValue(val);
        return "";
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print(value);
		pStream.printf(":%s", type.id);
    }
    
}