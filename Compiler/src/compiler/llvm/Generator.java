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
                .map(this::toLLVMType)
                .collect(Collectors.toList());

        types.add(0, pointerOf(vTableName));

        String typeDef = defStruct(classType, types);
        out.println(typeDef);

        List<String> args = clazz.functions.values().stream()
                .map(m -> toLLVMType(m, clazz))
                .toList();

        String vTableDef = defStruct(vTableName, args);
        out.println(vTableDef);
        out.println();
    }

    private String toLLVMType(VSOPType type) {
        if(type instanceof VSOPClass clazz)
            return pointerOf(classId(clazz.id));
        
        if(type == VSOPConstants.BOOL)
            return BOOL;
        
        if(type == VSOPConstants.INT32)
            return INT32;

        if(type == VSOPConstants.STRING)
            return STRING;

        throw new CompilationException("Unhandled type case");
    }

    private String toLLVMType(VSOPMethod method, VSOPClass parent) {
        String returnType = toLLVMType(method.ret);
        List<String> args = argsToLLVMType(method, parent);

        return function(returnType, args);
    }

    private List<String> argsToLLVMType(VSOPMethod method, VSOPClass parent) {
        List<String> args = method.args.stream()
                .map(f -> f.type)
                .map(this::toLLVMType)
                .collect(Collectors.toList());
                
        args.add(0, toLLVMType(parent));

        return args;
    }

    private void declareVTables() {
        classMap.values().stream()
                .filter(c -> c != VSOPConstants.OBJECT)
                .forEach(this::declareVTable);
    }

    private void declareVTable(VSOPClass clazz) {
        
        List<String> functions = clazz.functions.values().stream()
                .map(m -> toLLVMType(m, clazz)+" "+ functionId(clazz.id, m.id))
                .toList();
        
        String table = arrayOf(functions);
        String def = defConstant(var(vTableName(clazz.id)), vTableType(clazz.id), table);

        out.println(def);
    }

    private void declareFunctions(Context ctx) {
        class_loop:
        for(VSOPClass parent: classMap.values()) {
            for(VSOPMethod method: parent.functions.values()) {

                if(VSOPConstants.OBJECT.functions.containsValue(method))
                    continue class_loop;

                ASTExpr body = methods.get(method);
                declareFunction(ctx, method, parent, body);
            }
        }
    }
    
    private void declareFunction(Context ctx, VSOPMethod method, VSOPClass parent, ASTExpr body) {
        String returnType = toLLVMType(method.ret);
        String id = functionId(parent.id, method.id);
        List<String> args = argsToLLVMType(method, parent);
        String bodyStr = body.emitLLVM(ctx);

        String def = defFunction(returnType, id, args, bodyStr);
        out.println(def);
    }
}
