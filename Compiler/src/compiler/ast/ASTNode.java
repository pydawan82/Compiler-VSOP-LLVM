package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ASTNode {
    private ParserRuleContext context;

    public ASTNode(ParserRuleContext context) {
        this.context = context;
    }

    public ParserRuleContext getContext() {
        return context;
    }

    public abstract void visit();
    public abstract void print(PrintStream pStream);
}
