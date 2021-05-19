package compiler.llvm;

import static compiler.llvm.LLVMFormatter.*;

import compiler.vsop.VSOPClass;

import static compiler.llvm.LLVMConstants.*;

public final class LLVMUtil {
    private LLVMUtil() {}

    public static String mallocOf(Context ctx, VSOPClass type) {
        String classType = Generator.toRawLLVMType(type);
        int sizePtr = ctx.unnamed();
        String size = assign(sizePtr, getSize(classType));

        int sizeInt = ctx.unnamed();
        String sizeI = assign(sizeInt, ptrtoint(pointerOf(classType), var(sizePtr), integer(64)));
       
        int rawObjPtr = ctx.unnamed();
        String malloc = assign(rawObjPtr, malloc(integer(64)+" "+var(sizeInt)));
       
        int objPtr = ctx.unnamed();
        String cast = assign(objPtr, bitcast(pointerOf(integer(8)), var(rawObjPtr), pointerOf(classType)));

        return String.join(System.lineSeparator(), 
                size,
                sizeI,
                malloc,
                cast
            );
    }
}
