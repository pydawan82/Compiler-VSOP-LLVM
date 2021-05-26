# Compiler-VSOP-LLVM

**Project from the INFO0085-1 Compilers course at ULiege**
A VSOP Compiler written in JAVA

## Usage
```bash
vsopc [-e]? [-l|-p|-c|-i]? [input_file]
```

## -l: Lexing
+ ✅ Outputs tokens on the standard output
+ ✅ Outputs lexical errors on the standard error

## -p: Parsing
+ ✅ Outputs parse tree on the standard output
+ ✅ Outputs lexical and syntax errors on the standard error
+ ❌ Cannot tell if a given file contains only a program and nothing else. If the file is parasited, the compiler will ignore some part of the file.

## -c: Semantic checking
+ ✅ Outputs the anotated AST on the standard output
+ ✅ Outputs lexical, syntax and semantical errors on the standard error
+ ❌ Unstability might raise from syntax errors

## -i: LLVM IR Generation
+ ✅ Outputs the generated LLVM IR on the standard output
+ ✅ Outputs errors on the standard error
+ ❌ Generation is sometimes buggy and might not be perfect

## -e: Extension
+ ❌ No extensions supported yet


## Compilation
+ ✅ Generates an LLVM IR file
+ ✅ Compiles the file with CLang
+ ✅ VSOP errors and CLang errors are printed on the standard error
