package compiler.ast;

import java.io.PrintStream;
import java.util.List;

import compiler.parsing.VSOPParser;

public class ASTProgram extends ASTNode {

    public List<ASTClass> classes;

    public ASTProgram(
            VSOPParser.ProgramContext context,
            List<ASTClass> classes
        )
    {

        super(context);
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