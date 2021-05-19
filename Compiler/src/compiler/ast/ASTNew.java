package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

public class ASTNew extends ASTExpr {
    
    public ASTNew(
            VSOPType type
        )
    {
        super(type);
    }

    @Override
    public String emitLLVM(Context ctx) {
        int objPtr = ctx.unnamed();
        String init = assign(objPtr, call(Generator.toLLVMType(type), global(initId(type.id)), List.of()));
        ctx.setLastValue(objPtr);
        return init;
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("New(%s)", type.id);
		pStream.printf(":%s", type.id);
    }
    
}