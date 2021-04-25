package compiler.ast;

import java.io.PrintStream;

import compiler.vsop.VSOPType;

public class ASTLiteral extends ASTExpr {
    
    String value;
    
    public ASTLiteral(
        VSOPType type,
        String value
    ) {
        super(type);
        this.value = value;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print(value);
		pStream.printf(":%s", type.id);
    }
    
}