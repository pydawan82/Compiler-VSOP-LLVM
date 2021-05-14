package compiler.ast;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import compiler.llvm.Context;
import compiler.llvm.Generator;
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
        String objInstr = object.emitLLVM(ctx);

        List<Pair<String,String>> argsPair = args.stream()
                .map(arg -> argToLLVM(ctx, arg))
                .toList();
        
        String pre = argsPair.stream()
                .map(p -> p.first())
                .collect(Collectors.joining(System.lineSeparator()));

        List<String> argsList = argsPair.stream()
                .map(arg -> arg.second())
                .toList();

        String ret = Generator.toLLVMType(vsopMethod.returnType);
        String call = call(ret, global(functionId(vsopMethod.getParent().id, vsopMethod.id)), argsList);
        String line = (vsopMethod.returnType == VSOPConstants.UNIT)
            ? call
            : assign(var(ctx.unnamed()), call);

        String operation = line;

        return String.join(System.lineSeparator(), objInstr, pre, operation);
    }

    private Pair<String, String> argToLLVM(Context ctx, ASTExpr arg) {
        String instr = arg.emitLLVM(ctx);
        String var = Generator.toLLVMType(arg.type) + " " + ctx.getLastValue();
        return new Pair<String, String>(instr, var);
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