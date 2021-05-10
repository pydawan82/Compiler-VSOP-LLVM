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
    public final Map<String, VSOPClass> classMap;

    /**
     * A map from defined variables names to their corresponding {@link Variable}
     */
    public final Map<String, Variable<?>> vars;

    public final Map<String, String> param = new HashMap<>();

    public final Map<VSOPField, Integer> fieldOrdinal = new HashMap<>();
    public final Map<VSOPMethod, Integer> methodOrdinal = new HashMap<>();

    /**
     * Creates a new context given a map of classes and a map of variables
     * @param classMap - a map containing the defined classes
     * @param vars - a map containing the defined variables
     */
    public Context(Map<String, VSOPClass> classMap, Map<String, Variable<?>> vars) {
        this.classMap = Objects.requireNonNull(classMap);
        this.vars = Objects.requireNonNull(vars);
    }
}
