package compiler.ast;

import java.io.PrintStream;

public abstract class ASTNode {

    public abstract void visit();
    public abstract void print(PrintStream pStream);
}
