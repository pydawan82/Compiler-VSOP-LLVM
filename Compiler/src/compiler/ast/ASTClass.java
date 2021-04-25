package compiler.ast;

import java.io.PrintStream;
import java.util.List;


import compiler.vsop.VSOPClass;

public class ASTClass extends ASTNode {

    VSOPClass vsopClass;
    List<ASTField> fields;
    List<ASTMethod> methods;
    

    public ASTClass(
            VSOPClass vsopClass,
            List<ASTField> fields,
            List<ASTMethod> methods
        )
    {
        this.vsopClass = vsopClass;
        this.fields = fields;
        this.methods = methods;
    }

    @Override
    public void visit() {
        
    }

    @Override
    public void print(PrintStream pStream) {
        pStream.printf("Class(%s, %s, ", vsopClass.id, vsopClass.superClass.id);

        pStream.print('[');

        int i = 0;
        for (var field : fields) {
            i++;
            pStream.println();

            field.print(pStream);

            if (i != fields.size()) {
                pStream.print(",");
            }
        }

        pStream.print("], ");

        if (fields.size() != 0) {
            pStream.println();
        }

        pStream.print('[');

        i = 0;
        for (var method : methods) {
            i++;
            pStream.println();
            
            method.print(pStream);

            if (i != methods.size()) {
                pStream.print(",");
            }
        }
        pStream.print(']');

		pStream.print(')');
    }
    
}