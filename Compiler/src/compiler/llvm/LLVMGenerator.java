package compiler.llvm;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import compiler.ast.ASTProgram;
import compiler.vsop.VSOPClass;

public class LLVMGenerator {

    private final ASTProgram ast;
    private final Map<String, VSOPClass> classMap;
    
    public LLVMGenerator(ASTProgram ast, Map<String, VSOPClass> classMap) {
        this.ast = ast;
        this.classMap = classMap;
    }

    public void emitLLVM(PrintStream pStream) {
        Context ctx = new Context(classMap, new HashMap<>());
        ast.emitLLVM(pStream, ctx);
    }
}
