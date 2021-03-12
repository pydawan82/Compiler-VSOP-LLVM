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
	: IF ex+=expr THEN ex+=expr (ELSE ex+=expr)? #if
	| WHILE ex1=expr DO ex2=expr #while
	| LET id=OBJECT_IDENTIFIER COLON type (ASSIGN as=expr) IN ex=expr #let
	| id=OBJECT_IDENTIFIER ASSIGN ex=expr #ass
	| NOT expr #not
	| expr AND expr #binop
	| expr (EQUAL|LOWER|LOWER_EQUAL) expr #binop
	| expr (PLUS|MINUS) expr #binop
	| expr (TIMES|DIV) expr #binop
	| expr POW expr #binop
	| MINUS expr #minus
	| ISNULL expr #isnull
	| id=OBJECT_IDENTIFIER LPAR args RPAR #selfcall
	| expr DOT id=OBJECT_IDENTIFIER LPAR args RPAR #call
	| NEW id=TYPE_IDENTIFIER #new
	| id=OBJECT_IDENTIFIER #oi
	| SELF #self
	| literal #lit
	| RPAR LPAR #unit
	| LPAR expr RPAR #braceExpr
	| block #bl
	;

args: (ex+=expr (COMMA ex+=expr)*)?;
literal: INTEGER_LITERAL|STRING_LITERAL|booleanLiteral;
booleanLiteral: TRUE|FALSE;