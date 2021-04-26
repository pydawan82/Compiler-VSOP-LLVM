package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

public abstract class ASTNode {

    private static final String TAB = "  ";

    public void emitLLVM(PrintStream pStream, Context ctx) {
        throw new RuntimeException("Function should have been overriden");
    }

    public void print(PrintStream pStream) {
        print(pStream, 0);
    }

    public abstract void print(PrintStream pStream, int indent);

    protected void indent(PrintStream pStream, int indent) {
        for(int i=0; i<indent; i++)
            pStream.print(TAB);
    }
}
