package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.vsop.VSOPMethod;

public class ASTMethod extends ASTNode {

    VSOPMethod vsopMethod;
    List<ASTFormal> formals;
    ASTBlock block;

    public ASTMethod(
            VSOPMethod vsopMethod,
            List<ASTFormal> formals,
            ASTBlock block
        )
    {
        this.vsopMethod = vsopMethod;
        this.formals = formals;
        this.block = block;
    }

    @Override
    public void print(PrintStream pStream, int indent) {

        pStream.printf("Method(%s, ", vsopMethod.id);

        pStream.print('[');

        int i = 0;
        for (var formal : formals) {
            i++;

            pStream.println();
            indent(pStream, indent);

            formal.print(pStream, indent+1);

            if (i != formals.size()) {
                pStream.print(",");
            }
        }

        if (formals.size() != 0) {
            pStream.println();
            indent(pStream, indent);
        }

        pStream.print(']');

        pStream.print(", ");
        pStream.print(vsopMethod.returnType.id);

        pStream.print(", ");
        block.print(pStream, indent+1);

        pStream.print(')');
    }

}
