package compiler.llvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    /**
     * A map from defined class'names to their corresponding {@link VSOPClass}
     * TODO Probably to be removed
     */
    private final Map<String, VSOPClass> classMap;

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
        this.classMap = Objects.requireNonNull(classMap);
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

    public int ordinalOfField(String fieldId) {
        return fieldOrdinal.get(fieldId);
    }

    public int ordinalOf(VSOPMethod method) {
        return methodOrdinal.get(method);
    }

    public boolean push(String variable) {
        String value = valueOf(variable);
        if(value != null) {
            setLastValue(value);
        }

        return value!=null;
    }

    public String valueOf(String variable) {
        return varValue.get(variable);
    }

    public int updateVariable(String variable) {
        varCounter++;
        varValue.put(variable, var(varCounter));
        return varCounter;
    }

    public void setOrdinalOf(String variable, int ord) {
        varValue.put(variable, var(ord));
    }

    public void setValueOf(String variable, String value) {
        varValue.put(variable, value);
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

    private final String nulChar = "\\00";
    private final Pattern xescape = Pattern.compile("\\\\x(([0-9]|[a-f]|[A-F])([0-9]|[a-f]|[A-F]))");
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
    private int strSize(String str) {
        String t = escape.matcher(str).replaceAll("a");
        return t.length()-2;
    }

    public String stringDeclarations() {
        return String.join(System.lineSeparator(), consStrings);
    }
}
