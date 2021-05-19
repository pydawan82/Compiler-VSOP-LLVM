package compiler.ast;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import compiler.llvm.Context;
import compiler.llvm.Generator;
import compiler.util.Pair;
import compiler.vsop.VSOPConstants;
import compiler.vsop.VSOPMethod;
import compiler.vsop.VSOPType;

import static compiler.llvm.LLVMFormatter.*;
import static compiler.llvm.LLVMConstants.*;
import static compiler.llvm.Generator.toLLVMType;

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
        Pair<String, String> obj = argToLLVM(ctx, vsopMethod.getParent(), object);

        String methodAssign = loadVTable(ctx, ctx.getLastValue());
        String methodPtr = ctx.getLastValue();

        List<Pair<String,String>> argsPair = Pair.zip(vsopMethod.args, args).stream()
                .map(p -> argToLLVM(ctx, p.first().type, p.second()))
                .toList();
        
        String pre = argsPair.stream()
                .map(Pair::first)
                .collect(Collectors.joining(System.lineSeparator()));

        List<String> argsList = argsPair.stream()
                .map(Pair::second)
                .collect(Collectors.toList());
        
        argsList.add(0, obj.second());

        String ret = Generator.toLLVMType(vsopMethod.returnType);
        String call = call(ret, methodPtr, argsList);
        String line = (vsopMethod.returnType == VSOPConstants.UNIT)
            ? call
            : assign(var(ctx.unnamed()), call);

        String operation = line;

        return String.join(System.lineSeparator(), obj.first(), methodAssign, pre, operation);
    }

    private String loadVTable(Context ctx, String objPtr) {
        int vtablePtrPtr = ctx.unnamed();
        String vtableAssign = assign(vtablePtrPtr, GET(Generator.toRawLLVMType(vsopMethod.getParent()), objPtr, 0));
        int vtablePtr = ctx.unnamed();
        String vtableLoad = assign(vtablePtr, load(var(pointerOf(vTableType(vsopMethod.getParent().id))), var(vtablePtrPtr)));
        int methodPtrPtr = ctx.unnamed();
        String methodAssign = assign(methodPtrPtr, GET(var(vTableType(vsopMethod.getParent().id)), var(vtablePtr), ctx.ordinalOf(vsopMethod)));
        int methodPtr = ctx.unnamed();
        String methodLoad = assign(methodPtr, load(Generator.toLLVMType(vsopMethod), var(methodPtrPtr)));

        return String.join(System.lineSeparator(), 
                vtableAssign,
                vtableLoad,
                methodAssign,
                methodLoad
            );
    }

    private Pair<String, String> argToLLVM(Context ctx, VSOPType argType, ASTExpr arg) {
        String instr = arg.emitLLVM(ctx);
        String to = toLLVMType(argType);
        String from = toLLVMType(arg.type);
        String objPtr = ctx.getLastValue();
        instr += System.lineSeparator() + assign(ctx.unnamed(), bitcast(from, objPtr, to));
        String var = to + " " + ctx.getLastValue();
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