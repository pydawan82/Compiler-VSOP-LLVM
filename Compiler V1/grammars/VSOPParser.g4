parser grammar VSOPParser;

options { tokenVocab = VSOPLexer;}
no: ;
/*type: INT | FLOAT
	;
	
unop: NOT;
bop: ADD | SUB | MUL | DIV | EQ | EQQ | OR;
literal
	: INT_LITERAL
	| FLOAT_LITERAL
	| STRING_LITERAL
	;
signed_literal
	: SIGNED_INT_LITERAL
	| SIGNED_FLOAT_LITERAL
	;
	
expression
	: ID
	| literal
	| fcall
	| unop expression
	| expression signed_literal
	| expression bop expression
	| LPAR expression RPAR
	;

instruction: (expression | declaration) SEMI ;	

declaration: INT ID (EQ expression)?;

parameters
	: (expression)(COMMA expression)*
	|
	;

fcall: ID LPAR parameters RPAR;*/
