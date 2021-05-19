package compiler.ast;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.llvm.LLVMFormatter;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;

public class ASTLet extends ASTExpr {

    String id;
    VSOPType varType;
    Optional<ASTExpr> value;
    ASTExpr in;

    public ASTLet(
            VSOPType type,
            String id,
            VSOPType varType,
            Optional<ASTExpr> value,
            ASTExpr in
        )
    {
        super(type);
        this.id = id;
        this.varType = varType;
        this.value = value;
        this.in = in;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String assign = ass(ctx);
        String expr = in.emitLLVM(ctx);

        return String.join(System.lineSeparator(), assign, expr);
    }

    private String ass(Context ctx) {

        if(value.isPresent()) {

            String pre = value.get().emitLLVM(ctx);
            if(this.varType == value.get().type) {
                ctx.setValueOf(id, ctx.getLastValue());
            } else {
                String last = ctx.getLastValue();
                int ord = ctx.updateVariable(id);
                String from = Generator.toLLVMType(value.get().type);
                String to = Generator.toLLVMType(this.varType);
                String assign = assign(ord, bitcast(from, last, to));
                pre += System.lineSeparator() + assign;
            }

            return pre;

        } else {

            ctx.setValueOf(id, "0");
            return "";
        }
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.printf("Let(%s, %s, ", id, type.id);

		if (value.isPresent()) {
			value.get().print(pStream, indent+1);
			pStream.print(", ");
		}

		in.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}