lexer grammar VSOPKeywords;

tokens {AND, NOT, IN, CLASS, EXTENDS, ISNULL, LET, NEW, SELF, IF, THEN, ELSE, WHILE, DO, TRUE, FALSE, BOOL, UNIT, INT32, STRING}

@lexer::header {
	import java.util.Map;
}

@lexer::members { 
	Map<String, Integer> keywordMap = Map.ofEntries(
		new Entry<>("and", $AND.type)
	) 
}

fragment LowercaseLetter: [a-z];
fragment UppercaseLetter: [A-Z];
fragment Letter: LowercaseLetter | UppercaseLetter;
fragment BinDigit: [0-1];
fragment Digit: BinDigit | [2-9];
fragment HexDigit: Digit | [a-fA-F];


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