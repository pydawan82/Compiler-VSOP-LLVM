package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;
import static compiler.llvm.LLVMConstants.*;

public class ASTNew extends ASTExpr {
    String id;
    
    public ASTNew(
            VSOPType type
        )
    {
        super(type);
    }

    @Override
    public String emitLLVM(Context ctx) {
        String classType = Generator.toRawLLVMType(type);
        int sizePtr = ctx.unnamed();
        String size = assign(sizePtr, getSize(classType));

        int sizeInt = ctx.unnamed();
        String sizeI = assign(sizeInt, ptrtoint(pointerOf(classType), var(sizePtr), integer(64)));
       
        int rawObjPtr = ctx.unnamed();
        String malloc = assign(rawObjPtr, malloc(integer(64)+" "+var(sizeInt)));
       
        int objPtr = ctx.unnamed();
        String cast = assign(objPtr, bitcast(pointerOf(integer(8)), var(rawObjPtr), pointerOf(classType)));

        int vtablePtr = ctx.unnamed();
        String getVTable = assign(vtablePtr, GET(classType, var(objPtr), 0));
        String store = store(type(vTableType(type.id)), global(vTableName(type.id)), var(vtablePtr));
        
        ctx.setLastValue(objPtr);

        return String.join(System.lineSeparator(), 
                size,
                sizeI,
                malloc,
                cast,
                getVTable,
                store
            );
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("New(%s)", id);
		pStream.printf(":%s", type.id);
    }
    
}