package compiler.llvm;

import java.io.PrintStream;
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

        out.println(section("Classes"));
        defineClasses();

        out.println(section("VTables"));
        declareVTables();

        out.println(section("Functions"));
        declareFunctions();
    }

    private void defineClasses() {
        classMap.values().stream()
                .forEach(this::defineClass);
    }

    private void defineClass(VSOPClass clazz) {
        out.println(comment("Class "+clazz.id));

        String classType = classId(clazz.id);
        String vTableName = vTableType(clazz.id);

        List<String> types = clazz.fieldList().stream()
                .map(f -> f.type)
                .map(Generator::toLLVMType)
                .collect(Collectors.toList());

        types.add(0, pointerOf(type(vTableName)));

        String typeDef = defStruct(classType, types);
        out.println(typeDef);

        List<String> args = clazz.methodList().stream()
                .map(Generator::toLLVMType)
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

    public static String toRawLLVMType(VSOPType type) {
        if(type instanceof VSOPClass clazz)
            return type(classId(clazz.id));
        
        if(type == VSOPConstants.BOOL)
            return BOOL;
        
        if(type == VSOPConstants.INT32)
            return INT32;

        if(type == VSOPConstants.STRING)
            return STRING;

        throw new CompilationException("Unhandled type case");
    }

    public static String toLLVMType(VSOPMethod method) {
        String returnType = toLLVMType(method.returnType);
        List<String> args = argsToLLVMType(method);

        return function(returnType, args);
    }

    private static List<String> argsToLLVMType(VSOPMethod method) {
        List<String> args = method.args.stream()
                .map(f -> f.type)
                .map(Generator::toLLVMType)
                .collect(Collectors.toList());
                
        args.add(0, toLLVMType(method.getParent()));

        return args;
    }

    private void declareVTables() {
        classMap.values().stream()
                .forEach(this::declareVTable);
    }

    private void declareVTable(VSOPClass clazz) {
        
        List<String> functions = clazz.methodList().stream()
                .map(m -> toLLVMType(m)+" "+ global(functionId(m.getParent().id, m.id)))
                .toList();
        
        String table = arrayOf(functions);
        String def = defConstant(global(vTableName(clazz.id)), type(vTableType(clazz.id)), table);

        out.println(def);
    }

    private void declareFunctions() {
        for(VSOPClass parent: classMap.values()) {
            if(parent == VSOPConstants.OBJECT)
                continue;
                
            for(VSOPMethod method: parent.methodList()) {

                if(method.getParent() != parent)
                    continue;

                ASTExpr body = methods.get(method);
                declareFunction(method, body);
            }
        }
    }
    
    private void declareFunction(VSOPMethod method, ASTExpr body) {
        Context ctx = new Context(classMap, method);

        String returnType = toLLVMType(method.returnType);
        String id = functionId(method.getParent().id, method.id);
        List<String> args = argsToLLVMType(method);
        String bodyStr = declareBody(ctx, body);

        String def = defFunction(returnType, id, args, bodyStr);
        out.println(def);
        out.println(ctx.stringDeclarations());
    }

    private String declareBody(Context ctx, ASTExpr body) {

        String temp = body.emitLLVM(ctx);
        String type = toLLVMType(body.type);
        String retInstr = ret(type, ctx.getLastValue());
        String block = String.join(System.lineSeparator(), temp, retInstr);
        return indentBlock(block+System.lineSeparator());
    }

    private String labelPattern = "[0-9]+:\\s*";
    private String indentBlock(String block) {
        return block.lines()
                .filter(l -> !l.isBlank())
                .map(l -> l.matches(labelPattern) ? l : '\t'+l)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
