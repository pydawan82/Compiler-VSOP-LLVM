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

    private int varCounter = -1;
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

    /**
     * 
     * @param fieldId
     * @return
     */
    public int ordinalOfField(String fieldId) {
        return fieldOrdinal.get(fieldId);
    }

    /**
     * 
     * @param method
     * @return
     */
    public int ordinalOf(VSOPMethod method) {
        return methodOrdinal.get(method);
    }

    /**
     * 
     * @param variable
     * @return
     */
    public boolean push(String variable) {
        String value = valueOf(variable);
        if(value != null) {
            setLastValue(value);
        }

        return value!=null;
    }

    /**
     * 
     * @param variable
     * @return
     */
    public String valueOf(String variable) {
        return varValue.get(variable);
    }

    /**
     * 
     * @param variable
     * @return
     */
    public int updateVariable(String variable) {
        varCounter++;
        varValue.put(variable, var(varCounter));
        return varCounter;
    }

    /**
     * 
     * @param variable
     * @param ord
     */
    public void setOrdinalOf(String variable, int ord) {
        varValue.put(variable, var(ord));
    }

    /**
     * 
     * @param variable
     * @param value
     */
    public void setValueOf(String variable, String value) {
        varValue.put(variable, value);
    }

    /**
     * 
     * @return
     */
    public int unnamed() {
        varCounter++;
        setLastValue(varCounter);
        return varCounter;
    }

    /**
     * 
     * @return
     */
    public String getLastValue() {
        return lastValue;
    }

    /**
     * 
     * @param value
     */
    public void setLastValue(int value) {
        lastValue = var(value);
    }

    /**
     * 
     * @param value
     */
    public void setLastValue(String value) {
        lastValue = value;
    }

    private final String nulChar = "\\00";
    private final Pattern xescape = Pattern.compile("\\\\x(([0-9]|[a-f]|[A-F])([0-9]|[a-f]|[A-F]))");
    
    /**
     * 
     * @param val
     * @return
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
     * 
     * @param str
     * @return
     */
    private int strSize(String str) {
        String t = escape.matcher(str).replaceAll("a");
        return t.length()-2;
    }

    /**
     * 
     * @return
     */
    public String stringDeclarations() {
        return String.join(System.lineSeparator(), consStrings);
    }
}
