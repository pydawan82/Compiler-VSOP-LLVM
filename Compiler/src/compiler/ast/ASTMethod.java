package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.vsop.VSOPMethod;

public class ASTMethod extends ASTNode {

    VSOPMethod vsopMethod;
    List<ASTFormal> formals;
    ASTBlock block;

    public ASTMethod(
            List<ASTFormal> formals,
            ASTBlock block
        )
    {
        this.formals = formals;
        this.block = block;
    }

    @Override
    public void visit() {

    }

    @Override
    public void print(PrintStream pStream) {

        pStream.printf("Method(%s, ", vsopMethod.id);

        pStream.print('[');

        int i = 0;
        for (var formal : formals) {
            i++;

            pStream.println();

            formal.print(pStream);

            if (i != formals.size()) {
                pStream.print(",");
            }
        }

        if (formals.size() != 0) {
            pStream.println();
        }

        pStream.print(']');

        pStream.print(", ");
        pStream.print(vsopMethod.ret);

        pStream.print(", ");
        block.print(pStream);

        pStream.print(')');
    }

}
