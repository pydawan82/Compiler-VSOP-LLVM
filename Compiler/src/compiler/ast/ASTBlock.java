package compiler.ast;

import java.io.PrintStream;
import java.util.List;


import compiler.vsop.VSOPType;

public class ASTBlock extends ASTNode {
    List<ASTExpr> expressions;
    VSOPType returnType;

    public ASTBlock(
            List<ASTExpr> expressions,
            VSOPType returnType
        )
    {
        this.expressions = expressions;
        this.returnType = returnType;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.print('[');

		int i = 0;
		for (var expr : expressions) {
			i++;
			pStream.println();
			expr.print(pStream);
			if (i != expressions.size()) {
				pStream.print(",");
			}
		}

		if (expressions.size() != 0) {
			pStream.println();
		}

		pStream.print(']');
		pStream.printf(":%s", returnType.id);
    }
    
}
