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
SINGLELINE_COMMENT: '//' .*? ('\n'|EOF) -> channel(HIDDEN); // Ajouter EOF aussi mais je sais pas comment le noter

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

TYPE_IDENTIFIER: UppercaseLetter (Letter|Digit|'_')*;
OBJECT_IDENTIFIER: LowercaseLetter (Letter|Digit|'_')*;

/*
 * Literals
 */

fragment HexLitteral: '0x' HexDigit+;
fragment DecimalLitteral: Digit+;

INTEGER_LITERAL
	: Digit+
	| HexLitteral
	;
	
INVALID_HEX: HexLitteral [g-zG-Z]+ {
	getErrorListenerDispatch().syntaxError(this, "", getLine(), getCharPositionInLine(), "Ill formed hex literal", null);
} -> skip;

INVALID_DECIMAL: DecimalLitteral [a-zA-Z]+ {
	getErrorListenerDispatch().syntaxError(this, "", getLine(), getCharPositionInLine(), "Ill formed decimal literal", null);
} -> skip;

fragment LineSkip: '\\' ('\n'|'\r\n') [ \t]*;
fragment EscapeSequence: [btnr"\\] | 'x' HexDigit HexDigit; // \x40
fragment EscapeChar: '\\' EscapeSequence;
fragment RegularChar: ~[\\"\r\n];

STRING_LITERAL: '"' (RegularChar|EscapeChar|LineSkip)*? '"' {
	String s = getText();
	s = s.replaceAll("\\\\(\n|\r\n)( |\t)*", "");
	//s = s.replaceAll("\\\\n", "\\\\x0a");
	//s = s.replaceAll("\\\\r", "\\\\x0d");
	s = s.replaceAll("\\\\\"", "\\\\x22");
	s = s.replaceAll("\\\\\\\\", "\\\\x5c");
	s = s.replaceAll("\\\\b", "\\\\x08");
	s = s.replaceAll("\\\\t", "\\\\x09");
   
	for(int i=0; i<32; i++) {
		s = s.replaceAll("\\x"+String.format("%02x",i),"\\\\x"+String.format("%02x",i));
	}
   
	setText(s);
};

fragment BadEscapeChar: '\\' .;

BAD_ESCAPE_LITERAL: '"' (RegularChar|EscapeChar|LineSkip|BadEscapeChar)*? '"' {
	String[] split = getText().split("\\\\([^xbtnr\\r\\n\\\"\\\\]|x[^0-9a-fA-F]|x[0-9a-fA-F][^0-9a-fA-F])");
	
	String[] split2 = split[0].split("\\n");
	int ln = _tokenStartLine+split2.length-1;
	int col;
	if(split2.length == 1)
		col = _tokenStartCharPositionInLine+split2[0].length();
	else {
		col = split2[split2.length-1].length();
	}
	
	getErrorListenerDispatch().syntaxError(this, "", ln, col, "Unrecognized escape sequence", null);
} -> skip;

RAW_LINEFEED_LITERAL: '"' (RegularChar|EscapeChar|LineSkip|BadEscapeChar|'\n'|'\r')*? '"' {
	String[] split = getText().split("[^\\\\](\\n)");
	String[] split2 = split[0].split("\\n");
	int ln = _tokenStartLine+split2.length-1;
	int col;
	if(split2.length == 1)
		col = _tokenStartCharPositionInLine+split2[0].length();
	else {
		col = split2[split2.length-1].length();
	}
	
	getErrorListenerDispatch().syntaxError(this, "", ln, col, "Unauthorized raw linefeed", null);
} ->skip;


BAD_STRING_LITERAL: '"' (.)*? '"' {
	getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed string literal", null);
} -> skip;

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

LPS: '(*' -> more, mode(MULTI);
mode MULTI;
	MULTILINE_COMMENT: ('(*' MULTILINE_COMMENT|'('~[*]|'*'~[)]|~[(*])*? '*)' -> mode(DEFAULT_MODE), channel(HIDDEN);



/*
 * Bad
 */