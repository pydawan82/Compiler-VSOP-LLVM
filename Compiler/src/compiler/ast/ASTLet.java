package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import compiler.llvm.Context;
import compiler.llvm.LLVMFormatter;
import compiler.vsop.VSOPType;

public class ASTLet extends ASTExpr {

    String id;
    Optional<ASTExpr> value;
    ASTExpr in;

    public ASTLet(
            VSOPType type,
            String id,
            Optional<ASTExpr> value,
            ASTExpr in
        )
    {
        super(type);
        this.id = id;
        this.value = value;
        this.in = in;
    }

    @Override
    public String emitLLVM(Context ctx) {
        String assign = assign(ctx);
        String expr = in.emitLLVM(ctx);

        return String.join(System.lineSeparator(), assign, expr);
    }

    private String assign(Context ctx) {
        String llvmId = LLVMFormatter.var(ctx.updateVariable(id));

        String llvmValue;
        if(value.isPresent()) {
            value.get().emitLLVM(ctx);
            llvmValue = LLVMFormatter.var(ctx.getLastValue());
        } else {
            llvmValue = "0";
        }

        return LLVMFormatter.assign(llvmId, llvmValue);
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