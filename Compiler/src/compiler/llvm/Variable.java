package compiler.llvm;

import compiler.vsop.VSOPType;

/**
 * A class that represent a variable.
 * Not sure if this will be useful
 */
public class Variable<Type extends VSOPType> {
    private final String id;
    private String value;

    /**
     * Creates a new variable with the given id and value
     * @param id - The id of this {@link Variable}
     * @param value - The value of this {@link Variable}
     */
    public Variable(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Creates a new variable with the default value according to VSOP standards
     * @param id - The id of this {@link Variable}
     */
    public Variable(String id) {
        this(id, "");
    }

    /**
     * 
     * @return - The id of this {@link Variable}
     */
    public String id() {
        return id;
    }

    /**
     * Sets the value of this {@link Variable}
     * @param value - The new value of this {@link Variable}
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * 
     * @return - The current value of this {@link Variable}
     */
    public String getValue() {
        return value;
    }
}
