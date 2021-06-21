lexer grammar VSOPLexer;
import VSOPKeywords;

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
SINGLELINE_COMMENT: '//' .*? ('\n'|EOF) -> skip;

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

fragment LineSkip: '\\' ('\n'|'\r\n') [ \t]*;
fragment EscapeSequence: [btnr"\\] | 'x' HexDigit HexDigit; // \x40
fragment EscapeChar: '\\' EscapeSequence;
fragment RegularChar: ~[\\"\r\n];

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

RAW_LINEFEED_LITERAL: '"' (RegularChar|EscapeChar|LineSkip|BadEscapeChar|'\n')*? '"' {
	String[] split = getText().split("[^\\\\](\\n)");
	String[] split2 = split[0].split("\\n");
	int ln = _tokenStartLine+split2.length-1;
	int col;
	if(split2.length == 1)
		col = _tokenStartCharPositionInLine+split2[0].length();
	else {
		col = split2[split2.length-1].length();
	}
	
	getErrorListenerDispatch().syntaxError(this, "", ln, col+1, "Unauthorized raw linefeed", null);
} -> skip;

UNCLOSED_RAW_LINEFEED_LITERAL: '"' (RegularChar|EscapeChar|LineSkip|BadEscapeChar)*? '\n' {
	String[] split = getText().split("[^\\\\](\\n)");
	String[] split2 = split[0].split("\\n");
	int ln = _tokenStartLine+split2.length-1;
	int col;
	if(split2.length == 1)
		col = _tokenStartCharPositionInLine+split2[0].length();
	else {
		col = split2[split2.length-1].length();
	}
	
	getErrorListenerDispatch().syntaxError(this, "", ln, col+1, "Unauthorized raw linefeed", null);
} -> skip;


BAD_STRING_LITERAL: '"' (.)*? (~[\\]) '"' {
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

	UNCLOSED_MULTILINE_COMMENT: ('(*' MULTILINE_COMMENT| '(*' UNCLOSED_MULTILINE_COMMENT |'('~[*]|'*'~[)]|~[(*])* EOF? {
		int ln = 0;
		int col = 0;
		String text = "(*"+getText();

		java.util.regex.Pattern delim = java.util.regex.Pattern.compile("(\\(\\*|\\*\\))");
		java.util.regex.Matcher matcher = delim.matcher(text);
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>(); 

		while(matcher.find()) {
			int start = matcher.start();
				if(text.substring(start, start+2).equals("(*"))
					stack.push(start);
				else
					stack.pop();
		}
		
		int errorPos = stack.pop();
		int pos = 0;

		delim = java.util.regex.Pattern.compile("\\n");
		matcher = delim.matcher(text);

		while(matcher.find() && matcher.start()<errorPos) {
			pos = matcher.start();
			ln++;
		}

		col = (ln != 0) ? (errorPos-pos-1) : (_tokenStartCharPositionInLine+errorPos);
		ln += _tokenStartLine;
		
		getErrorListenerDispatch().syntaxError(this, "", ln, col, "Unclosed opened multiline comment "+getText(), null);
	} -> mode(DEFAULT_MODE), skip;

	MULTILINE_COMMENT: ('(*' MULTILINE_COMMENT|'('~[*]|'*'~[)]|~[(*])*? '*)' -> mode(DEFAULT_MODE), skip;



/*
 * Bad
 */
