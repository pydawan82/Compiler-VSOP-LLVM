package compiler.ast;

import compiler.vsop.VSOPType;

public abstract class ASTExpr extends ASTNode {

    public final VSOPType type;

    public ASTExpr(VSOPType type) {
        this.type = type;
    }
    
}
