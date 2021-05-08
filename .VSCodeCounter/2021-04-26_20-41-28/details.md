# Details

Date : 2021-04-26 20:41:28

Directory e:\Data\David\git\Compiler-VSOP-LLVM

Total : 49 files,  4435 codes, 670 comments, 849 blanks, all 5954 lines

[summary](results.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [Compiler/Makefile](/Compiler/Makefile) | Makefile | 22 | 0 | 7 | 29 |
| [Compiler/grammars/VSOPLexer.g4](/Compiler/grammars/VSOPLexer.g4) | ANTLR | 156 | 24 | 48 | 228 |
| [Compiler/grammars/VSOPParser.g4](/Compiler/grammars/VSOPParser.g4) | ANTLR | 47 | 0 | 5 | 52 |
| [Compiler/src/compiler/ClassVisitor.java](/Compiler/src/compiler/ClassVisitor.java) | Java | 171 | 8 | 55 | 234 |
| [Compiler/src/compiler/Compiler.java](/Compiler/src/compiler/Compiler.java) | Java | 155 | 35 | 35 | 225 |
| [Compiler/src/compiler/CustomVisitor.java](/Compiler/src/compiler/CustomVisitor.java) | Java | 328 | 0 | 108 | 436 |
| [Compiler/src/compiler/SemanticVisitor.java](/Compiler/src/compiler/SemanticVisitor.java) | Java | 361 | 0 | 113 | 474 |
| [Compiler/src/compiler/VariableStack.java](/Compiler/src/compiler/VariableStack.java) | Java | 34 | 0 | 12 | 46 |
| [Compiler/src/compiler/ast/ASTAss.java](/Compiler/src/compiler/ast/ASTAss.java) | Java | 25 | 0 | 8 | 33 |
| [Compiler/src/compiler/ast/ASTBinop.java](/Compiler/src/compiler/ast/ASTBinop.java) | Java | 30 | 0 | 11 | 41 |
| [Compiler/src/compiler/ast/ASTBlock.java](/Compiler/src/compiler/ast/ASTBlock.java) | Java | 38 | 0 | 14 | 52 |
| [Compiler/src/compiler/ast/ASTCall.java](/Compiler/src/compiler/ast/ASTCall.java) | Java | 46 | 1 | 14 | 61 |
| [Compiler/src/compiler/ast/ASTClass.java](/Compiler/src/compiler/ast/ASTClass.java) | Java | 55 | 0 | 22 | 77 |
| [Compiler/src/compiler/ast/ASTExpr.java](/Compiler/src/compiler/ast/ASTExpr.java) | Java | 8 | 0 | 6 | 14 |
| [Compiler/src/compiler/ast/ASTField.java](/Compiler/src/compiler/ast/ASTField.java) | Java | 27 | 0 | 13 | 40 |
| [Compiler/src/compiler/ast/ASTFormal.java](/Compiler/src/compiler/ast/ASTFormal.java) | Java | 22 | 0 | 9 | 31 |
| [Compiler/src/compiler/ast/ASTIf.java](/Compiler/src/compiler/ast/ASTIf.java) | Java | 36 | 1 | 13 | 50 |
| [Compiler/src/compiler/ast/ASTIsnull.java](/Compiler/src/compiler/ast/ASTIsnull.java) | Java | 19 | 1 | 9 | 29 |
| [Compiler/src/compiler/ast/ASTLet.java](/Compiler/src/compiler/ast/ASTLet.java) | Java | 34 | 0 | 11 | 45 |
| [Compiler/src/compiler/ast/ASTLiteral.java](/Compiler/src/compiler/ast/ASTLiteral.java) | Java | 21 | 1 | 8 | 30 |
| [Compiler/src/compiler/ast/ASTMethod.java](/Compiler/src/compiler/ast/ASTMethod.java) | Java | 47 | 0 | 21 | 68 |
| [Compiler/src/compiler/ast/ASTMinus.java](/Compiler/src/compiler/ast/ASTMinus.java) | Java | 19 | 0 | 8 | 27 |
| [Compiler/src/compiler/ast/ASTNew.java](/Compiler/src/compiler/ast/ASTNew.java) | Java | 20 | 0 | 8 | 28 |
| [Compiler/src/compiler/ast/ASTNode.java](/Compiler/src/compiler/ast/ASTNode.java) | Java | 17 | 0 | 9 | 26 |
| [Compiler/src/compiler/ast/ASTNot.java](/Compiler/src/compiler/ast/ASTNot.java) | Java | 22 | 1 | 9 | 32 |
| [Compiler/src/compiler/ast/ASTOi.java](/Compiler/src/compiler/ast/ASTOi.java) | Java | 21 | 0 | 9 | 30 |
| [Compiler/src/compiler/ast/ASTProgram.java](/Compiler/src/compiler/ast/ASTProgram.java) | Java | 28 | 1 | 9 | 38 |
| [Compiler/src/compiler/ast/ASTSelf.java](/Compiler/src/compiler/ast/ASTSelf.java) | Java | 15 | 1 | 8 | 24 |
| [Compiler/src/compiler/ast/ASTWhile.java](/Compiler/src/compiler/ast/ASTWhile.java) | Java | 27 | 0 | 9 | 36 |
| [Compiler/src/compiler/llvm/Context.java](/Compiler/src/compiler/llvm/Context.java) | Java | 11 | 0 | 6 | 17 |
| [Compiler/src/compiler/llvm/LLVMGenerator.java](/Compiler/src/compiler/llvm/LLVMGenerator.java) | Java | 18 | 0 | 7 | 25 |
| [Compiler/src/compiler/llvm/Variable.java](/Compiler/src/compiler/llvm/Variable.java) | Java | 12 | 0 | 4 | 16 |
| [Compiler/src/compiler/parsing/VSOPLexer.java](/Compiler/src/compiler/parsing/VSOPLexer.java) | Java | 469 | 4 | 52 | 525 |
| [Compiler/src/compiler/parsing/VSOPParser.java](/Compiler/src/compiler/parsing/VSOPParser.java) | Java | 1,597 | 4 | 50 | 1,651 |
| [Compiler/src/compiler/parsing/VSOPParserBaseListener.java](/Compiler/src/compiler/parsing/VSOPParserBaseListener.java) | Java | 68 | 316 | 4 | 388 |
| [Compiler/src/compiler/parsing/VSOPParserListener.java](/Compiler/src/compiler/parsing/VSOPParserListener.java) | Java | 62 | 271 | 2 | 335 |
| [Compiler/src/compiler/util/Chrono.java](/Compiler/src/compiler/util/Chrono.java) | Java | 22 | 0 | 8 | 30 |
| [Compiler/src/compiler/util/Node.java](/Compiler/src/compiler/util/Node.java) | Java | 10 | 0 | 4 | 14 |
| [Compiler/src/compiler/util/Pair.java](/Compiler/src/compiler/util/Pair.java) | Java | 9 | 0 | 4 | 13 |
| [Compiler/src/compiler/util/Tree.java](/Compiler/src/compiler/util/Tree.java) | Java | 18 | 0 | 7 | 25 |
| [Compiler/src/compiler/vsop/SemanticError.java](/Compiler/src/compiler/vsop/SemanticError.java) | Java | 35 | 0 | 12 | 47 |
| [Compiler/src/compiler/vsop/VSOPBinOp.java](/Compiler/src/compiler/vsop/VSOPBinOp.java) | Java | 11 | 0 | 3 | 14 |
| [Compiler/src/compiler/vsop/VSOPClass.java](/Compiler/src/compiler/vsop/VSOPClass.java) | Java | 104 | 0 | 34 | 138 |
| [Compiler/src/compiler/vsop/VSOPConstants.java](/Compiler/src/compiler/vsop/VSOPConstants.java) | Java | 43 | 0 | 11 | 54 |
| [Compiler/src/compiler/vsop/VSOPField.java](/Compiler/src/compiler/vsop/VSOPField.java) | Java | 44 | 0 | 10 | 54 |
| [Compiler/src/compiler/vsop/VSOPMethod.java](/Compiler/src/compiler/vsop/VSOPMethod.java) | Java | 18 | 0 | 8 | 26 |
| [Compiler/src/compiler/vsop/VSOPType.java](/Compiler/src/compiler/vsop/VSOPType.java) | Java | 12 | 0 | 4 | 16 |
| [Compiler/vsopc.sh](/Compiler/vsopc.sh) | Shell Script | 3 | 1 | 2 | 6 |
| [README.md](/README.md) | Markdown | 18 | 0 | 6 | 24 |

[summary](results.md)