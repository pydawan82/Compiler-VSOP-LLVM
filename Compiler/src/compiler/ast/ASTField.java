package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;


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
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {

		pStream.printf("Field(%s, %s", vsopField.id, vsopField.type.id);

		if(expr.isPresent())
            pStream.print(", ");
            expr.get().print(pStream);

		pStream.print(')');
    }
    
}
