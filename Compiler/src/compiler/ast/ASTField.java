package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPField;

public class ASTField extends ASTNode{
    VSOPField vsopField;
    ASTType type;
    Optional<ASTExpr> expr;

    public ASTField(
            ParserRuleContext context,
            VSOPField field,
            ASTType type,
            Optional<ASTExpr> expr
        )
    {
        super(context);
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
