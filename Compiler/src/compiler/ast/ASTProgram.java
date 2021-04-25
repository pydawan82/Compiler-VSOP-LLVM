package compiler.ast;

import java.io.PrintStream;
import compiler.parsing.VSOPParser;

public class ASTProgram extends ASTNode {

    public ASTProgram(VSOPParser.ProgramContext context) {
        super(context);
    }

    @Override
    public void visit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void print(PrintStream pStream) {
        // TODO Auto-generated method stub
        
    }
}