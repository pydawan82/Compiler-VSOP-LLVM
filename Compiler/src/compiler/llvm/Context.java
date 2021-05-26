package compiler.llvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import compiler.util.Pair;
import compiler.vsop.VSOPClass;
import compiler.vsop.VSOPField;
import compiler.vsop.VSOPMethod;

import static compiler.llvm.LLVMFormatter.*;
import static compiler.llvm.LLVMConstants.*;

/**
 * A context class to store any data related to code generation
 * and current context of the VSOP program.
 */
public class Context {

    private static final String SELF = "self";

    private final Map<String, Integer> fieldOrdinal = new HashMap<>();
    private final Map<VSOPMethod, Integer> methodOrdinal = new HashMap<>();
    private final Map<String, String> varValue = new HashMap<>();

    protected int varCounter = -1;
    private String lastValue = "bad";

    private int strCounter = -1;
    private List<String> consStrings = new ArrayList<>();

    public final VSOPMethod method;

    /**
     * Creates a new context given a map of classes and a map of variables
     * @param classMap - a map containing the defined classes
     * @param vars - a map containing the defined variables
     */
    public Context(Map<String, VSOPClass> classMap, VSOPMethod method) {
        Objects.requireNonNull(classMap);
        this.method = Objects.requireNonNull(method);

        List<VSOPField> fields = method.getParent().fieldList();
        int ord = 1;
        for(VSOPField f: fields) {
            fieldOrdinal.put(f.id, ord);
            ord++;
        }

        for(VSOPClass clazz: classMap.values()) {
            ord = 0;
            for(VSOPMethod m: clazz.methodList()) {
                methodOrdinal.put(m, ord);
                ord++;
            }
        }
        
        //Adding function variables to scope
        updateVariable(SELF);
        for(VSOPField arg: method.args)
            updateVariable(arg.id);
        
        //Skipping function label
        unnamed();
    }

    public Context(VSOPClass clazz, VSOPMethod m) {
        method = m;

        List<VSOPField> fields = clazz.fieldList();
        int ord = 1;
        for(VSOPField f: fields) {
            fieldOrdinal.put(f.id, ord);
            ord++;
        }
        
        //Label skip
        unnamed();
    }

    /**
     * Get the ordinal of the field passed as argument.
     * @param fieldId Id of the field (String)
     * @return Returns the ordinal of the field.
     */
    public int ordinalOfField(String fieldId) {
        return fieldOrdinal.get(fieldId);
    }

    /**
     * Get the ordinal of the method passed as argument.
     * @param method - Method (VSOPMethod).
     * @return Returns the ordinal of the method.
     */
    public int ordinalOf(VSOPMethod method) {
        return methodOrdinal.get(method);
    }

    /**
     * Push the value of the variable on the lastValue variable.
     * @param variable - Variable as String.
     * @return A boolean stating if the push has succeed or not.
     */
    public boolean push(String variable) {
        String value = valueOf(variable);
        if(value != null) {
            setLastValue(value);
        }

        return value!=null;
    }

    /**
     * Get the value of variable.
     * @param variable - the variable as String.
     * @return Returns the value of the variable as String.
     */
    public String valueOf(String variable) {
        return varValue.get(variable);
    }

    /**
     * Add the variable to the varValue map.
     * @param variable - Variable to update.
     * @return Returns the variable counter.
     */
    public int updateVariable(String variable) {
        varCounter++;
        varValue.put(variable, var(varCounter));
        return varCounter;
    }

    /**
     * Set the ordinal of a variable to the ord variable.
     * @param variable - Variable to change the ordinal of.
     * @param ord - New ordinal of the variable.
     */
    public void setOrdinalOf(String variable, int ord) {
        varValue.put(variable, var(ord));
    }

    /**
     * Set the value of a given variable to a given value
     * @param variable - Variable to be changed.
     * @param value - New value of the variable.
     */
    public void setValueOf(String variable, String value) {
        varValue.put(variable, value);
    }

    /**
     * Get the next unused ordinal.
     * @return Returns the next unused ordinal.
     */
    public int unnamed() {
        varCounter++;
        setLastValue(varCounter);
        return varCounter;
    }

    /**
     * Get the last value.
     * @return Returns the last value.
     */
    public String getLastValue() {
        return lastValue;
    }

    /**
     * Set the last value to a given value (int).
     * @param value - New value of lastValue.
     */
    public void setLastValue(int value) {
        lastValue = var(value);
    }

    /**
     * Set the last value to a given value (String).
     * @param value - New value of lastValue.
     */
    public void setLastValue(String value) {
        lastValue = value;
    }

    private final String nulChar = "\\00";
    private final Pattern xescape = Pattern.compile("\\\\x(([0-9]|[a-f]|[A-F])([0-9]|[a-f]|[A-F]))");
    
    /**
     * Add a new string to the constant string table.
     * @param val - New string to be added.
     * @return Returns a pair of a String (newly added string) and a size (size of the new string).
     */
    public Pair<String, Integer> declareConstString(String val) {
        
        String constant = xescape.matcher(val.substring(0, val.length()-1)).replaceAll("\\\\$1") + nulChar + '"';
        

        strCounter++;
        String var = global(functionId(method.getParent().id, method.id)+".str."+strCounter);
        int size = strSize(constant);
        String assign = assign(var, "constant "+array(CHAR, size)+" c"+constant);
        consStrings.add(assign);
        return new Pair<>(var, size);
    }

    private Pattern escape = Pattern.compile("\\\\([btnr\"\\\\]|(([0-9]|[a-f]|[A-F])([0-9]|[a-f]|[A-F])))");

    /**
     * Get the size of a string (LLVM size).
     * @param str - String you want to know the size of.
     * @return Returns the size of the String.
     */
    private int strSize(String str) {
        String t = escape.matcher(str).replaceAll("a");
        return t.length()-2;
    }

    /**
     * Get the LLVM code for the declarations of constant strings.
     * @return Returns the LLVM code for the declarations of constant strings.
     */
    public String stringDeclarations() {
        return String.join(System.lineSeparator(), consStrings);
    }
}
