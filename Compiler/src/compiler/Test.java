package compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    
    public static void t() {
        Pattern delim = Pattern.compile("(\\(\\*))|(\\(\\*)");
        Matcher matcher = delim.matcher("");
        matcher.results()
                .map(result -> result.start());
    }
}
