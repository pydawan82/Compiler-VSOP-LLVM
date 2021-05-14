package compiler.llvm;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import compiler.ast.ASTExpr;
import compiler.ast.ASTProgram;
import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPMethod;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMConstants.*;
import static compiler.llvm.LLVMFormatter.*;

/**
 * A class used to generate LLVM source file from an {@link ASTProgram}
 */
public class Generator {

    /**
     * TODO Probably to be removed
     */
    private final ASTProgram ast;
    private final Map<String, VSOPClass> classMap;
    private final Map<VSOPMethod, ASTExpr> methods;
    private final PrintStream out;

    /**
     * Creates a new generator given a program tree and a map of defined classes.
     * @param ast - The program tree
     * @param classMap - The map of defined classes
     */
    public Generator(ASTProgram ast, Map<String, VSOPClass> classMap, Map<VSOPMethod, ASTExpr> methods, PrintStream out) {
        this.ast = Objects.requireNonNull(ast);
        this.classMap = Objects.requireNonNull(classMap);
        this.methods = Objects.requireNonNull(methods);
        this.out = Objects.requireNonNull(out);
    }

    /**
     * Emits LLVM source code to a given {@link PrintStream}
     * @param out - The {@link PrintStream} to be written
     */
    public void emitLLVM() {
        Context ctx = new Context(classMap, new HashMap<>());

        out.println(section("Classes"));
        defineClasses();

        out.println(section("VTables"));
        declareVTables();

        out.println(section("Functions"));
        declareFunctions(ctx);
    }

    private void defineClasses() {
        classMap.values().stream()
                .filter(c -> c != VSOPConstants.OBJECT)
                .forEach(this::defineClass);
    }

    private void defineClass(VSOPClass clazz) {
        out.println(comment("Class "+clazz.id));

        String classType = classId(clazz.id);
        String vTableName = vTableType(clazz.id);

        List<String> types = clazz.fields.values().stream()
                .map(f -> f.type)
                .map(Generator::toLLVMType)
                .collect(Collectors.toList());

        types.add(0, pointerOf(type(vTableName)));

        String typeDef = defStruct(classType, types);
        out.println(typeDef);

        List<String> args = clazz.methods.values().stream()
                .map(this::toLLVMType)
                .toList();

        String vTableDef = defStruct(vTableName, args);
        out.println(vTableDef);
        out.println();
    }

    public static String toLLVMType(VSOPType type) {
        if(type instanceof VSOPClass clazz)
            return pointerOf(type(classId(clazz.id)));
        
        if(type == VSOPConstants.BOOL)
            return BOOL;
        
        if(type == VSOPConstants.INT32)
            return INT32;

        if(type == VSOPConstants.STRING)
            return STRING;

        throw new CompilationException("Unhandled type case");
    }

    private String toLLVMType(VSOPMethod method) {
        String returnType = toLLVMType(method.returnType);
        List<String> args = argsToLLVMType(method);

        return function(returnType, args);
    }

    private List<String> argsToLLVMType(VSOPMethod method) {
        List<String> args = method.args.stream()
                .map(f -> f.type)
                .map(Generator::toLLVMType)
                .collect(Collectors.toList());
                
        args.add(0, toLLVMType(method.getParent()));

        return args;
    }

    private void declareVTables() {
        classMap.values().stream()
                .filter(c -> c != VSOPConstants.OBJECT)
                .forEach(this::declareVTable);
    }

    private void declareVTable(VSOPClass clazz) {
        
        List<String> functions = clazz.methods.values().stream()
                .map(m -> toLLVMType(m)+" "+ global(functionId(m.getParent().id, m.id)))
                .toList();
        
        String table = arrayOf(functions);
        String def = defConstant(global(vTableName(clazz.id)), type(vTableType(clazz.id)), table);

        out.println(def);
    }

    private void declareFunctions(Context ctx) {
    class_loop:
        for(VSOPClass parent: classMap.values()) {
            for(VSOPMethod method: parent.methods.values()) {

                if(method.getParent() == VSOPConstants.OBJECT)
                    continue class_loop;

                ASTExpr body = methods.get(method);
                declareFunction(ctx, method, body);
            }
        }
    }
    
    private void declareFunction(Context ctx, VSOPMethod method, ASTExpr body) {
        String returnType = toLLVMType(method.returnType);
        String id = functionId(method.getParent().id, method.id);
        List<String> args = argsToLLVMType(method);
        String bodyStr = indentBlock(body.emitLLVM(ctx));

        String def = defFunction(returnType, id, args, bodyStr);
        out.println(def);
    }

    private String labelPattern = "[0-9]+:\\s*";
    private String indentBlock(String block) {
        return block.lines()
                .map(l -> l.matches(labelPattern) ? l : '\t'+l)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
