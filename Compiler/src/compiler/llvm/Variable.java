package compiler.llvm;

public class Variable {
    public final String id;
    public String value;

    public Variable(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Variable(String id) {
        this(id, "");
    }
}
