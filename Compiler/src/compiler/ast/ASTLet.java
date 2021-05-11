package compiler.ast;

import java.io.PrintStream;
import java.util.Optional;

import compiler.llvm.Context;
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
        String format = "";

        ctx.updateVariable(id);
        
        return String.format(format);
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