package compiler.llvm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPField;
import compiler.vsop.VSOPMethod;

import static compiler.llvm.LLVMFormatter.*;

/**
 * A context class to store any data related to code generation
 * and current context of the VSOP program.
 */
public class Context {

    private static final String SELF = "self";

    /**
     * A map from defined class'names to their corresponding {@link VSOPClass}
     * TODO Probably to be removed
     */
    private final Map<String, VSOPClass> classMap;

    private final Map<String, Integer> fieldOrdinal = new HashMap<>();
    private final Map<String, Integer> methodOrdinal = new HashMap<>();
    private final Map<String, Integer> varOrdinal = new HashMap<>();

    private int varCounter = 0;
    private String lastValue = "bad";

    public final VSOPMethod method;

    /**
     * Creates a new context given a map of classes and a map of variables
     * @param classMap - a map containing the defined classes
     * @param vars - a map containing the defined variables
     */
    public Context(Map<String, VSOPClass> classMap, VSOPMethod method) {
        this.classMap = Objects.requireNonNull(classMap);
        this.method = Objects.requireNonNull(method);

        List<VSOPField> fields = method.getParent().fieldList();
        int ord = 0;
        for(VSOPField f: fields) {
            fieldOrdinal.put(f.id, ord);
            ord++;
        }
        
        updateVariable(SELF);
        for(VSOPField arg: method.args)
            updateVariable(arg.id);
    }

    public int ordinalOfField(String fieldId) {
        return fieldOrdinal.get(fieldId);
    }

    public int ordinalOf(VSOPMethod method) {
        return methodOrdinal.get(method.id);
    }

    public boolean push(String variable) {
        int ord = ordinalOf(variable);
        if(ord != -1) {
            setLastValue(ord);
            return true;
        }

        return false;
    }

    public int ordinalOf(String variable) {
        Integer ordinal = varOrdinal.get(variable);
        
        if(ordinal == null)
            return -1;

        return ordinal;
    }

    public int updateVariable(String variable) {
        varCounter++;
        varOrdinal.put(variable, varCounter);
        return varCounter;
    }

    public int unnamed() {
        varCounter++;
        setLastValue(varCounter);
        return varCounter;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(int value) {
        lastValue = var(value);
    }

    public void setLastValue(String value) {
        lastValue = value;
    }
}
