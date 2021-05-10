package compiler.llvm;

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

        return String.format(format, value);
    }

    public static String arrayOf(Iterable<String> values) {
        String format = "{%s}";
        String elements = String.join(", ", values);

        return String.format(format, elements);
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

        String format = "%di";
        
        return String.format(format, size);
    }

    public static String string(int length) {
        return array(CHAR, length);
    }

    public static String array(String type, int length) {
        if(length < 0)
            throw new IllegalArgumentException("Length must be a positive integer");
        
        String format = "[%s x %s]";

        return String.format(format, type, length); 
    }

    public static String pointerOf(String type) {
        String format = "%s*";

        return String.format(format, type);
    }

    public static String function(String returnType, String ... args) {
        String format = "%s (%s)*";

        String argsStr = String.join(", ", args);

        return String.format(format, returnType, argsStr);
    }

    public static String function(String returnType, Iterable<String> args) {
        String format = "%s (%s)*";

        String argsStr = String.join(", ", args);

        return String.format(format, returnType, argsStr);
    }

}