package compiler.error;

public record SyntaxError(int ln, int col, String message) {
    public static String FILE_NAME = "undefined";

    @Override
    public String toString() {
        return String.format("%s:%d:%d: syntax error: %s", FILE_NAME, ln, col, message)
                + System.lineSeparator();
    }
}