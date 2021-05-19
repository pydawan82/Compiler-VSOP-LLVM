package compiler.llvm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import compiler.util.Pair;

import static compiler.llvm.LLVMConstants.*;

/**
 * A class used to format instructions of LLVM IR
 */
public final class LLVMFormatter {
    private LLVMFormatter() {}

    /**
     * Creates an empty comment
     * @return a formatted empty comment
     */
    public static String comment() {
        return comment("");
    }

    /**
     * Creates a comment in LLVM IR. Comment should not contain any line return.
     * @param comment the comment to be written
     * @return a formatted comment
     */
    public static String comment(String comment) {
        String format = "; %s";

        return String.format(format, comment);
    }

    /**
     * Creates a 'section' comment. It is just a comment but with an empty comment
     * and bottom.
     * @param section - the name of the section
     * @return a 3 lines comment with the name of the section
     */
    public static String section(String section) {
        String format = """
        %s
        %s
        %s""";

        return String.format(format, comment(), comment(section), comment());
    }

    /**
     * A formatted version of the {@link compiler.vsop.VSOPClass} id.
     * @param classId - the id of a class
     * @return a formatted version of the class id;
     */
    public static String classId(String classId) {
        String format = "%s";

        return String.format(format, classId);
    }

    public static String initId(String classId) {
        String format = "%s___init";

        return format.formatted(classId);
    }

    /**
     * 
     * @param classId - the id of a class 
     * @return a formatted version of the vTable type
    */
    public static String vTableType(String classId) {
        String format = "%sVTable";

        return String.format(format, classId);
    }

    /**
     * 
     * @param classId - the id of a class
     * @return a formatted version of the vTable name
     */
    public static String vTableName(String classId) {
        String format = "%s___vtable";

        return String.format(format, classId);
    }

    /**
     * 
     * @param id - the id of a variable
     * @return A formatted version of the variable reference
     */
    public static String var(String id) {
        String format = "%%%s";

        return String.format(format, id);
    }

    /**
     * 
     * @param ord - the id of a variable
     * @return A formatted version of the variable reference
     */
    public static String var(int ord) {
        String format = "%%%d";

        return String.format(format, ord);
    }

    /**
     * 
     * @param name - the id of a label
     * @return A label in LLVM IR
     */
    public static String label(String name) {
        String format = "%s:";

        return format.formatted(name);
    }

    /**
     * 
     * @param ord - the id of a label
     * @return A label in LLVM IR
     */
    public static String label(int ord) {
        String format = "%d:";

        return format.formatted(ord);
    }

    /**
     * 
     * @param var - the id of a global variable
     * @return The formatted version of this global variable
     */
    public static String global(String var) {
        String format = "@%s";

        return String.format(format, var);
    }

    /**
     * 
     * @param type - a class type
     * @return The formatted version of the reference to this type
     */
    public static String type(String type) {
        String format = "%%%s";

        return String.format(format, type);
    }

    /**
     * Creates a {@link String} representation from a VSOP method.
     * @param clazz - The name of the class of the method
     * @param method - The name of the method
     * @return a formated function name to use in LLVM source code
     */
    public static String functionId(String clazz, String method) {
        String format = "%s__%s";

        return String.format(format, clazz, method);
    }

    public static String defVar(String id, String type, String value) {
        String format = "%s = %s %s";

        return String.format(format, id, type, value);
    }

    public static String defConstant(String id, String type, String value) {
        String format = "%s = constant %s %s";

        return String.format(format, id, type, value);
    }

    /**
     * Used to define structures in LLVM.
     * Vararg version of {@link #defStruct(String, List)}
     * @param name - the name of the stucture
     * @param types - the types in the struct
     * @return a {@link String} representation of a structure definition in LLVM
     */
    public static String defStruct(String name, String ... types) {
        String format = "%%%s = type {%s}";
        String typesStr = String.join(", ", types);

        return String.format(format, name, typesStr);
    }

    /**
     * Used to define structures in LLVM.
     * List version of {@link #defStruct(String, String...)}
     * @param name - the name of the stucture
     * @param types - the types in the struct
     * @return a {@link String} representation of a structure definition in LLVM
     */
    public static String defStruct(String name, Iterable<String> types) {
        String format = "%%%s = type {%s}";
        String typesStr = String.join(", ", types);

        return String.format(format, name, typesStr);
    }

    /**
     * Creates a {@link String} representation of the definition of a function in LLVM.
     * @param returnType - the return type of the function
     * @param name - the name of the function
     * @param args - a list of (type, name) {@link Pair}s
     * @param body - a list of instructions
     * @return a {@link String} of the function definition.
     */
    public static String defFunction(String returnType, String name, Iterable<String> args, String body) {
        String format = """
            define %s @%s (%s) {
            %s
            }
            """;

        String argsStr = String.join(", ", args);
        
        return String.format(format, returnType, name, argsStr, body);
    }
    
    public static String assign(int ord, String value) {
        return assign(var(ord), value);
    }

    public static String assign(String id, String value) {
        String format = "%s = %s";

        return String.format(format, id, value);
    }

    public static String op(String op, String type, String left, String right) {
        String format = "%s %s %s, %s";
        return String.format(format, op, type, left, right);
    }

    private static String callFormat = "call %s %s(%s)";
    public static String call(String type, String function, String ... args) {
        String argsStr = String.join(", ", args);

        return String.format(callFormat, type, function, argsStr);
    }

    public static String call(String type, String function, Iterable<String> args) {
        String argsStr = String.join(", ", args);

        return String.format(callFormat, type, function, argsStr);
    }

    public static String branch(String cond, String labelTrue, String labelFalse) {
        String format = "br i1 %s, label %s, label %s";

        return format.formatted(cond, labelTrue, labelFalse);
    }

    public static String branch(String label) {
        String format = "br label %s";

        return format.formatted(label);
    }

    public static String select(String cond, String then, String elze) {
        String format = "select i1 %s, %s, %s";

        return format.formatted(cond, then, elze);
    }

    public static String phi(String type, List<Pair<String, String>> pairs) {
        String format = "phi %s %s";
        String formatPair = "[%s, %s]";

        String br = pairs.stream()
                .map(p -> formatPair.formatted(p.first(), p.second()))
                .collect(Collectors.joining(", "));
        
        return format.formatted(type, br);
    }

    public static String icmp(String cond, String type, String op1, String op2) {
        String format = "icmp %s %s %s, %s";

        return format.formatted(cond, type, op1, op2);
    }

    public static String ret(String type, String value) {
        String format = "ret %s %s";

        return format.formatted(type, value);
    }

    public static String GET(String type, String ptrval, int idx) {
        String format = "getelementptr %s, %s %s, %s %d,%s %d";

        return format.formatted(type, pointerOf(type), ptrval, LLVMConstants.INT32, 0, LLVMConstants.INT32, idx);
    }

    public static String get(String type, String ptrval, int ... idx) {
        String format = "getelementptr %s, %s, %s";

        String values = IntStream.of(idx)
            .mapToObj(i -> INT32+" "+i)
            .collect(Collectors.joining(", "));
        
        return format.formatted(type, ptrval, values);
    }

    public static String getSize(String type) {
        String format = "getelementptr %s, %s %s, %s %d";
        
        return format.formatted(type, pointerOf(type), NULL, INT32, 1);
    }

    public static String getString(String var, int size) {
        String format = "getelementptr inbounds ([%d x i8], [%d x i8]* %s, i64 0, i64 0)";
        
        return format.formatted(size, size, var);
    }

    public static String extractvalue(String type, String value, int ord) {
        String format = "extractvalue %s %s, %d";
        return format.formatted(type, type, LLVMConstants.pointerOf(type), value, ord);
    }

    public static String load(String type, String ptr) {
        String format = "load %s, %s %s";

        return format.formatted(type, LLVMConstants.pointerOf(type), ptr);
    }

    public static String store(String type, String value, String ptr) {
        String format = "store %s %s, %s %s";

        return format.formatted(type, value, LLVMConstants.pointerOf(type), ptr);
    }

    public static String alloca(String type) {
        String format = "alloca %s";

        return format.formatted(type);
    }

    public static String malloc(String size) {
        String malloc = "@malloc";
        return call(pointerOf(integer(8)), malloc, size);
    }

    public static String ptrtoint(String type, String var, String intType) {
        String format = "ptrtoint %s %s to %s";

        return format.formatted(type, var, intType);
    }

    public static String bitcast(String fromType, String var, String toType) {
        String format = "bitcast %s %s to %s";

        return format.formatted(fromType, var, toType);
    }
}
