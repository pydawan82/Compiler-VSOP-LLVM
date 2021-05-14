package compiler.util;

import java.io.PrintStream;
import java.util.function.Consumer;

public final class PrintUtil {
    
    private PrintUtil() {}

    private static final String PRINT = "#p";
    private static final String NEWLINE = "%n";

    @SafeVarargs
    public static void format(PrintStream out, String format, Consumer<PrintStream> ... args) {
        format = format.replaceAll(NEWLINE, System.lineSeparator());
        
        int n = 0;
        int end = 0;
        int begin = 0; 
        while((end = format.indexOf(PRINT, begin)) != -1) {
            if(n >= args.length)
                throw new IndexOutOfBoundsException("Too few arguments were given");
            
            out.print(format.substring(begin, end));
            args[n].accept(out);

            n++;
            begin = end+PRINT.length();
        }

        out.print(format.substring(begin));
    }
}
