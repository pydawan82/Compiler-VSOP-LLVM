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
    public void emitLLVM(PrintStream pStream, Context ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream, int indent) {
        pStream.print('[');

		int i = 0;
		for (var clazz : classes) {
			clazz.print(pStream, indent+1);
			if (i != classes.size()) {
				pStream.println(',');
                indent(pStream, indent);
			}
		}

		pStream.println(']');
    }
}