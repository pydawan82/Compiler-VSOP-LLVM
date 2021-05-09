package compiler.llvm;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import compiler.ast.ASTProgram;
import compiler.vsop.VSOPClass;

/**
 * A class used to generate LLVM source file from an {@link ASTProgram}
 */
public class Generator {

    private final ASTProgram ast;
    private final Map<String, VSOPClass> classMap;
    
    /**
     * Creates a new generator given a program tree and a map of defined classes.
     * @param ast - The program tree
     * @param classMap - The map of defined classes
     */
    public Generator(ASTProgram ast, Map<String, VSOPClass> classMap) {
        this.ast = Objects.requireNonNull(ast);
        this.classMap = Objects.requireNonNull(classMap);
    }

    /**
     * Emits LLVM source code to a given {@link PrintStream}
     * @param out - The {@link PrintStream} to be written
     */
    public void emitLLVM(PrintStream out) {
        Context ctx = new Context(classMap, new HashMap<>());

        defineStructures(out, ctx);

        ast.emitLLVM(out, ctx);
    }

    private void defineStructures(PrintStream out, Context ctx) {
    }
}
