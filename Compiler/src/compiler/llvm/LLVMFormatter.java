package compiler.llvm;

import java.util.List;
import java.util.stream.Stream;

import compiler.util.Pair;

public class LLVMFormatter {
    private LLVMFormatter() {}

    /**
     * Used to define structures in LLVM
     * @param name - the name of the stucture
     * @param types - the types in the struct
     * @return a {@link String} representation of a structure definition in LLVM
     */
    public static String struct(String name, String ... types) {
        String format = "%%%s = type {%s}";
        String typeList = Stream.of(types).reduce("", (s1, s2) -> s1+", "+s2);

        return String.format(format, name, typeList);
    }

    /**
     *
     * @param value - the value of the constant
     * @return a {@link String} representation of a string constant definition in LLVM
     */
    public static String constantString(String value) {
        String format =  "c\"%s\"";

        return String.format(format, value);
    }

    /**
     * Creates a {@link String} representation of the definition of a function in LLVM.
     * @param returnType - the return type of the function
     * @param name - the name of the function
     * @param args - a list of (type, name) {@link Pair}s
     * @param body - a list of instructions
     * @return a {@link String} of the function definition.
     */
    public static String function(String returnType, String name, List<Pair<String, String>> args, List<String> body) {
        String format = """
            define %s @%s (%s) {
                %s
            }
            """;
        
        String argsStr = args.stream()
                .map(pair -> pair.first() + " " + pair.second())
                .reduce("", (s1, s2) -> s1+", "+s2);
        
        String bodyStr = body.stream()
                .reduce("", (s1, s2) -> s1+"\n"+s2);
        
        return String.format(format, returnType, name, argsStr, bodyStr);
    }
}
