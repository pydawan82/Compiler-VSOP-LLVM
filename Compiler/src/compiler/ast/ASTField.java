package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;


import compiler.vsop.VSOPField;

public class ASTField extends ASTNode{
    VSOPField vsopField;
    ASTType type;
    Optional<ASTExpr> expr;

    public ASTField(
            VSOPField vsopField,
            ASTType type,
            Optional<ASTExpr> expr
        )
    {
        this.vsopField = vsopField;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {

		pStream.printf("Field(%s, ", vsopField.id);
		type.print(pStream);

		if(expr.isPresent())
            pStream.print(", ");
            expr.get().print(pStream);

		pStream.print(')');
    }
    
}
