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
        
        String classType = Generator.toRawLLVMType(ctx.method.getParent());
        String self = ctx.valueOf("self");
        String varType = Generator.toLLVMType(this.type);
        int idx = ctx.ordinalOfField(id);
        int ord = ctx.unnamed();

        String get = assign(ord, GET(classType, self, idx));
        String load = assign(ctx.unnamed(), load(varType, var(ord)));

        return String.join(System.lineSeparator(), get, load);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("%s:%s", id, type.id);
    }
    
}