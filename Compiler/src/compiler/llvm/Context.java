package compiler.llvm;

import java.util.Map;

import compiler.vsop.VSOPClass;

public class Context {

    public final Map<String, VSOPClass> classMap;
    public final Map<String, Variable> vars;

    public Context(Map<String, VSOPClass> classMap, Map<String, Variable> vars) {
        this.classMap = classMap;
        this.vars = vars;
    }
}
