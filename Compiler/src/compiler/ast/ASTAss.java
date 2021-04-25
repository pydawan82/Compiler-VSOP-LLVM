package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTAss extends ASTExpr {
    String id;
    ASTExpr value;
    VSOPType type;

    public ASTAss(
            VSOPType type,
            ASTExpr value
        ) 
    {
        super(type);
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