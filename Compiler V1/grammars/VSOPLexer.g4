lexer grammar VSOPLexer;

WS: [ \t\r\n]-> skip;


/*
 * Primitive types
 */
INT: 'int';
FLOAT: 'float';
/*
 * Seprators
 */
LPAR: '(';
RPAR: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';
SEMI: ';';
COMMA: ',';

/*
 * Operators
 */
NOT: '!'; 
 
ADD: '+';
SUB: '-';	
MUL: '*';
DIV: '/';
EQ: '=';
EQQ: '==';
OR: '||';

DOT: '.';

/*
 * Literals
 */
INT_LITERAL: Digit+;

FLOAT_LITERAL
	: Digit* DOT Digit+
	| Digit+ DOT Digit*
	;
	
STRING_LITERAL: '"'(Letter|Digit)*'"';

ID: Letter (Letter|Digit)*;

fragment Digit: [0-9];
fragment Letter: [a-zA-Z];

