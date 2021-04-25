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
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.print('[');

		int i = 0;
		for (var clazz : classes) {
			clazz.print(pStream);
			if (i != classes.size()) {
				pStream.println(',');
			}
		}

		pStream.println(']');
    }
}