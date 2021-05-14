package compiler.llvm;

import java.util.Map;

import compiler.vsop.VSOPBinOp;

import static compiler.vsop.VSOPConstants.*;

public final class LLVMConstants {

    private LLVMConstants() {};

    /*
     * Literal constants
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String NULL = "null";
    public static final String NONE = "none";

    
    /**
     *
     * @param value - the value of the constant
     * @return a {@link String} representation of a string constant definition in LLVM
     */
    public static String stringLiteral(String value) {
        String format =  "c\"%s\"";

        return format.formatted(value);
    }

    public static String arrayOf(Iterable<String> values) {
        String format = "{%s}";
        String elements = String.join(", ", values);

        return format.formatted(elements);
    }

    /*
     * Types
     */

    public static final String BOOL = integer(1);
    public static final String INT32 = integer(32);
    public static final String CHAR = integer(8);
    public static final String STRING = pointerOf(CHAR);

    /**
     * 
     * @param size - the number of bit of the integer
     * @return the {@link String} representation of a integer type of <code>size</code> bits in LLVM
     */
    public static String integer(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Size must be a non-nul positive integer");

        String format = "i%d";
        
        return format.formatted(size);
    }

    public static String string(int length) {
        return array(CHAR, length);
    }

    public static String array(String type, int length) {
        if(length < 0)
            throw new IllegalArgumentException("Length must be a positive integer");
        
        String format = "[%s x %s]";

        return format.formatted(type, length); 
    }

    public static String pointerOf(String type) {
        String format = "%s*";

        return format.formatted(type);
    }

    public static String function(String returnType, String ... args) {
        String format = "%s (%s)*";

        String argsStr = String.join(", ", args);

        return format.formatted(returnType, argsStr);
    }

    public static String function(String returnType, Iterable<String> args) {
        String format = "%s (%s)*";

        String argsStr = String.join(", ", args);

        return format.formatted(returnType, argsStr);
    }

    /*
     * Operators
     */

    private static Map<VSOPBinOp, String> binops = Map.of(
            AND, "and",
            EQ, "icmp eq",
            LOW, "icmp slt",
            LE, "icmp sle",
            PLUS, "add",
            MINUS, "sub",
            TIMES, "mul",
            DIV, "sdiv",
            POW, "pow"
        );
    
    public static String binOp(VSOPBinOp binOp) {
        String result = binops.get(binOp);

        if(result == null)
            throw new CompilationException("Unhandled operator");
        
        return result;
    }
}