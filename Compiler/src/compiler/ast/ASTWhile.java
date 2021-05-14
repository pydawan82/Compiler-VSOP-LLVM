package compiler.ast;

import java.io.PrintStream;

import compiler.llvm.Context;

import static compiler.vsop.VSOPConstants.UNIT;
import static compiler.llvm.LLVMFormatter.*;

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
    public String emitLLVM(Context ctx) {
        int loopOrd = ctx.unnamed();
        String labelLoop = label(loopOrd);

        String condInstr = cond.emitLLVM(ctx);
        String condVar = ctx.getLastValue();
        int startOrd = ctx.unnamed();
        int endOrd = ctx.unnamed();

        String branch = branch(condVar, var(startOrd), var(endOrd));

        String labelStart = label(startOrd);
        String loopInstr = loop.emitLLVM(ctx);
        String jump = branch(var(loopOrd));

        String labelEnd = label(endOrd);
        
        return String.join(System.lineSeparator(), 
                labelLoop,
                condInstr,
                branch,
                labelStart,
                loopInstr,
                jump,
                labelEnd
            );
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