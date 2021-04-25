package compiler.ast;

import java.io.PrintStream;

import org.antlr.v4.runtime.ParserRuleContext;

import compiler.vsop.VSOPClass;

public class ASTSelf extends ASTExpr {

    public ASTSelf(ParserRuleContext context, VSOPClass vsopClass) {
        super(context, vsopClass);
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.printf("self:%s", type.id);
    }
    
}