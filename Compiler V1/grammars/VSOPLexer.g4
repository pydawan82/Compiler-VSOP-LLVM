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
 * Comments
 */
SINGLELINE_COMMENT: '//' .*? ('\n'|EOF) -> skip; // Ajouter EOF aussi mais je sais pas comment le noter
MULTILINE_COMMENT: '(*' (MULTILINE_COMMENT|.)*? '*)' -> skip; // (* (* azeaz *)  azeaze azeazeaz *) 
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

TYPE_INDENTIFIER: UppercaseLetter (Letter|Digit|'_')*;
OBJECT_IDENTIFIER: LowercaseLetter (Letter|Digit|'_')*;

/*
 * Literals
 */
 
INTEGER_LITERAL
	: Digit+
	| '0x' HexDigit+
	;

fragment LineSkip: '\\' ('\n'|'\r\n') [ \t]*;
fragment EscapeSequence: [btnr"\\] | 'x' HexDigit HexDigit; // \x40
fragment EscapeChar: '\\' EscapeSequence;
fragment RegularChar: ~[\\"];

STRING_LITERAL: '"' (RegularChar|EscapeChar|LineSkip)*? '"' {
	String s = getText();
	s = s.replaceAll("\\\\(\n|\r\n)( |\t)*", "");
	s = s.replaceAll("\\\\n", "\\\\x0a");
	s = s.replaceAll("\\\\r", "\\\\x0d");
	s = s.replaceAll("\\\\\"", "\\\\x22");
	s = s.replaceAll("\\\\\\\\", "\\\\x5c");
	s = s.replaceAll("\\\\b", "\\\\x08");
	s = s.replaceAll("\\\\t", "\\\\x09");
   
	for(int i=0; i<32; i++) {
		s = s.replaceAll("\\x"+String.format("%02x",i),"\\\\x"+String.format("%02x",i));
	}
   
	setText(s);
};

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


/*
 * Whitespaces
 */
WS: [ \t\n\f\r]+ -> skip;

/*
 * Bad
 */