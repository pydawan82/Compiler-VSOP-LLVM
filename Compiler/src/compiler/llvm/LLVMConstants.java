package compiler.llvm;

import java.util.Map;

import compiler.vsop.VSOPBinOp;
import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPType;

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

    protected static final String EMPTY_STRING = "";

    public static String defaultValue(VSOPType type) {
        if(type instanceof VSOPClass)
            return NULL;
        if(type == VSOPConstants.BOOL)
            return FALSE;
        if(type == VSOPConstants.INT32)
            return String.valueOf(0);
        if(type == VSOPConstants.STRING)
            return EMPTY_STRING;
        
        throw new CompilationException("Unreachable branch");
    }

    
    /**
     *
     * @param value - the value of the constant
     * @return a {@link String} representation of a string constant definition in LLVM
     */
    public static String stringLiteral(String value) {
        String format =  "c%s";

        return format.formatted(value);
    }

    /**
     * Creates a formatted representation of an aggregated structure.
     * @param values - a list of types.
     * @return a formatted version of the array of types in LLVM IR
     */
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

    /**
     * Creates a formatted version of the type of a constant string in LLVM IR.
     * @param length - the length of the string
     * @return the formatted representation of the type of the string
     */
    public static String string(int length) {
        return array(CHAR, length);
    }

    /**
     * 
     * @param type - the type of the the elements in the array
     * @param length - the length of the array
     * @return A formatted representation of the array type in LLVM IR.
     */
    public static String array(String type, int length) {
        if(length < 0)
            throw new IllegalArgumentException("Length must be a positive integer");
        
        String format = "[%s x %s]";

        return format.formatted(length, type); 
    }

    /**
     * 
     * @param type - a type
     * @return a formatted version of a pointer type to the given type.
     */
    public static String pointerOf(String type) {
        String format = "%s*";

        return format.formatted(type);
    }

    /**
     * Creates a formatted version of the type of a function in LLVM IR.
     * @param returnType - the return type of the function
     * @param args - a list of argument types
     * @return a formatted version of the type of the function
     */
    public static String function(String returnType, String ... args) {
        String format = "%s (%s)*";

        String argsStr = String.join(", ", args);

        return format.formatted(returnType, argsStr);
    }

    /**
     * Creates a formatted version of the type of a function in LLVM IR.
     * @param returnType - the return type of the function
     * @param args - a list of argument types
     * @return a formatted version of the type of the function
     */
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
    
    /**
     * Maps {@link VSOPBinOp} to their LLVM IR opcodes.
     * @param binOp - the binary operator
     * @return the opcode in LLVM IR
     * @throws CompilationException if the operator is not mapped to any opcode
     */
    public static String binOp(VSOPBinOp binOp) {
        String result = binops.get(binOp);

        if(result == null)
            throw new CompilationException("Unhandled operator");
        
        return result;
    }
}