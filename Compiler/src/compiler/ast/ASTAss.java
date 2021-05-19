package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

public class ASTAss extends ASTExpr {
    String id;
    ASTExpr value;

    public ASTAss(
            VSOPType type,
            String id,
            ASTExpr value
        ) 
    {
        super(type);
        this.id = id;
        this.value = value;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String valueInstr = value.emitLLVM(ctx);
        String val = ctx.getLastValue();
        
        if(ctx.valueOf(id) != null) {
            ctx.setValueOf(id, val);
            return valueInstr;
        } else {
            String classType = Generator.toRawLLVMType(ctx.method.getParent());
            String self = ctx.valueOf("self");
            int idx = ctx.ordinalOfField(id);
            int ptr = ctx.unnamed();
            String get = assign(ptr, GET(classType, self, idx));

            String varType = Generator.toLLVMType(type);
            String store = store(varType, val, var(ptr));

            ctx.setLastValue(val);

            return String.join(System.lineSeparator(), get, store);
        }
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("Assign(%s, ", id);
		value.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}