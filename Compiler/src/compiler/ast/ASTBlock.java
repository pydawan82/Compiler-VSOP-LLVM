package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.llvm.Context;
import compiler.vsop.VSOPType;

public class ASTBlock extends ASTExpr {
    List<ASTExpr> expressions;

    public ASTBlock(
            List<ASTExpr> expressions,
            VSOPType returnType
        )
    {
        super(returnType);
        this.expressions = expressions;
    }

    @Override
    public String emitLLVM(Context ctx) {
        List<String> exprStr = expressions.stream()
                .map((expr) -> expr.emitLLVM(ctx))
                .toList();
        
        String body = String.join("\n", exprStr);

        return body;
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.print('[');

		int i = 0;
		for (var expr : expressions) {
			i++;
			pStream.println();
            indent(pStream, indent);
            
			expr.print(pStream, indent+1);
			if (i != expressions.size()) {
				pStream.print(",");
			}
		}

		if (expressions.size() != 0) {
			pStream.println();
            indent(pStream, indent);
		}

		pStream.print(']');
		pStream.printf(":%s", type.id);
    }
    
}
