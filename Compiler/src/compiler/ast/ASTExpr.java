package compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public abstract class ASTExpr extends ASTNode {

    VSOPType type;

    public ASTExpr(ParserRuleContext context, VSOPType type) {
        super(context);
        this.type = type;
    }
    
}
