package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

/**
 * A base class used to represent any node of an AST.
 */
public abstract class ASTNode {
    /**
     * The {@link String} used to indent.
     */
    public static final String TAB = "  ";

    /**
     * Emit LLVM source code to the given {@link PrintStream}.
     * The context represents the current context of the program.
     * @param pStream - the stream where source code is printed
     * @param ctx - the current program context
     */
    public void emitLLVM(PrintStream pStream, Context ctx) {
        throw new RuntimeException("Function should have been overriden");
    }

    /**
     * Prints the underlying tree to the given {@link PrintStream}.
     * @param pStream - the stream where the tree is printed
     */
    public void print(PrintStream pStream) {
        print(pStream, 0);
    }

    /**
     * Prints the underlying tree to the given {@link PrintStream}.
     * @param pStream - the stream where the tree is printed
     * @param indent - the number of repetition of {@link #TAB} after each new line.
     */
    public abstract void print(PrintStream pStream, int indent);

    /**
     * Prints a repetition of {@link #TAB} to the given {@link PrintStream}
     * @param pStream - the stream where {@link #TAB} is printed
     * @param indent - the number of repetition of {@link #TAB}
     */
    protected void indent(PrintStream pStream, int indent) {
        for(int i=0; i<indent; i++)
            pStream.print(TAB);
    }
}
