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
block: LBRACE (ex1=expr (SEMICOLON ex2=expr)*)? RBRACE;
expr
	: block #bl
	| LPAR expr RPAR #braceExpr
	| RPAR LPAR #unit
	| literal #lit
	| SELF #self
	| id=OBJECT_IDENTIFIER #oi
	| NEW id=TYPE_IDENTIFIER #new
	| expr DOT id=OBJECT_IDENTIFIER LPAR args RPAR #call
	| id=OBJECT_IDENTIFIER LPAR args RPAR #selfcall
	| expr op=POW expr #binop
	| ISNULL expr #isnull
	| MINUS expr #minus
	| expr op=(TIMES|DIV) expr #binop
	| expr op=(PLUS|MINUS) expr #binop
	| expr op=(EQUAL|LOWER|LOWER_EQUAL) expr #binop
	| NOT expr #not
	| expr op=AND expr #binop
	| id=OBJECT_IDENTIFIER ASSIGN expr #ass
	| LET id=OBJECT_IDENTIFIER COLON type (ASSIGN as=expr)? IN ex=expr #let
	| WHILE expr DO expr #while
	| IF expr THEN expr (ELSE expr)? #if
	
	;

args: (ex+=expr (COMMA ex+=expr)*)?;
literal: INTEGER_LITERAL|STRING_LITERAL|booleanLiteral;
booleanLiteral: TRUE|FALSE;