; 
; Classes
; 
; Class Nil
%Nil = type {%NilVTable*}
%NilVTable = type {%Object* (%Object*, i1)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i32)*, i1 (%Object*)*, i8* (%Object*)*, i32 (%Object*)*, i32 (%List*)*, i1 (%List*)*}

; Class Cons
%Cons = type {%ConsVTable*, i32, %List*}
%ConsVTable = type {%Object* (%Object*, i1)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i32)*, i1 (%Object*)*, i8* (%Object*)*, i32 (%Object*)*, i32 (%Cons*)*, i1 (%Cons*)*, i32 (%Cons*)*, %Cons* (%Cons*, i32, %List*)*}

; Class Object
%Object = type {%ObjectVTable*}
%ObjectVTable = type {%Object* (%Object*, i1)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i32)*, i1 (%Object*)*, i8* (%Object*)*, i32 (%Object*)*}

; Class List
%List = type {%ListVTable*}
%ListVTable = type {%Object* (%Object*, i1)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i32)*, i1 (%Object*)*, i8* (%Object*)*, i32 (%Object*)*, i32 (%List*)*, i1 (%List*)*}

; Class Main
%Main = type {%MainVTable*}
%MainVTable = type {%Object* (%Object*, i1)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i32)*, i1 (%Object*)*, i8* (%Object*)*, i32 (%Object*)*, i32 (%Main*)*}


; 
; VTables
; 
@Nil___vtable = constant %NilVTable {%Object* (%Object*, i1)* @Object__printBool, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i32)* @Object__printInt32, i1 (%Object*)* @Object__inputBool, i8* (%Object*)* @Object__inputLine, i32 (%Object*)* @Object__inputInt32, i32 (%List*)* @List__length, i1 (%List*)* @List__isNil}
@Cons___vtable = constant %ConsVTable {%Object* (%Object*, i1)* @Object__printBool, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i32)* @Object__printInt32, i1 (%Object*)* @Object__inputBool, i8* (%Object*)* @Object__inputLine, i32 (%Object*)* @Object__inputInt32, i32 (%Cons*)* @Cons__length, i1 (%Cons*)* @Cons__isNil, i32 (%Cons*)* @Cons__head, %Cons* (%Cons*, i32, %List*)* @Cons__init}
@Object___vtable = constant %ObjectVTable {%Object* (%Object*, i1)* @Object__printBool, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i32)* @Object__printInt32, i1 (%Object*)* @Object__inputBool, i8* (%Object*)* @Object__inputLine, i32 (%Object*)* @Object__inputInt32}
@List___vtable = constant %ListVTable {%Object* (%Object*, i1)* @Object__printBool, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i32)* @Object__printInt32, i1 (%Object*)* @Object__inputBool, i8* (%Object*)* @Object__inputLine, i32 (%Object*)* @Object__inputInt32, i32 (%List*)* @List__length, i1 (%List*)* @List__isNil}
@Main___vtable = constant %MainVTable {%Object* (%Object*, i1)* @Object__printBool, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i32)* @Object__printInt32, i1 (%Object*)* @Object__inputBool, i8* (%Object*)* @Object__inputLine, i32 (%Object*)* @Object__inputInt32, i32 (%Main*)* @Main__main}

; 
; Functions
; 
define i32 @Cons__length (%Cons*) {
	%2 = getelementptr %Cons, %Cons* %0, i32 0,i32 2
	%3 = load %List*, %List** %2
	%4 = bitcast %List* %3 to %List*
	%5 = getelementptr %List, %List* %4, i32 0,i32 0
	%6 = load %ListVTable*, %ListVTable** %5
	%7 = getelementptr %ListVTable, %ListVTable* %6, i32 0,i32 6
	%8 = load i32 (%List*)*, i32 (%List*)** %7
	%9 = call i32 %8(%List* %4)
	%10 = add i32 1, %9
	ret i32 %10
}


define i1 @Cons__isNil (%Cons*) {
	ret i1 false
}


define i32 @Cons__head (%Cons*) {
	%2 = getelementptr %Cons, %Cons* %0, i32 0,i32 1
	%3 = load i32, i32* %2
	ret i32 %3
}


define %Cons* @Cons__init (%Cons*, i32, %List*) {
	%4 = getelementptr %Cons, %Cons* %0, i32 0,i32 1
	store i32 %1, i32* %4
	%5 = getelementptr %Cons, %Cons* %0, i32 0,i32 2
	store %List* %2, %List** %5
	ret %Cons* %0
}


define i32 @List__length (%List*) {
	ret i32 0
}


define i1 @List__isNil (%List*) {
	ret i1 true
}


define i32 @Main__main (%Main*) {
	%2 = getelementptr %Cons, %Cons* null, i32 1
	%3 = ptrtoint %Cons* %2 to i64
	%4 = call i8* @malloc(i64 %3)
	%5 = bitcast i8* %4 to %Cons*
	%6 = getelementptr %Cons, %Cons* %5, i32 0,i32 0
	store %ConsVTable* @Cons___vtable, %ConsVTable** %6
	%7 = bitcast %Cons* %5 to %Cons*
	%8 = getelementptr %Cons, %Cons* %7, i32 0,i32 0
	%9 = load %ConsVTable*, %ConsVTable** %8
	%10 = getelementptr %ConsVTable, %ConsVTable* %9, i32 0,i32 9
	%11 = load %Cons* (%Cons*, i32, %List*)*, %Cons* (%Cons*, i32, %List*)** %10
	%12 = bitcast i32 0 to i32
	%13 = getelementptr %Cons, %Cons* null, i32 1
	%14 = ptrtoint %Cons* %13 to i64
	%15 = call i8* @malloc(i64 %14)
	%16 = bitcast i8* %15 to %Cons*
	%17 = getelementptr %Cons, %Cons* %16, i32 0,i32 0
	store %ConsVTable* @Cons___vtable, %ConsVTable** %17
	%18 = bitcast %Cons* %16 to %Cons*
	%19 = getelementptr %Cons, %Cons* %18, i32 0,i32 0
	%20 = load %ConsVTable*, %ConsVTable** %19
	%21 = getelementptr %ConsVTable, %ConsVTable* %20, i32 0,i32 9
	%22 = load %Cons* (%Cons*, i32, %List*)*, %Cons* (%Cons*, i32, %List*)** %21
	%23 = bitcast i32 1 to i32
	%24 = getelementptr %Cons, %Cons* null, i32 1
	%25 = ptrtoint %Cons* %24 to i64
	%26 = call i8* @malloc(i64 %25)
	%27 = bitcast i8* %26 to %Cons*
	%28 = getelementptr %Cons, %Cons* %27, i32 0,i32 0
	store %ConsVTable* @Cons___vtable, %ConsVTable** %28
	%29 = bitcast %Cons* %27 to %Cons*
	%30 = getelementptr %Cons, %Cons* %29, i32 0,i32 0
	%31 = load %ConsVTable*, %ConsVTable** %30
	%32 = getelementptr %ConsVTable, %ConsVTable* %31, i32 0,i32 9
	%33 = load %Cons* (%Cons*, i32, %List*)*, %Cons* (%Cons*, i32, %List*)** %32
	%34 = bitcast i32 2 to i32
	%35 = getelementptr %Nil, %Nil* null, i32 1
	%36 = ptrtoint %Nil* %35 to i64
	%37 = call i8* @malloc(i64 %36)
	%38 = bitcast i8* %37 to %Nil*
	%39 = getelementptr %Nil, %Nil* %38, i32 0,i32 0
	store %NilVTable* @Nil___vtable, %NilVTable** %39
	%40 = bitcast %Nil* %38 to %List*
	%41 = call %Cons* %33(%Cons* %29, i32 %34, %List* %40)
	%42 = bitcast %Cons* %41 to %List*
	%43 = call %Cons* %22(%Cons* %18, i32 %23, %List* %42)
	%44 = bitcast %Cons* %43 to %List*
	%45 = call %Cons* %11(%Cons* %7, i32 %12, %List* %44)
	%46 = bitcast %Cons* %45 to %List*
	%47 = bitcast %Main* %0 to %Object*
	%48 = getelementptr %Object, %Object* %47, i32 0,i32 0
	%49 = load %ObjectVTable*, %ObjectVTable** %48
	%50 = getelementptr %ObjectVTable, %ObjectVTable* %49, i32 0,i32 1
	%51 = load %Object* (%Object*, i8*)*, %Object* (%Object*, i8*)** %50
	%52 = bitcast i8* getelementptr inbounds ([17 x i8], [17 x i8]* @Main__main.str.0, i64 0, i64 0) to i8*
	%53 = call %Object* %51(%Object* %47, i8* %52)
	%54 = bitcast %Main* %0 to %Object*
	%55 = getelementptr %Object, %Object* %54, i32 0,i32 0
	%56 = load %ObjectVTable*, %ObjectVTable** %55
	%57 = getelementptr %ObjectVTable, %ObjectVTable* %56, i32 0,i32 2
	%58 = load %Object* (%Object*, i32)*, %Object* (%Object*, i32)** %57
	%59 = bitcast %List* %46 to %List*
	%60 = getelementptr %List, %List* %59, i32 0,i32 0
	%61 = load %ListVTable*, %ListVTable** %60
	%62 = getelementptr %ListVTable, %ListVTable* %61, i32 0,i32 6
	%63 = load i32 (%List*)*, i32 (%List*)** %62
	%64 = call i32 %63(%List* %59)
	%65 = bitcast i32 %64 to i32
	%66 = call %Object* %58(%Object* %54, i32 %65)
	%67 = bitcast %Main* %0 to %Object*
	%68 = getelementptr %Object, %Object* %67, i32 0,i32 0
	%69 = load %ObjectVTable*, %ObjectVTable** %68
	%70 = getelementptr %ObjectVTable, %ObjectVTable* %69, i32 0,i32 1
	%71 = load %Object* (%Object*, i8*)*, %Object* (%Object*, i8*)** %70
	%72 = bitcast i8* getelementptr inbounds ([2 x i8], [2 x i8]* @Main__main.str.1, i64 0, i64 0) to i8*
	%73 = call %Object* %71(%Object* %67, i8* %72)
	ret i32 0
}

@Main__main.str.0 = constant [17 x i8] c"List has length \00"
@Main__main.str.1 = constant [2 x i8] c"\0a\00"

; 
; Main
; 
define i32 @main () {
	%1 = getelementptr %Main, %Main* null, i32 1
	%2 = ptrtoint %Main* %1 to i64
	%3 = call i8* @malloc(i64 %2)
	%4 = bitcast i8* %3 to %Main*
	%5 = getelementptr %Main, %Main* %4, i32 0,i32 0
	store %MainVTable* @Main___vtable, %MainVTable** %5
	%6 = bitcast %Main* %4 to %Main*
	%7 = getelementptr %Main, %Main* %6, i32 0,i32 0
	%8 = load %MainVTable*, %MainVTable** %7
	%9 = getelementptr %MainVTable, %MainVTable* %8, i32 0,i32 6
	%10 = load i32 (%Main*)*, i32 (%Main*)** %9
	%11 = call i32 %10(%Main* %6)
	ret i32 %11
}



; 
; Runtime
; 
; This file is mostly generated from the C code, with just a touch of cleanup
; by hand to make it more portable and legible.

; Imports from the C library

%_IO_FILE = type opaque

@stdin = external global %_IO_FILE*
@stderr = external global %_IO_FILE*

declare void @exit(i32)
declare i32 @fprintf(%_IO_FILE*, i8*, ...)
declare void @free(i8*)
declare i32 @getc(%_IO_FILE*)
declare i32 @isspace(i32)
declare i8* @malloc(i64)
declare i32 @printf(i8*, ...)
declare i8* @realloc(i8*, i64)
declare i64 @strlen(i8*)
declare i32 @strncmp(i8*, i8*, i64)
declare i64 @strtoll(i8*, i8**, i32)
declare i32 @ungetc(i32, %_IO_FILE*)

; String literals

@.str = constant [3 x i8] c"%s\00"
@.str.1 = constant [5 x i8] c"true\00"
@.str.2 = constant [6 x i8] c"false\00"
@.str.3 = constant [3 x i8] c"%d\00"
@.str.4 = constant [1 x i8] zeroinitializer
@.str.5 = constant [38 x i8] c"Object::inputBool: cannot read word!\0A\00"
@.str.6 = constant [49 x i8] c"Object::inputBool: `%s` is not a valid boolean!\0A\00"
@.str.7 = constant [39 x i8] c"Object::inputInt32: cannot read word!\0A\00"
@.str.8 = constant [58 x i8] c"Object::inputInt32: `%s` is not a valid integer literal!\0A\00"
@.str.9 = constant [57 x i8] c"Object::inputInt32: `%s` does not fit a 32-bit integer!\0A\00"

; Object's methods

define %Object* @Object__print(%Object*, i8*) {
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %1)
  ret %Object* %0
}

define %Object* @Object__printBool(%Object*, i1 zeroext) {
  %3 = zext i1 %1 to i8
  %4 = trunc i8 %3 to i1
  %5 = zext i1 %4 to i64
  %6 = select i1 %4, i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.1, i64 0, i64 0), i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i64 0, i64 0)
  %7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i8* %6)
  ret %Object* %0
}

define %Object* @Object__printInt32(%Object*, i32) {
  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32 %1)
  ret %Object* %0
}

define i8* @Object__inputLine(%Object*) {
  %2 = call i8* @read_until(i32 (i32)* @is_eol)
  %3 = icmp ne i8* %2, null
  br i1 %3, label %5, label %4

4:                                                ; preds = %1
  br label %5

5:                                                ; preds = %4, %1
  %.0 = phi i8* [ %2, %1 ], [ getelementptr inbounds ([1 x i8], [1 x i8]* @.str.4, i64 0, i64 0), %4 ]
  ret i8* %.0
}

define zeroext i1 @Object__inputBool(%Object*) {
  call void @skip_while(i32 (i32)* @isspace)
  %2 = call i8* @read_until(i32 (i32)* @isspace)
  %3 = icmp ne i8* %2, null
  br i1 %3, label %7, label %4

4:                                                ; preds = %1
  %5 = load %_IO_FILE*, %_IO_FILE** @stderr
  %6 = call i32 (%_IO_FILE*, i8*, ...) @fprintf(%_IO_FILE* %5, i8* getelementptr inbounds ([38 x i8], [38 x i8]* @.str.5, i64 0, i64 0))
  call void @exit(i32 1)
  unreachable

7:                                                ; preds = %1
  %8 = call i64 @strlen(i8* %2)
  %9 = icmp eq i64 %8, 4
  br i1 %9, label %10, label %14

10:                                               ; preds = %7
  %11 = call i32 @strncmp(i8* %2, i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.1, i64 0, i64 0), i64 4)
  %12 = icmp eq i32 %11, 0
  br i1 %12, label %13, label %14

13:                                               ; preds = %10
  call void @free(i8* %2)
  br label %23

14:                                               ; preds = %10, %7
  %15 = icmp eq i64 %8, 5
  br i1 %15, label %16, label %20

16:                                               ; preds = %14
  %17 = call i32 @strncmp(i8* %2, i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i64 0, i64 0), i64 5)
  %18 = icmp eq i32 %17, 0
  br i1 %18, label %19, label %20

19:                                               ; preds = %16
  call void @free(i8* %2)
  br label %23

20:                                               ; preds = %16, %14
  %21 = load %_IO_FILE*, %_IO_FILE** @stderr
  %22 = call i32 (%_IO_FILE*, i8*, ...) @fprintf(%_IO_FILE* %21, i8* getelementptr inbounds ([49 x i8], [49 x i8]* @.str.6, i64 0, i64 0), i8* %2)
  call void @free(i8* %2)
  call void @exit(i32 1)
  unreachable

23:                                               ; preds = %19, %13
  %.0 = phi i1 [ true, %13 ], [ false, %19 ]
  ret i1 %.0
}

define i32 @Object__inputInt32(%Object*) {
  %2 = alloca i8*
  call void @skip_while(i32 (i32)* @isspace)
  %3 = call i8* @read_until(i32 (i32)* @isspace)
  %4 = icmp ne i8* %3, null
  br i1 %4, label %8, label %5

5:                                                ; preds = %1
  %6 = load %_IO_FILE*, %_IO_FILE** @stderr
  %7 = call i32 (%_IO_FILE*, i8*, ...) @fprintf(%_IO_FILE* %6, i8* getelementptr inbounds ([39 x i8], [39 x i8]* @.str.7, i64 0, i64 0))
  call void @exit(i32 1)
  unreachable

8:                                                ; preds = %1
  %9 = call i64 @strlen(i8* %3)
  %10 = icmp ugt i64 %9, 2
  br i1 %10, label %11, label %21

11:                                               ; preds = %8
  %12 = getelementptr inbounds i8, i8* %3, i64 0
  %13 = load i8, i8* %12
  %14 = sext i8 %13 to i32
  %15 = icmp eq i32 %14, 48
  br i1 %15, label %16, label %21

16:                                               ; preds = %11
  %17 = getelementptr inbounds i8, i8* %3, i64 1
  %18 = load i8, i8* %17
  %19 = sext i8 %18 to i32
  %20 = icmp eq i32 %19, 120
  br i1 %20, label %45, label %21

21:                                               ; preds = %16, %11, %8
  %22 = icmp ugt i64 %9, 3
  br i1 %22, label %23, label %43

23:                                               ; preds = %21
  %24 = getelementptr inbounds i8, i8* %3, i64 0
  %25 = load i8, i8* %24
  %26 = sext i8 %25 to i32
  %27 = icmp eq i32 %26, 43
  br i1 %27, label %33, label %28

28:                                               ; preds = %23
  %29 = getelementptr inbounds i8, i8* %3, i64 0
  %30 = load i8, i8* %29
  %31 = sext i8 %30 to i32
  %32 = icmp eq i32 %31, 45
  br i1 %32, label %33, label %43

33:                                               ; preds = %28, %23
  %34 = getelementptr inbounds i8, i8* %3, i64 1
  %35 = load i8, i8* %34
  %36 = sext i8 %35 to i32
  %37 = icmp eq i32 %36, 48
  br i1 %37, label %38, label %43

38:                                               ; preds = %33
  %39 = getelementptr inbounds i8, i8* %3, i64 2
  %40 = load i8, i8* %39
  %41 = sext i8 %40 to i32
  %42 = icmp eq i32 %41, 120
  br label %43

43:                                               ; preds = %38, %33, %28, %21
  %44 = phi i1 [ false, %33 ], [ false, %28 ], [ false, %21 ], [ %42, %38 ]
  br label %45

45:                                               ; preds = %43, %16
  %46 = phi i1 [ true, %16 ], [ %44, %43 ]
  %47 = zext i1 %46 to i8
  %48 = trunc i8 %47 to i1
  br i1 %48, label %49, label %51

49:                                               ; preds = %45
  %50 = call i64 @strtoll(i8* %3, i8** %2, i32 16)
  br label %53

51:                                               ; preds = %45
  %52 = call i64 @strtoll(i8* %3, i8** %2, i32 10)
  br label %53

53:                                               ; preds = %51, %49
  %.0 = phi i64 [ %50, %49 ], [ %52, %51 ]
  %54 = load i8*, i8** %2
  %55 = load i8, i8* %54
  %56 = sext i8 %55 to i32
  %57 = icmp ne i32 %56, 0
  br i1 %57, label %58, label %61

58:                                               ; preds = %53
  %59 = load %_IO_FILE*, %_IO_FILE** @stderr
  %60 = call i32 (%_IO_FILE*, i8*, ...) @fprintf(%_IO_FILE* %59, i8* getelementptr inbounds ([58 x i8], [58 x i8]* @.str.8, i64 0, i64 0), i8* %3)
  call void @exit(i32 1)
  unreachable

61:                                               ; preds = %53
  %62 = icmp slt i64 %.0, -2147483648
  br i1 %62, label %65, label %63

63:                                               ; preds = %61
  %64 = icmp sgt i64 %.0, 2147483647
  br i1 %64, label %65, label %68

65:                                               ; preds = %63, %61
  %66 = load %_IO_FILE*, %_IO_FILE** @stderr
  %67 = call i32 (%_IO_FILE*, i8*, ...) @fprintf(%_IO_FILE* %66, i8* getelementptr inbounds ([57 x i8], [57 x i8]* @.str.9, i64 0, i64 0), i8* %3)
  call void @exit(i32 1)
  unreachable

68:                                               ; preds = %63
  %69 = trunc i64 %.0 to i32
  ret i32 %69
}

; Object constructor and initializer

define %Object* @Object___new() {
  %1 = call i8* @malloc(i64 8)
  %2 = bitcast i8* %1 to %Object*
  %3 = call %Object* @Object___init(%Object* %2)
  ret %Object* %3
}

define %Object* @Object___init(%Object*) {
  %2 = icmp ne %Object* %0, null
  br i1 %2, label %3, label %5

3:                                                ; preds = %1
  %4 = getelementptr inbounds %Object, %Object* %0, i32 0, i32 0
  store %ObjectVTable* @Object___vtable, %ObjectVTable** %4
  br label %5

5:                                                ; preds = %3, %1
  ret %Object* %0
}

; Utility functions

define internal i8* @read_until(i32 (i32)*) {
  %2 = call i8* @malloc(i64 1024)
  br label %3

3:                                                ; preds = %29, %1
  %.03 = phi i8* [ %2, %1 ], [ %.14, %29 ]
  %.02 = phi i64 [ 1024, %1 ], [ %.1, %29 ]
  %.01 = phi i64 [ 0, %1 ], [ %30, %29 ]
  %4 = icmp ne i8* %.03, null
  br i1 %4, label %5, label %31

5:                                                ; preds = %3
  %6 = load %_IO_FILE*, %_IO_FILE** @stdin
  %7 = call i32 @getc(%_IO_FILE* %6)
  %8 = icmp eq i32 %7, -1
  br i1 %8, label %14, label %9

9:                                                ; preds = %5
  %10 = trunc i32 %7 to i8
  %11 = sext i8 %10 to i32
  %12 = call i32 %0(i32 %11)
  %13 = icmp ne i32 %12, 0
  br i1 %13, label %14, label %21

14:                                               ; preds = %9, %5
  %15 = icmp ne i32 %7, -1
  br i1 %15, label %16, label %19

16:                                               ; preds = %14
  %17 = load %_IO_FILE*, %_IO_FILE** @stdin
  %18 = call i32 @ungetc(i32 %7, %_IO_FILE* %17)
  br label %19

19:                                               ; preds = %16, %14
  %20 = getelementptr inbounds i8, i8* %.03, i64 %.01
  store i8 0, i8* %20
  br label %32

21:                                               ; preds = %9
  %22 = trunc i32 %7 to i8
  %23 = getelementptr inbounds i8, i8* %.03, i64 %.01
  store i8 %22, i8* %23
  %24 = sub i64 %.02, 1
  %25 = icmp eq i64 %.01, %24
  br i1 %25, label %26, label %29

26:                                               ; preds = %21
  %27 = mul i64 %.02, 2
  %28 = call i8* @realloc(i8* %.03, i64 %27)
  br label %29

29:                                               ; preds = %26, %21
  %.14 = phi i8* [ %28, %26 ], [ %.03, %21 ]
  %.1 = phi i64 [ %27, %26 ], [ %.02, %21 ]
  %30 = add i64 %.01, 1
  br label %3

31:                                               ; preds = %3
  br label %32

32:                                               ; preds = %31, %19
  %.0 = phi i8* [ %.03, %19 ], [ null, %31 ]
  ret i8* %.0
}

define internal i32 @is_eol(i32) {
  %2 = icmp eq i32 %0, 10
  %3 = zext i1 %2 to i32
  ret i32 %3
}

define internal void @skip_while(i32 (i32)*) {
  %2 = load %_IO_FILE*, %_IO_FILE** @stdin
  %3 = call i32 @getc(%_IO_FILE* %2)
  br label %4

4:                                                ; preds = %11, %1
  %.0 = phi i32 [ %3, %1 ], [ %13, %11 ]
  %5 = icmp ne i32 %.0, -1
  br i1 %5, label %6, label %9

6:                                                ; preds = %4
  %7 = call i32 %0(i32 %.0)
  %8 = icmp ne i32 %7, 0
  br label %9

9:                                                ; preds = %6, %4
  %10 = phi i1 [ false, %4 ], [ %8, %6 ]
  br i1 %10, label %11, label %14

11:                                               ; preds = %9
  %12 = load %_IO_FILE*, %_IO_FILE** @stdin
  %13 = call i32 @getc(%_IO_FILE* %12)
  br label %4

14:                                               ; preds = %9
  %15 = icmp ne i32 %.0, -1
  br i1 %15, label %16, label %19

16:                                               ; preds = %14
  %17 = load %_IO_FILE*, %_IO_FILE** @stdin
  %18 = call i32 @ungetc(i32 %.0, %_IO_FILE* %17)
  br label %19

19:                                               ; preds = %16, %14
  ret void
}
