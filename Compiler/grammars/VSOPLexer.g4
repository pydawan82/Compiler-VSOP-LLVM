lexer grammar VSOPLexer;

tokens {AND, NOT, IN, CLASS, EXTENDS, ISNULL, LET, NEW, SELF, IF, THEN, ELSE, WHILE, DO, TRUE, FALSE, BOOL, UNIT, INT32, STRING}

@lexer::header {
import java.util.Map;
import java.util.regex.*;
}

@lexer::members { 
Map<String, Integer> keywordMap = Map.ofEntries(
	Map.entry("and", AND),
	Map.entry("not", NOT),
	Map.entry("in", IN),
	Map.entry("class", CLASS),
	Map.entry("extends", EXTENDS),
	Map.entry("isnull", ISNULL),
	Map.entry("let", LET),
	Map.entry("new", NEW),
	Map.entry("self", SELF),
	Map.entry("if", IF),
	Map.entry("then", THEN),
	Map.entry("else", ELSE),
	Map.entry("while", WHILE),
	Map.entry("do", DO),
	Map.entry("true", TRUE),
	Map.entry("false", FALSE),
	Map.entry("bool", BOOL),
	Map.entry("unit", UNIT),
	Map.entry("int32", INT32),
	Map.entry("string", STRING)
);

int commentDepth = 0;
}

fragment LowercaseLetter: [a-z];
fragment UppercaseLetter: [A-Z];
fragment Letter: LowercaseLetter | UppercaseLetter;
fragment BinDigit: [0-1];
fragment Digit: BinDigit | [2-9];
fragment HexDigit: Digit | [a-fA-F];

/*
 * Identifiers
 */

TYPE_IDENTIFIER: UppercaseLetter (Letter|Digit|'_')*;
OBJECT_IDENTIFIER: LowercaseLetter (Letter|Digit|'_')* {
Integer type = keywordMap.get(getText());
if(type != null)
	setType(type);
};

/*
 * Literals
 */

fragment HexLiteral: '0x' HexDigit+;
fragment DecimalLiteral: Digit+;

INTEGER_LITERAL
	: (DecimalLiteral
	| HexLiteral)
	{
	String txt = getText();
	int val;
	if(txt.startsWith("0x")) {
		val = Integer.parseInt(txt.substring(2),16);
	} else {
		val = Integer.parseInt(txt);
	}
	setText(Integer.toString(val));
};
	
INVALID_HEX: HexLiteral [g-zG-Z]+ {
	getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed hex literal", null);
} -> skip;

INVALID_DECIMAL: DecimalLiteral [a-zA-Z]+ {
	getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed decimal literal", null);
} -> skip;

STRING_OPENER: '"' -> more, mode(STRING_MODE);

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
 * Comments
 */
SINGLELINE_COMMENT: '//' .*? ('\n'|EOF) -> skip;
LPS: '(*' -> more, mode(MULTI);

mode STRING_MODE;
	fragment LineSkip: '\\' ('\n'|'\r\n') [ \t]*;
	fragment EscapeSequence: [btnr"\\] | 'x' HexDigit HexDigit; // \x40
	fragment EscapeChar: '\\' EscapeSequence;
	fragment RegularChar: ~[\\"\r\n];

	STRING_LITERAL: (RegularChar|EscapeChar|LineSkip)*? '"' -> mode(DEFAULT_MODE);

mode MULTI;

	fragment comment_char
		: '('~[*]
		| '*'~[)]
		| ~[(*]
		;

	UNCLOSED_MULTILINE_COMMENT: ('(*' MULTILINE_COMMENT | '(*' UNCLOSED_MULTILINE_COMMENT | comment_char)* EOF? {
		int ln = 0;
		int col = 0;
		
		getErrorListenerDispatch().syntaxError(this, "", ln, col, "Unclosed opened multiline comment "+getText(), null);
	} -> mode(DEFAULT_MODE), skip;

	MULTILINE_COMMENT: ('(*' MULTILINE_COMMENT | comment_char)*? '*)' -> mode(DEFAULT_MODE), skip;
