package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPType;

public class ASTAss extends ASTExpr {
    String id;
    ASTExpr value;
    VSOPType type;

    public ASTAss(
            ParserRuleContext context,
            VSOPType type,
            ASTExpr value
        ) 
    {
        super(context, type);
        this.value = value;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.printf("Assign(%s, ", id);
		value.print(pStream);
		pStream.printf("):%s", type.id);
    }
    
}