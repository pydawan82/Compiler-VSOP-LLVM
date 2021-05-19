package compiler.ast;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

public abstract class ASTExpr extends ASTNode {

    public final VSOPType type;

    public ASTExpr(VSOPType type) {
        this.type = type;
    }

    /**
     * Emit the LLVM code relative to the node.
     * @param ctx - A context object that store any data related to code generation
     * and current context of the VSOP program.
     * @return a String containing the LLVM code relative to the node.
     */
    public abstract String emitLLVM(Context ctx);
    
}
