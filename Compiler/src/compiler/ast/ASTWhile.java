package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

import static compiler.vsop.VSOPConstants.UNIT;

public class ASTWhile extends ASTExpr {

    ASTExpr cond;
    ASTExpr loop;

    public ASTWhile(
            ASTExpr cond,
            ASTExpr loop
        )
    {
        super(UNIT);
        this.cond = cond;
        this.loop = loop;
    }

    @Override
    public void emitLLVM(PrintStream pStream, Context ctx) {
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("While(");
        cond.print(pStream, indent+1);
		pStream.print(", ");
        loop.print(pStream, indent+1);
		pStream.printf("):%s", UNIT.id);
    }
    
}