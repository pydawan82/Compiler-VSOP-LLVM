package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.assign;

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
    public String emitLLVM(Context ctx) {
        String valueInstr = value.emitLLVM(ctx);
        String val = ctx.getLastValue();
        
        String ass = assign(ctx.unnamed(), val);
        
        return String.join(System.lineSeparator(), valueInstr, ass);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("Assign(%s, ", id);
		value.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}