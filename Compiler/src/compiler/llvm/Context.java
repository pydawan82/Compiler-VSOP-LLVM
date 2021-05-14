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
     * TODO Probably to be removed
     */
    private final Map<String, VSOPClass> classMap;

    /**
     * A map from defined variables names to their corresponding {@link Variable}
     * TODO Probably to be removed
     */
    private final Map<String, Variable<?>> vars;

    /**
     * A map to from String to String properties.
     * TODO Probably to be removed
     */
    private final Map<String, String> properties = new HashMap<>();

    private final Map<VSOPField, Integer> fieldOrdinal = new HashMap<>();
    private final Map<VSOPMethod, Integer> methodOrdinal = new HashMap<>();
    private final Map<String, Integer> varOrdinal = new HashMap<>();

    private int varCounter = -1;
    private int lastValue = varCounter;

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
        Integer ordinal = varOrdinal.get(variable);
        
        if(ordinal == null)
            throw new CompilationException("Attempted to fetch undeclared variable");
        
            return ordinal;
    }

    public int updateVariable(String variable) {
        varCounter++;
        lastValue = varCounter;
        varOrdinal.put(variable, varCounter);
        return varCounter;
    }

    public int unnamed() {
        varCounter++;
        lastValue = varCounter;
        return varCounter;
    }

    public int getLastValue() {
        return varCounter;
    }

    public void setLastValue(int ord) {
        lastValue = ord;
    }
}
