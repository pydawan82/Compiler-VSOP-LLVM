package compiler.llvm;

public class CompilationException extends RuntimeException {
    
    public CompilationException() {
        
    }

    public CompilationException(String msg) {
        super(msg);
    }

    public CompilationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
