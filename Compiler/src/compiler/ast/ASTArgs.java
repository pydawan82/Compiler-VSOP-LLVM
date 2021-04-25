package compiler.ast;

import java.io.PrintStream;
import java.util.List;

public class ASTArgs extends ASTNode {

    List<ASTExpr> expressions;

    public ASTArgs(
            List<ASTExpr> expressions
        )
    {
        this.expressions = expressions;
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
		pStream.print("[");
		int i = 0;
		for (var expr : expressions) {
			pStream.println();
			expr.print(pStream);
			
            i++;

			if (i != expressions.size()) {
				pStream.print(",");
			}
		}

		if (expressions.size() != 0) {
			pStream.println();
		}

		pStream.print("]");
    }
}