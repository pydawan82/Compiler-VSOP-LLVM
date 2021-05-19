; 
; Classes
; 
; Class Nil
%Nil = type {%NilVTable*}
%NilVTable = type {i8* (%Object*)*, i1 (%Object*)*, %Object* (%Object*, i32)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i1)*, i32 (%Object*)*, i32 (%List*)*, i1 (%List*)*}

; Class Cons
%Cons = type {%ConsVTable*, i32, %List*}
%ConsVTable = type {i8* (%Object*)*, i1 (%Object*)*, %Object* (%Object*, i32)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i1)*, i32 (%Object*)*, i32 (%Cons*)*, i1 (%Cons*)*, i32 (%Cons*)*, %Cons* (%Cons*, i32, %List*)*}

; Class Object
%Object = type {%ObjectVTable*}
%ObjectVTable = type {i8* (%Object*)*, i1 (%Object*)*, %Object* (%Object*, i32)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i1)*, i32 (%Object*)*}

; Class List
%List = type {%ListVTable*}
%ListVTable = type {i8* (%Object*)*, i1 (%Object*)*, %Object* (%Object*, i32)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i1)*, i32 (%Object*)*, i32 (%List*)*, i1 (%List*)*}

; Class Main
%Main = type {%MainVTable*}
%MainVTable = type {i8* (%Object*)*, i1 (%Object*)*, %Object* (%Object*, i32)*, %Object* (%Object*, i8*)*, %Object* (%Object*, i1)*, i32 (%Object*)*, i32 (%Main*)*}

; 
; VTables
; 
@Nil___vtable = constant %NilVTable {i8* (%Object*)* @Object__inputLine, i1 (%Object*)* @Object__inputBool, %Object* (%Object*, i32)* @Object__printInt32, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i1)* @Object__printBool, i32 (%Object*)* @Object__inputInt32, i32 (%List*)* @List__length, i1 (%List*)* @List__isNil}
@Cons___vtable = constant %ConsVTable {i8* (%Object*)* @Object__inputLine, i1 (%Object*)* @Object__inputBool, %Object* (%Object*, i32)* @Object__printInt32, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i1)* @Object__printBool, i32 (%Object*)* @Object__inputInt32, i32 (%Cons*)* @Cons__length, i1 (%Cons*)* @Cons__isNil, i32 (%Cons*)* @Cons__head, %Cons* (%Cons*, i32, %List*)* @Cons__init}
@Object___vtable = constant %ObjectVTable {i8* (%Object*)* @Object__inputLine, i1 (%Object*)* @Object__inputBool, %Object* (%Object*, i32)* @Object__printInt32, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i1)* @Object__printBool, i32 (%Object*)* @Object__inputInt32}
@List___vtable = constant %ListVTable {i8* (%Object*)* @Object__inputLine, i1 (%Object*)* @Object__inputBool, %Object* (%Object*, i32)* @Object__printInt32, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i1)* @Object__printBool, i32 (%Object*)* @Object__inputInt32, i32 (%List*)* @List__length, i1 (%List*)* @List__isNil}
@Main___vtable = constant %MainVTable {i8* (%Object*)* @Object__inputLine, i1 (%Object*)* @Object__inputBool, %Object* (%Object*, i32)* @Object__printInt32, %Object* (%Object*, i8*)* @Object__print, %Object* (%Object*, i1)* @Object__printBool, i32 (%Object*)* @Object__inputInt32, i32 (%Main*)* @Main__main}
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
	store %ConsVTable @Cons___vtable, %ConsVTable* %6
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
	store %ConsVTable @Cons___vtable, %ConsVTable* %17
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
	store %ConsVTable @Cons___vtable, %ConsVTable* %28
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
	store %NilVTable @Nil___vtable, %NilVTable* %39
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
	%50 = getelementptr %ObjectVTable, %ObjectVTable* %49, i32 0,i32 3
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
	%70 = getelementptr %ObjectVTable, %ObjectVTable* %69, i32 0,i32 3
	%71 = load %Object* (%Object*, i8*)*, %Object* (%Object*, i8*)** %70
	%72 = bitcast i8* getelementptr inbounds ([2 x i8], [2 x i8]* @Main__main.str.1, i64 0, i64 0) to i8*
	%73 = call %Object* %71(%Object* %67, i8* %72)
	ret i32 0
}

@Main__main.str.0 = constant [17 x i8] c"List has length \00"
@Main__main.str.1 = constant [2 x i8] c"\0a\00"
