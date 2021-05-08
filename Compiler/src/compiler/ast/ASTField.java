package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import compiler.llvm.Context;
import compiler.vsop.VSOPField;

public class ASTField extends ASTNode{
    VSOPField vsopField;
    Optional<ASTExpr> expr;

    public ASTField(
            VSOPField vsopField,
            Optional<ASTExpr> expr
        )
    {
        this.vsopField = vsopField;
        this.expr = expr;
    }

    @Override
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {

		pStream.printf("Field(%s, %s", vsopField.id, vsopField.type.id);

		if(expr.isPresent())
            pStream.print(", ");
            expr.get().print(pStream, indent+1);

		pStream.print(')');
    }
    
}
