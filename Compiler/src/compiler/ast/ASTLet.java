package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import compiler.llvm.Context;
import compiler.llvm.Generator;
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
        String prevVal = ctx.valueOf(id);
        String assign = ass(ctx);
        String expr = in.emitLLVM(ctx);
        ctx.setValueOf(id, prevVal);
        return String.join(System.lineSeparator(), assign, expr);
    }


    /**
     * Emit the LLVM code related to the assignment of the Let node.
     * @param ctx - A context object that store any data related to code generation
     * and current context of the VSOP program.
     * @return Returns the String related to the assignement of the Let in LLVM.
     */
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
		pStream.printf("Let(%s, %s, ", id, varType.id);

		if (value.isPresent()) {
			value.get().print(pStream, indent+1);
			pStream.print(", ");
		}

		in.print(pStream, indent+1);
		pStream.printf("):%s", type.id);
    }
    
}