parser grammar VSOPParser;
options{tokenVocab = VSOPLexer;}

block: LBRACE RBRACE;
type
	: TYPE_IDENTIFIER
	| BOOL
	| UNIT
	| INT32
	| STRING
	;

class_declaration: CLASS TYPE_IDENTIFIER (EXTENDS TYPE_IDENTIFIER)? block;
method_declaration: OBJECT_IDENTIFIER LPAR /* Argument */ RPAR COLON type block;



assignment: OBJECT_IDENTIFIER ASSIGN expression;
args
	: 
	| expression (COMMA expression)*
	;
dispatch
	: OBJECT_IDENTIFIER DOT OBJECT_IDENTIFIER LPAR args RPAR
	| OBJECT_IDENTIFIER LPAR args RPAR
	;
conditional: IF expression THEN expression (ELSE expression)?;
loop: WHILE expression DO expression;

expression:;