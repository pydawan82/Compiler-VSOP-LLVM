parser grammar VSOPParser;

options { tokenVocab = VSOPLexer;}

type: INT | FLOAT
	;
	
unop: NOT;
bop: ADD | SUB | MUL | DIV | EQ | EQQ | OR;
literal
	: INT_LITERAL
	| FLOAT_LITERAL
	| STRING_LITERAL
	;
	
expression
	: ID
	| literal
	| fcall
	| unop expression
	| expression bop expression
	| LPAR expression RPAR
	;

instruction: (expression | declaration) SEMI ;	

declaration: INT ID (EQ expression)?;

parameters
	: (expression)(COMMA expression)*
	|
	;

fcall: ID LPAR parameters RPAR;
