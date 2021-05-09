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

    /*
     * Types
     */

    /**
     * 
     * @param size - the number of bit of the integer
     * @return the {@link String} representation of a integer type of <code>size</code> bits in LLVM
     */
    public static String integer(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Size must be a strict positive integer");

        return String.format("%di", size);
    }


}