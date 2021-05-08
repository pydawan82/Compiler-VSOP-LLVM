package compiler;

import java.io.PrintStream;
import java.util.Map;
import java.util.Objects;

import compiler.ast.ASTProgram;
import compiler.error.SemanticError;
import compiler.parsing.VSOPParser;
import compiler.visitors.ClassVisitor;
import compiler.visitors.SemanticVisitor;
import compiler.vsop.VSOPClass;

/**
 * A class used for semantic checking.
 */
public class SemanticChecker {
    
    private PrintStream out = System.out;
    private PrintStream err = System.err;

    private VSOPParser parser;

    /**
     * Creates a new {@link SemanticChecker} given a {@link VSOPParser} in order
     * to proceed to semantic checking and previous compiling stages.
     * @param parser - a parser
     */
    public SemanticChecker(VSOPParser parser) {
        this.parser = Objects.requireNonNull(parser);
    }

    /**
     * Sets the {@link PrintStream} where the anotated AST is printed.
     * @param out - the new output stream
     */
    public void setOutputStream(PrintStream out) {
        this.out = out;
    }

    /**
     * Sets the {@link PrintStream} where errors are printed.
     * @param err - the new error stream
     */
    public void setErrorStream(PrintStream err) {
        this.err = err;
    }

    /**
     * Proceed to semantic checking and previous compiling stages.
     * @return <code>true</code> if checking terminated without errors,
     * <code>false</code> otherwise.
     */
    public boolean check() {
		VSOPParser.ProgramContext ctx = parser.program();
		
		if(ctx == null) {
			System.err.println(new SemanticError("Input is not a valid program"));
			return false;
		}
		
		ClassVisitor classVisitor = new ClassVisitor(err);
        Map<String, VSOPClass> map = classVisitor.classMap(ctx);
        if(map == null)
            return false;

        SemanticVisitor semanticVisitor = new SemanticVisitor(map);
        ASTProgram program = semanticVisitor.check(ctx);
        if(program == null)
            return false;

        program.print(out);

		return true;
    }
}
