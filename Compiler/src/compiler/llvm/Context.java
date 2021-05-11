package compiler.llvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPField;
import compiler.vsop.VSOPMethod;

/**
 * A context class to store any data related to code generation
 * and current context of the VSOP program.
 */
public class Context {

    /**
     * A map from defined class'names to their corresponding {@link VSOPClass}
     */
    private final Map<String, VSOPClass> classMap;

    /**
     * A map from defined variables names to their corresponding {@link Variable}
     */
    private final Map<String, Variable<?>> vars;

    private final Map<String, String> param = new HashMap<>();

    private final Map<VSOPField, Integer> fieldOrdinal = new HashMap<>();
    private final Map<VSOPMethod, Integer> methodOrdinal = new HashMap<>();
    private final Map<String, Integer> varOrdinal = new HashMap<>();

    private int varCounter = -1;

    /**
     * Creates a new context given a map of classes and a map of variables
     * @param classMap - a map containing the defined classes
     * @param vars - a map containing the defined variables
     */
    public Context(Map<String, VSOPClass> classMap, Map<String, Variable<?>> vars) {
        this.classMap = Objects.requireNonNull(classMap);
        this.vars = Objects.requireNonNull(vars);
    }

    public int ordinalOf(VSOPField field) {
        return fieldOrdinal.get(field);
    }

    public int ordinalOf(VSOPMethod method) {
        return methodOrdinal.get(method);
    }

    public int ordinalOf(String variable) {
        if(!varOrdinal.containsKey(variable))
            throw new CompilationException("Attempted to fetch undeclared variable");
        return varOrdinal.get(variable);
    }

    public int updateVariable(String variable) {
        varCounter++;
        varOrdinal.put(variable, varCounter);
        return varCounter;
    }

    public int getLastValue() {
        return varCounter;
    }
}
