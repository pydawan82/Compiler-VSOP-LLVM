lexer grammar VSOPLexer;

/*
 * Fragments
 */
fragment LowercaseLetter: [a-z];
fragment UppercaseLetter: [A-Z];
fragment Letter: LowercaseLetter | UppercaseLetter;
fragment BinDigit: [0-1];
fragment Digit: BinDigit | [2-9];
fragment HexDigit: Digit | [a-fA-F];

/*
 * Whitespaces
 */
WS: [ \t\n\f\r]+;

/*
 * Comments
 */
SINGLELINE_COMMENT: '//' .*? [\n]; // Ajouter EOF aussi mais je sais pas comment le noter
MULTILINE_COMMENT: '(*' .*? '*)';
	
/*
 * Keywords
 */
 
AND: 'and';
NOT: 'not';
IN: 'in';

CLASS: 'class';
EXTENDS: 'extends';
ISNULL: 'isnull';
LET: 'let';
NEW: 'new';
SELF: 'self';

IF: 'if';
THEN: 'then';
ELSE: 'else';

WHILE: 'while';
DO: 'do';

TRUE: 'true';
FALSE: 'false';

BOOL: 'bool';
UNIT: 'unit';
INT32: 'int32';
STRING: 'string';

/*
 * Identifiers
 */
OBJECT_IDENTIFIER: LowercaseLetter (Letter|Digit|'_')*;

/*
 * Literals
 */
 
INTEGER_LITERAL
	: Digit+
	| '0x' HexDigit+
	;

fragment EscapeSequence: [btnr"\\] | 'x' HexDigit HexDigit | '\n' [ \t]*;
fragment EscapeChar: '\\' EscapeSequence;
fragment RegularChar: ;

STRING_LITERAL: '"' (RegularChar|EscapeChar)*? '"';

/*
 * Operators
 */

LBRACE: '{';
RBRACE: '}';
LPAR: '(';
RPAR: ')';
COLON: ':';
SEMICOLON: ';';
COMMA: ',';
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
POW: '^';
DOT: '.';
EQUAL: '=';
LOWER: '<';
LOWER_EQUAL: '<=';
ASSIGN: '<-';