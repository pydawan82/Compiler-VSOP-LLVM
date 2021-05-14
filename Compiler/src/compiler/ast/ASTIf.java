package compiler.ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.util.Pair;
import compiler.util.PrintUtil;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

public class ASTIf extends ASTExpr {

    ASTExpr condExpr;
    ASTExpr thenExpr;
    Optional<ASTExpr> elseExpr;

    public ASTIf(
            VSOPType type,
            ASTExpr condExpr,
            ASTExpr thenExpr,
            Optional<ASTExpr> elseExpr
        )
    {
        super(type);
        this.condExpr = condExpr;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public String emitLLVM(Context ctx) {
        List<String> list = new ArrayList<>();

        String condStr = condExpr.emitLLVM(ctx);
        list.add(condStr);
        String condVar = var(ctx.getLastValue());

        int labelTrue = ctx.unnamed();
        int labelFalse = ctx.unnamed();

        String branch = branch(condVar, var(labelTrue), var(labelFalse));
        list.add(branch);
        list.add(label(labelTrue));

        int labelEnd = ctx.unnamed();

        String then = thenExpr.emitLLVM(ctx);
        list.add(then);
        String thenVar = ctx.getLastValue();

        String elzeVar = "";
        if(elseExpr.isPresent()) {
            String jump = branch(var(labelEnd));
            list.add(jump);

            list.add(label(labelFalse));
            String elze = elseExpr.get().emitLLVM(ctx);
            list.add(elze);
            elzeVar = ctx.getLastValue();
        }

        list.add((elseExpr.isPresent()) ? label(labelEnd) : label(labelFalse));

        if(type != VSOPConstants.UNIT) {
            String phi = assign(var(ctx.unnamed()),
                phi(Generator.toLLVMType(type), List.of(
                        new Pair<String, String>(var(labelTrue), thenVar),
                        new Pair<String, String>(var(labelFalse), elzeVar)
                    )
                )
            );

            list.add(phi);
        }
        
        return String.join(System.lineSeparator(), list);
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print("If(");
		condExpr.print(pStream, indent+1);
		pStream.print(", ");
		thenExpr.print(pStream, indent+1);

		if (elseExpr.isPresent()) {
			pStream.print(", ");
			elseExpr.get().print(pStream, indent+1);
        }

		pStream.printf("):%s", type.id);
    }
    
}