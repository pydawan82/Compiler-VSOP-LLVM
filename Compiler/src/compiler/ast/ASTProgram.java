package compiler.ast;

import java.io.PrintStream;
import java.util.List;

public class ASTProgram extends ASTNode {

    public List<ASTClass> classes;

    public ASTProgram(
            List<ASTClass> classes
        )
    {
        this.classes = classes;
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print('[');

		int i = 0;
        int size = classes.size();
		for (var clazz : classes) {
            i++;

			clazz.print(pStream, indent+1);
			if (i != size) {
				pStream.println(',');
                indent(pStream, indent);
			}
		}

		pStream.println(']');
    }
}