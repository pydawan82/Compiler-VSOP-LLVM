package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

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
        if(ctx.push(id)) return "";
        
        String type = Generator.toLLVMType(ctx.method.getParent()).split("\\*")[0];
        String self = var(ctx.ordinalOf("self"));
        String varType = Generator.toLLVMType(this.type);
        int idx = ctx.ordinalOfField(id);
        
        int ord = ctx.unnamed();

        return assign(ord, GET(type, self, varType, idx));
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("%s:%s", id, type.id);
    }
    
}