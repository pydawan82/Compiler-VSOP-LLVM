package compiler.llvm;

import java.util.List;

import compiler.util.Pair;

public final class LLVMFormatter {
    private LLVMFormatter() {}

    public static String comment() {
        return comment("");
    }

    public static String comment(String comment) {
        String format = "; %s";

        return String.format(format, comment);
    }

    public static String section(String section) {
        String format = """
        %s
        %s
        %s""";

        return String.format(format, comment(), comment(section), comment());
    }

    public static String classId(String classId) {
        String format = "%s";

        return String.format(format, classId);
    }

    public static String vTableType(String classId) {
        String format = "%sVTable";

        return String.format(format, classId);
    }

    public static String vTableName(String classId) {
        String format = "%s_vtable";

        return String.format(format, classId);
    }

    public static String var(String id) {
        String format = "%%%s";

        return String.format(format, id);
    }

    public static String var(int ord) {
        String format = "%%%d";

        return String.format(format, ord);
    }

    public static String global(String var) {
        String format = "@%s";

        return String.format(format, var);
    }

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
        String format = "%s_%s";

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

    public static String assign(String id, String value) {
        String format = "%s = %s";

        return String.format(format, id, value);
    }

    public static String op(String op, String type, String left, String right) {
        String format = "%s %s %s %s";
        return String.format(format, op, type, left, right);
    }

    private static String callFormat = "call %s %s(%s)";
    public static String call(String type, String function, String ... args) {
        String argsStr = String.join(", ", args);

        return String.format(callFormat, argsStr);
    }

    public static String call(String type, String function, Iterable<String> args) {
        String argsStr = String.join(", ", args);

        return String.format(callFormat, argsStr);
    }
}
