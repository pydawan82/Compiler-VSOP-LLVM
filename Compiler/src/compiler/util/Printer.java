package compiler.util;

import java.io.PrintStream;

@FunctionalInterface
public interface Printer {

    public void print(PrintStream out);
    
}
