package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTLet extends ASTExpr {

    String id;
    Optional<ASTExpr> value;
    ASTExpr in;

    public ASTLet(
            ParserRuleContext context,
            VSOPType type,
            String id,
            Optional<ASTExpr> value,
            ASTExpr in
        )
    {
        super(context, type);
        this.id = id;
        this.value = value;
        this.in = in;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.printf("Let(%s, %s, ", id, type.id);

		if (value.isPresent()) {
			value.get().print(pStream);
			pStream.print(", ");
		}

		in.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}