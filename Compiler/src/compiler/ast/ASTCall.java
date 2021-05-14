package compiler.ast;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import compiler.llvm.Context;
import compiler.util.Pair;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPMethod;

import static compiler.llvm.LLVMFormatter.*;

public class ASTCall extends ASTExpr {
    
    VSOPMethod vsopMethod;
    ASTExpr object;
    List<ASTExpr> args;

    public ASTCall(
            VSOPMethod vsopMethod,
            ASTExpr object,
            List<ASTExpr> args
        )
    {
        super(vsopMethod.returnType);
        this.vsopMethod = vsopMethod;
        this.object = object;
        this.args = args;
    }

    @Override
    public String emitLLVM(Context ctx) {
        List<Pair<String,String>> argsStr = args.stream()
                .map(arg -> argToLLVM(ctx))
                .toList();
        
        String pre = argsStr.stream()
                .map(p -> p.first())
                .collect(Collectors.joining(System.lineSeparator()));

        String call = "";//TODO  Use argscall();
        String line = (vsopMethod.returnType == VSOPConstants.UNIT)
            ? call
            : assign(var(ctx.unnamed()), call);

        String operation = line;

        return String.join(System.lineSeparator(), pre, operation);
    }

    private Pair<String, String> argToLLVM(Context ctx) {
        //TODO Ouais
        return new Pair<String, String>("", "");
    }

    @Override
    public void print(PrintStream pStream, int indent) {
		pStream.print("Call(");
		object.print(pStream, indent+1);
		pStream.printf(", %s, ", vsopMethod.id);

		pStream.print("[");
		int i = 0;
		for (var arg : args) {
			pStream.println();
            indent(pStream, indent);
			arg.print(pStream, indent+1);
			
            i++;

			if (i != args.size()) {
				pStream.print(",");
			}
		}

		if (args.size() != 0) {
			pStream.println();
            indent(pStream, indent);
		}
		pStream.print("]");

		pStream.printf("):%s", vsopMethod.returnType.id);
    }
    
}