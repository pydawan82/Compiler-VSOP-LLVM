parser grammar VSOPParser;
options{tokenVocab = VSOPLexer;}

program: clazz+;
clazz: CLASS id=TYPE_IDENTIFIER (EXTENDS idext=TYPE_IDENTIFIER)? classBody;
classBody: LBRACE (field|method)* RBRACE;
field: id=OBJECT_IDENTIFIER COLON type (ASSIGN expr)? SEMICOLON;
method: id=OBJECT_IDENTIFIER LPAR formals RPAR COLON type block;
type
	: TYPE_IDENTIFIER
	| INT32
	| BOOL
	| STRING
	| UNIT
	;

formals: (formal (COMMA formal)*)?;
formal: id=OBJECT_IDENTIFIER COLON type;
block: LBRACE (expr (SEMICOLON expr)*)? RBRACE;
expr
	: IF expr THEN expr (ELSE expr)?
	| WHILE expr DO expr
	| LET OBJECT_IDENTIFIER COLON type (ASSIGN expr) IN expr
	| OBJECT_IDENTIFIER ASSIGN expr
	| NOT expr
	| expr AND expr
	| expr (EQUAL|LOWER|LOWER_EQUAL) expr
	| expr (PLUS|MINUS) expr
	| expr (TIMES|DIV) expr
	| expr POW expr
	| MINUS expr
	| ISNULL expr
	| OBJECT_IDENTIFIER LPAR args RPAR
	| expr DOT OBJECT_IDENTIFIER LPAR args RPAR
	| NEW TYPE_IDENTIFIER
	| OBJECT_IDENTIFIER
	| SELF
	| literal
	| RPAR LPAR
	| LPAR expr RPAR
	| block
	;

args: (expr (COMMA expr)*)?;
literal: INTEGER_LITERAL|STRING_LITERAL|booleanLiteral;
booleanLiteral: TRUE|FALSE;