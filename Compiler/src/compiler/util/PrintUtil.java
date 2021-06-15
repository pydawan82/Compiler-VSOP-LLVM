package compiler.util;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PrintUtil {

    private static final Pattern pattern = Pattern.compile("#(.)");

    private static final String print = "p";
    private static final String newline = "n";
    private static final String hash = "#";

    @SafeVarargs
    public static void pformat(PrintStream stream, String format, Printer ... printers) {
        Matcher matcher = pattern.matcher(format);
        int i = 0;
        int begin = 0;
        int end = 0;

        while(matcher.find()) {
            end = matcher.start();
            stream.print(format.substring(begin, end));

            String option = matcher.group(1);
            switch(option) {
                case print:
                    if(i >= printers.length)
                        throw new MissingFormatArgumentException("#p");
                    printers[i].print(stream);
                    i++;
                    break;
                
                case newline:
                    stream.println();
                    break;
                
                case hash:
                    stream.print(hash);
                    break;
                
                default:
                    throw new UnknownFormatConversionException(option);
            }

            begin = matcher.end();
        }

        stream.print(format.substring(begin));
    }

    @SafeVarargs
    public static void pjoin(PrintStream stream, String separator, Printer ... printers) {
        int i = 0;

        for(var printer: printers) {
            i++;
            printer.print(stream);
            if(i < printers.length)
                stream.print(separator);
        }
    }

    public static void join(PrintStream stream, String separator, Iterable<Printer> printers) {
        Iterator<Printer> it = printers.iterator();

        if(!it.hasNext())
            return;

        while(true) {
            it.next().print(stream);

            if(it.hasNext())
                stream.print(separator);
            else
                break;
        }
    }
}
