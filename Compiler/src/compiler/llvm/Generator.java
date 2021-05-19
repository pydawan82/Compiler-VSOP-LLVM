package compiler.llvm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import compiler.ast.ASTCall;
import compiler.ast.ASTExpr;
import compiler.ast.ASTNew;
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

    private static final String RUNTIME = "runtime/runtime.ll";

    /**
     * TODO Probably to be removed
     */
    private final Map<String, VSOPClass> classMap;
    private final Map<VSOPMethod, ASTExpr> methods;
    private final PrintStream out;

    /**
     * Creates a new generator given a program tree and a map of defined classes.
     * @param ast - The program tree
     * @param classMap - The map of defined classes
     */
    public Generator(Map<String, VSOPClass> classMap, Map<VSOPMethod, ASTExpr> methods, PrintStream out) {
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
        out.println();

        out.println(section("VTables"));
        declareVTables();
        out.println();

        out.println(section("Functions"));
        declareFunctions();
        out.println();

        out.println(section("Main"));
        declareMain();
        out.println();

        out.println(section("Runtime"));
        declareRuntime();
    }

    /**
     * Emit the LLVM code relative to the definition of the classes.
     */
    private void defineClasses() {
        classMap.values().stream()
                .forEach(this::defineClass);
    }

    /**
     * Emit the LLVM code relative to the definition of a given VSOPClass.
     * @param clazz - a clazz.
     */
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

    /**
     * Get a pointer to the type in LLVM code.
     * @param type - Type to be changed in LLVM.
     * @return Returns the code of the pointer to a VSOPType (String).
     */
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

    /**
     * Get the type in LLVM code.
     * @param type - Type to be changed in LLVM.
     * @return Returns the code of the VSOPType (String).
     */
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

    /**
     * Get the method in LLVM code.
     * @param method - Method to be changed in LLVM.
     * @return Returns the LLVM code relative to the VSOPMethod.
     */
    public static String toLLVMType(VSOPMethod method) {
        String returnType = toLLVMType(method.returnType);
        List<String> args = argsToLLVMType(method);

        return function(returnType, args);
    }

    /**
     * Transform the args of a method into LLVM code.
     * @param method - a VSOPMethod.
     * @return Returns a list of String. The String are the LLVM code relative to the arguments of the method.
     */
    private static List<String> argsToLLVMType(VSOPMethod method) {
        List<String> args = method.args.stream()
                .map(f -> f.type)
                .map(Generator::toLLVMType)
                .collect(Collectors.toList());
                
        args.add(0, toLLVMType(method.getParent()));

        return args;
    }

    /**
     * Emit LLVM code relative to the declarations of the vTables.
     */
    private void declareVTables() {
        classMap.values().stream()
                .forEach(this::declareVTable);
    }


    /**
     * Emit the LLVM code relative to the declaration of the vTable of a class.
     * @param clazz - a VSOPClass.
     */
    private void declareVTable(VSOPClass clazz) {
        
        List<String> functions = clazz.methodList().stream()
                .map(m -> toLLVMType(m)+" "+ global(functionId(m.getParent().id, m.id)))
                .toList();
        
        String table = arrayOf(functions);
        String def = defConstant(global(vTableName(clazz.id)), type(vTableType(clazz.id)), table);

        out.println(def);
    }

    /**
     * Emit the LLVM code relative to the declaration of the functions.
     */
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
    
    /**
     * Emit the LLVM code relative to a given method.
     * @param method - a VSOPMethod.
     * @param body - The body of the method.
     */
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

    /**
     * Emit the LLVM code relative to a given body of a method.
     * @param ctx - A context object that store any data related to code generation
     * and current context of the VSOP program.
     * @param body - a ASTExpr. The body of a method.
     * @return 
     */
    private String declareBody(Context ctx, ASTExpr body) {

        String temp = body.emitLLVM(ctx);
        String type = toLLVMType(body.type);
        String retInstr = ret(type, ctx.getLastValue());
        String block = String.join(System.lineSeparator(), temp, retInstr);
        return indentBlock(block+System.lineSeparator());
    }

    private String labelPattern = "[0-9]+:\\s*";

    /**
     * Indent the body of the method.
     * @param block - Block corresponding to the body of a method.
     * @return Return the indented block.
     */
    private String indentBlock(String block) {
        return block.lines()
                .filter(l -> !l.isBlank())
                .map(l -> l.matches(labelPattern) ? l : '\t'+l)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private void declareMain() {
        VSOPClass mainClass = classMap.get(VSOPConstants.MAIN_CLASS);
        VSOPMethod mainMethod = mainClass.methods().get(VSOPConstants.MAIN_METHOD);
        ASTCall callMain = new ASTCall(mainMethod, new ASTNew(mainClass), List.of());

        Context ctx = new Context(classMap, mainMethod);
        ctx.varCounter = 0;

        String returnType = toLLVMType(mainMethod.returnType);
        String id = "main";
        List<String> args = List.of();
        String bodyStr = declareBody(ctx, callMain);

        String def = defFunction(returnType, id, args, bodyStr);
        out.println(def);
        out.println(ctx.stringDeclarations());
    }

    private void declareRuntime() {
        try(Scanner scanner = new Scanner(new File(RUNTIME))) {
            scanner.useDelimiter("\n");
            scanner.forEachRemaining(out::println);
            IOException e = scanner.ioException();
            if(e != null) {
                throw new CompilationException("Failed to append runtime", e);
            }
        } catch (FileNotFoundException e) {
            throw new CompilationException("Failed to append runtime", e);
        }
    }
}
