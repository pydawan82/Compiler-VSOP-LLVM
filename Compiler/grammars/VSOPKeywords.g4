lexer grammar VSOPKeywords;

tokens {AND, NOT, IN, CLASS, EXTENDS, ISNULL, LET, NEW, SELF, IF, THEN, ELSE, WHILE, DO, TRUE, FALSE, BOOL, UNIT, INT32, STRING}

@lexer::header {
import java.util.Map;
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