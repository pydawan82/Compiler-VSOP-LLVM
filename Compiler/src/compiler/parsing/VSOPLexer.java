// Generated from e:\Data\David\git\Compiler-VSOP-LLVM\Compiler\grammars\VSOPLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VSOPLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SINGLELINE_COMMENT=1, AND=2, NOT=3, IN=4, CLASS=5, EXTENDS=6, ISNULL=7, 
		LET=8, NEW=9, SELF=10, IF=11, THEN=12, ELSE=13, WHILE=14, DO=15, TRUE=16, 
		FALSE=17, BOOL=18, UNIT=19, INT32=20, STRING=21, TYPE_IDENTIFIER=22, OBJECT_IDENTIFIER=23, 
		INTEGER_LITERAL=24, INVALID_HEX=25, INVALID_DECIMAL=26, STRING_LITERAL=27, 
		BAD_ESCAPE_LITERAL=28, RAW_LINEFEED_LITERAL=29, UNCLOSED_RAW_LINEFEED_LITERAL=30, 
		BAD_STRING_LITERAL=31, LBRACE=32, RBRACE=33, LPAR=34, RPAR=35, COLON=36, 
		SEMICOLON=37, COMMA=38, PLUS=39, MINUS=40, TIMES=41, DIV=42, POW=43, DOT=44, 
		EQUAL=45, LOWER=46, LOWER_EQUAL=47, ASSIGN=48, WS=49, UNCLOSED_MULTILINE_COMMENT=50, 
		MULTILINE_COMMENT=51, LPS=52;
	public static final int
		MULTI=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "MULTI"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LowercaseLetter", "UppercaseLetter", "Letter", "BinDigit", "Digit", 
			"HexDigit", "SINGLELINE_COMMENT", "AND", "NOT", "IN", "CLASS", "EXTENDS", 
			"ISNULL", "LET", "NEW", "SELF", "IF", "THEN", "ELSE", "WHILE", "DO", 
			"TRUE", "FALSE", "BOOL", "UNIT", "INT32", "STRING", "TYPE_IDENTIFIER", 
			"OBJECT_IDENTIFIER", "HexLiteral", "DecimalLiteral", "INTEGER_LITERAL", 
			"INVALID_HEX", "INVALID_DECIMAL", "LineSkip", "EscapeSequence", "EscapeChar", 
			"RegularChar", "STRING_LITERAL", "BadEscapeChar", "BAD_ESCAPE_LITERAL", 
			"RAW_LINEFEED_LITERAL", "UNCLOSED_RAW_LINEFEED_LITERAL", "BAD_STRING_LITERAL", 
			"LBRACE", "RBRACE", "LPAR", "RPAR", "COLON", "SEMICOLON", "COMMA", "PLUS", 
			"MINUS", "TIMES", "DIV", "POW", "DOT", "EQUAL", "LOWER", "LOWER_EQUAL", 
			"ASSIGN", "WS", "LPS", "UNCLOSED_MULTILINE_COMMENT", "MULTILINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'and'", "'not'", "'in'", "'class'", "'extends'", "'isnull'", 
			"'let'", "'new'", "'self'", "'if'", "'then'", "'else'", "'while'", "'do'", 
			"'true'", "'false'", "'bool'", "'unit'", "'int32'", "'string'", null, 
			null, null, null, null, null, null, null, null, null, "'{'", "'}'", "'('", 
			"')'", "':'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'^'", "'.'", 
			"'='", "'<'", "'<='", "'<-'", null, null, null, "'(*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SINGLELINE_COMMENT", "AND", "NOT", "IN", "CLASS", "EXTENDS", "ISNULL", 
			"LET", "NEW", "SELF", "IF", "THEN", "ELSE", "WHILE", "DO", "TRUE", "FALSE", 
			"BOOL", "UNIT", "INT32", "STRING", "TYPE_IDENTIFIER", "OBJECT_IDENTIFIER", 
			"INTEGER_LITERAL", "INVALID_HEX", "INVALID_DECIMAL", "STRING_LITERAL", 
			"BAD_ESCAPE_LITERAL", "RAW_LINEFEED_LITERAL", "UNCLOSED_RAW_LINEFEED_LITERAL", 
			"BAD_STRING_LITERAL", "LBRACE", "RBRACE", "LPAR", "RPAR", "COLON", "SEMICOLON", 
			"COMMA", "PLUS", "MINUS", "TIMES", "DIV", "POW", "DOT", "EQUAL", "LOWER", 
			"LOWER_EQUAL", "ASSIGN", "WS", "UNCLOSED_MULTILINE_COMMENT", "MULTILINE_COMMENT", 
			"LPS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public VSOPLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "VSOPLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 31:
			INTEGER_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 32:
			INVALID_HEX_action((RuleContext)_localctx, actionIndex);
			break;
		case 33:
			INVALID_DECIMAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 38:
			STRING_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 40:
			BAD_ESCAPE_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 41:
			RAW_LINEFEED_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 42:
			UNCLOSED_RAW_LINEFEED_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 43:
			BAD_STRING_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			UNCLOSED_MULTILINE_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void INTEGER_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

				String txt = getText();
				int val;
				if(txt.startsWith("0x")) {
					val = Integer.parseInt(txt.substring(2),16);
				} else {
					val = Integer.parseInt(txt);
				}
				setText(Integer.toString(val));

			break;
		}
	}
	private void INVALID_HEX_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

				getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed hex literal", null);

			break;
		}
	}
	private void INVALID_DECIMAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

				getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed decimal literal", null);

			break;
		}
	}
	private void STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

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

			break;
		}
	}
	private void BAD_ESCAPE_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:

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

			break;
		}
	}
	private void RAW_LINEFEED_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:

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

			break;
		}
	}
	private void UNCLOSED_RAW_LINEFEED_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:

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

			break;
		}
	}
	private void BAD_STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:

				getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed string literal", null);

			break;
		}
	}
	private void UNCLOSED_MULTILINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:

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
				
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0205\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4"+
		"+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4"+
		"\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4"+
		"=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\3\3\3\3\4\3\4\5\4\u008d\n"+
		"\4\3\5\3\5\3\6\3\6\5\6\u0093\n\6\3\7\3\7\5\7\u0097\n\7\3\b\3\b\3\b\3\b"+
		"\7\b\u009d\n\b\f\b\16\b\u00a0\13\b\3\b\5\b\u00a3\n\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\7\35\u0110\n\35\f\35\16\35\u0113\13\35\3\36\3\36\3\36\3\36\7\36"+
		"\u0119\n\36\f\36\16\36\u011c\13\36\3\37\3\37\3\37\3\37\6\37\u0122\n\37"+
		"\r\37\16\37\u0123\3 \6 \u0127\n \r \16 \u0128\3!\3!\5!\u012d\n!\3!\3!"+
		"\3\"\3\"\6\"\u0133\n\"\r\"\16\"\u0134\3\"\3\"\3\"\3\"\3#\3#\6#\u013d\n"+
		"#\r#\16#\u013e\3#\3#\3#\3#\3$\3$\3$\3$\5$\u0149\n$\3$\7$\u014c\n$\f$\16"+
		"$\u014f\13$\3%\3%\3%\3%\3%\5%\u0156\n%\3&\3&\3&\3\'\3\'\3(\3(\3(\3(\7"+
		"(\u0161\n(\f(\16(\u0164\13(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\7*\u0171"+
		"\n*\f*\16*\u0174\13*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\7+\u0181\n+\f+\16"+
		"+\u0184\13+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\7,\u0190\n,\f,\16,\u0193\13"+
		",\3,\3,\3,\3,\3,\3-\3-\7-\u019c\n-\f-\16-\u019f\13-\3-\3-\3-\3-\3-\3-"+
		"\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3"+
		"\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3=\3>\3>\3"+
		">\3?\6?\u01cc\n?\r?\16?\u01cd\3?\3?\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3"+
		"A\3A\3A\3A\3A\3A\3A\3A\7A\u01e5\nA\fA\16A\u01e8\13A\3A\5A\u01eb\nA\3A"+
		"\3A\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3B\3B\7B\u01fb\nB\fB\16B\u01fe\13B\3"+
		"B\3B\3B\3B\3B\3B\t\u009e\u0162\u0172\u0182\u0191\u019d\u01fc\2C\4\2\6"+
		"\2\b\2\n\2\f\2\16\2\20\3\22\4\24\5\26\6\30\7\32\b\34\t\36\n \13\"\f$\r"+
		"&\16(\17*\20,\21.\22\60\23\62\24\64\25\66\268\27:\30<\31>\2@\2B\32D\33"+
		"F\34H\2J\2L\2N\2P\35R\2T\36V\37X Z!\\\"^#`$b%d&f\'h(j)l*n+p,r-t.v/x\60"+
		"z\61|\62~\63\u0080\66\u0082\64\u0084\65\4\2\3\22\3\2c|\3\2C\\\3\2\62\63"+
		"\3\2\64;\4\2CHch\3\3\f\f\4\2I\\i|\4\2C\\c|\4\2\13\13\"\"\b\2$$^^ddppt"+
		"tvv\6\2\f\f\17\17$$^^\3\2^^\5\2\13\f\16\17\"\"\3\2,,\3\2++\4\2**,,\2\u0224"+
		"\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32"+
		"\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2"+
		"&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62"+
		"\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2"+
		"B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2P\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3"+
		"\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2"+
		"\2\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2"+
		"\2r\3\2\2\2\2t\3\2\2\2\2v\3\2\2\2\2x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~"+
		"\3\2\2\2\2\u0080\3\2\2\2\3\u0082\3\2\2\2\3\u0084\3\2\2\2\4\u0086\3\2\2"+
		"\2\6\u0088\3\2\2\2\b\u008c\3\2\2\2\n\u008e\3\2\2\2\f\u0092\3\2\2\2\16"+
		"\u0096\3\2\2\2\20\u0098\3\2\2\2\22\u00a6\3\2\2\2\24\u00aa\3\2\2\2\26\u00ae"+
		"\3\2\2\2\30\u00b1\3\2\2\2\32\u00b7\3\2\2\2\34\u00bf\3\2\2\2\36\u00c6\3"+
		"\2\2\2 \u00ca\3\2\2\2\"\u00ce\3\2\2\2$\u00d3\3\2\2\2&\u00d6\3\2\2\2(\u00db"+
		"\3\2\2\2*\u00e0\3\2\2\2,\u00e6\3\2\2\2.\u00e9\3\2\2\2\60\u00ee\3\2\2\2"+
		"\62\u00f4\3\2\2\2\64\u00f9\3\2\2\2\66\u00fe\3\2\2\28\u0104\3\2\2\2:\u010b"+
		"\3\2\2\2<\u0114\3\2\2\2>\u011d\3\2\2\2@\u0126\3\2\2\2B\u012c\3\2\2\2D"+
		"\u0130\3\2\2\2F\u013a\3\2\2\2H\u0144\3\2\2\2J\u0155\3\2\2\2L\u0157\3\2"+
		"\2\2N\u015a\3\2\2\2P\u015c\3\2\2\2R\u0168\3\2\2\2T\u016b\3\2\2\2V\u017a"+
		"\3\2\2\2X\u018a\3\2\2\2Z\u0199\3\2\2\2\\\u01a6\3\2\2\2^\u01a8\3\2\2\2"+
		"`\u01aa\3\2\2\2b\u01ac\3\2\2\2d\u01ae\3\2\2\2f\u01b0\3\2\2\2h\u01b2\3"+
		"\2\2\2j\u01b4\3\2\2\2l\u01b6\3\2\2\2n\u01b8\3\2\2\2p\u01ba\3\2\2\2r\u01bc"+
		"\3\2\2\2t\u01be\3\2\2\2v\u01c0\3\2\2\2x\u01c2\3\2\2\2z\u01c4\3\2\2\2|"+
		"\u01c7\3\2\2\2~\u01cb\3\2\2\2\u0080\u01d1\3\2\2\2\u0082\u01e6\3\2\2\2"+
		"\u0084\u01fc\3\2\2\2\u0086\u0087\t\2\2\2\u0087\5\3\2\2\2\u0088\u0089\t"+
		"\3\2\2\u0089\7\3\2\2\2\u008a\u008d\5\4\2\2\u008b\u008d\5\6\3\2\u008c\u008a"+
		"\3\2\2\2\u008c\u008b\3\2\2\2\u008d\t\3\2\2\2\u008e\u008f\t\4\2\2\u008f"+
		"\13\3\2\2\2\u0090\u0093\5\n\5\2\u0091\u0093\t\5\2\2\u0092\u0090\3\2\2"+
		"\2\u0092\u0091\3\2\2\2\u0093\r\3\2\2\2\u0094\u0097\5\f\6\2\u0095\u0097"+
		"\t\6\2\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\17\3\2\2\2\u0098"+
		"\u0099\7\61\2\2\u0099\u009a\7\61\2\2\u009a\u009e\3\2\2\2\u009b\u009d\13"+
		"\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009f\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a3\t\7"+
		"\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\b\b\2\2\u00a5"+
		"\21\3\2\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7f\2\2\u00a9"+
		"\23\3\2\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7v\2\2\u00ad"+
		"\25\3\2\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7p\2\2\u00b0\27\3\2\2\2\u00b1"+
		"\u00b2\7e\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7u\2\2"+
		"\u00b5\u00b6\7u\2\2\u00b6\31\3\2\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7"+
		"z\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd"+
		"\7f\2\2\u00bd\u00be\7u\2\2\u00be\33\3\2\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1"+
		"\7u\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7w\2\2\u00c3\u00c4\7n\2\2\u00c4"+
		"\u00c5\7n\2\2\u00c5\35\3\2\2\2\u00c6\u00c7\7n\2\2\u00c7\u00c8\7g\2\2\u00c8"+
		"\u00c9\7v\2\2\u00c9\37\3\2\2\2\u00ca\u00cb\7p\2\2\u00cb\u00cc\7g\2\2\u00cc"+
		"\u00cd\7y\2\2\u00cd!\3\2\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7g\2\2\u00d0"+
		"\u00d1\7n\2\2\u00d1\u00d2\7h\2\2\u00d2#\3\2\2\2\u00d3\u00d4\7k\2\2\u00d4"+
		"\u00d5\7h\2\2\u00d5%\3\2\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7j\2\2\u00d8"+
		"\u00d9\7g\2\2\u00d9\u00da\7p\2\2\u00da\'\3\2\2\2\u00db\u00dc\7g\2\2\u00dc"+
		"\u00dd\7n\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7g\2\2\u00df)\3\2\2\2\u00e0"+
		"\u00e1\7y\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7n\2\2"+
		"\u00e4\u00e5\7g\2\2\u00e5+\3\2\2\2\u00e6\u00e7\7f\2\2\u00e7\u00e8\7q\2"+
		"\2\u00e8-\3\2\2\2\u00e9\u00ea\7v\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7"+
		"w\2\2\u00ec\u00ed\7g\2\2\u00ed/\3\2\2\2\u00ee\u00ef\7h\2\2\u00ef\u00f0"+
		"\7c\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7u\2\2\u00f2\u00f3\7g\2\2\u00f3"+
		"\61\3\2\2\2\u00f4\u00f5\7d\2\2\u00f5\u00f6\7q\2\2\u00f6\u00f7\7q\2\2\u00f7"+
		"\u00f8\7n\2\2\u00f8\63\3\2\2\2\u00f9\u00fa\7w\2\2\u00fa\u00fb\7p\2\2\u00fb"+
		"\u00fc\7k\2\2\u00fc\u00fd\7v\2\2\u00fd\65\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff"+
		"\u0100\7p\2\2\u0100\u0101\7v\2\2\u0101\u0102\7\65\2\2\u0102\u0103\7\64"+
		"\2\2\u0103\67\3\2\2\2\u0104\u0105\7u\2\2\u0105\u0106\7v\2\2\u0106\u0107"+
		"\7t\2\2\u0107\u0108\7k\2\2\u0108\u0109\7p\2\2\u0109\u010a\7i\2\2\u010a"+
		"9\3\2\2\2\u010b\u0111\5\6\3\2\u010c\u0110\5\b\4\2\u010d\u0110\5\f\6\2"+
		"\u010e\u0110\7a\2\2\u010f\u010c\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u010e"+
		"\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		";\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u011a\5\4\2\2\u0115\u0119\5\b\4\2"+
		"\u0116\u0119\5\f\6\2\u0117\u0119\7a\2\2\u0118\u0115\3\2\2\2\u0118\u0116"+
		"\3\2\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b=\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e\7\62\2\2"+
		"\u011e\u011f\7z\2\2\u011f\u0121\3\2\2\2\u0120\u0122\5\16\7\2\u0121\u0120"+
		"\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"?\3\2\2\2\u0125\u0127\5\f\6\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2"+
		"\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129A\3\2\2\2\u012a\u012d\5"+
		"@ \2\u012b\u012d\5>\37\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\b!\3\2\u012fC\3\2\2\2\u0130\u0132\5>\37\2\u0131"+
		"\u0133\t\b\2\2\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0132\3\2"+
		"\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\b\"\4\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\b\"\2\2\u0139E\3\2\2\2\u013a\u013c\5@ \2\u013b"+
		"\u013d\t\t\2\2\u013c\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013c\3\2"+
		"\2\2\u013e\u013f\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\b#\5\2\u0141"+
		"\u0142\3\2\2\2\u0142\u0143\b#\2\2\u0143G\3\2\2\2\u0144\u0148\7^\2\2\u0145"+
		"\u0149\7\f\2\2\u0146\u0147\7\17\2\2\u0147\u0149\7\f\2\2\u0148\u0145\3"+
		"\2\2\2\u0148\u0146\3\2\2\2\u0149\u014d\3\2\2\2\u014a\u014c\t\n\2\2\u014b"+
		"\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2"+
		"\2\2\u014eI\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0156\t\13\2\2\u0151\u0152"+
		"\7z\2\2\u0152\u0153\5\16\7\2\u0153\u0154\5\16\7\2\u0154\u0156\3\2\2\2"+
		"\u0155\u0150\3\2\2\2\u0155\u0151\3\2\2\2\u0156K\3\2\2\2\u0157\u0158\7"+
		"^\2\2\u0158\u0159\5J%\2\u0159M\3\2\2\2\u015a\u015b\n\f\2\2\u015bO\3\2"+
		"\2\2\u015c\u0162\7$\2\2\u015d\u0161\5N\'\2\u015e\u0161\5L&\2\u015f\u0161"+
		"\5H$\2\u0160\u015d\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u015f\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0165\3\2"+
		"\2\2\u0164\u0162\3\2\2\2\u0165\u0166\7$\2\2\u0166\u0167\b(\6\2\u0167Q"+
		"\3\2\2\2\u0168\u0169\7^\2\2\u0169\u016a\13\2\2\2\u016aS\3\2\2\2\u016b"+
		"\u0172\7$\2\2\u016c\u0171\5N\'\2\u016d\u0171\5L&\2\u016e\u0171\5H$\2\u016f"+
		"\u0171\5R)\2\u0170\u016c\3\2\2\2\u0170\u016d\3\2\2\2\u0170\u016e\3\2\2"+
		"\2\u0170\u016f\3\2\2\2\u0171\u0174\3\2\2\2\u0172\u0173\3\2\2\2\u0172\u0170"+
		"\3\2\2\2\u0173\u0175\3\2\2\2\u0174\u0172\3\2\2\2\u0175\u0176\7$\2\2\u0176"+
		"\u0177\b*\7\2\u0177\u0178\3\2\2\2\u0178\u0179\b*\2\2\u0179U\3\2\2\2\u017a"+
		"\u0182\7$\2\2\u017b\u0181\5N\'\2\u017c\u0181\5L&\2\u017d\u0181\5H$\2\u017e"+
		"\u0181\5R)\2\u017f\u0181\7\f\2\2\u0180\u017b\3\2\2\2\u0180\u017c\3\2\2"+
		"\2\u0180\u017d\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u017f\3\2\2\2\u0181\u0184"+
		"\3\2\2\2\u0182\u0183\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0185\3\2\2\2\u0184"+
		"\u0182\3\2\2\2\u0185\u0186\7$\2\2\u0186\u0187\b+\b\2\u0187\u0188\3\2\2"+
		"\2\u0188\u0189\b+\2\2\u0189W\3\2\2\2\u018a\u0191\7$\2\2\u018b\u0190\5"+
		"N\'\2\u018c\u0190\5L&\2\u018d\u0190\5H$\2\u018e\u0190\5R)\2\u018f\u018b"+
		"\3\2\2\2\u018f\u018c\3\2\2\2\u018f\u018d\3\2\2\2\u018f\u018e\3\2\2\2\u0190"+
		"\u0193\3\2\2\2\u0191\u0192\3\2\2\2\u0191\u018f\3\2\2\2\u0192\u0194\3\2"+
		"\2\2\u0193\u0191\3\2\2\2\u0194\u0195\7\f\2\2\u0195\u0196\b,\t\2\u0196"+
		"\u0197\3\2\2\2\u0197\u0198\b,\2\2\u0198Y\3\2\2\2\u0199\u019d\7$\2\2\u019a"+
		"\u019c\13\2\2\2\u019b\u019a\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019e\3"+
		"\2\2\2\u019d\u019b\3\2\2\2\u019e\u01a0\3\2\2\2\u019f\u019d\3\2\2\2\u01a0"+
		"\u01a1\n\r\2\2\u01a1\u01a2\7$\2\2\u01a2\u01a3\b-\n\2\u01a3\u01a4\3\2\2"+
		"\2\u01a4\u01a5\b-\2\2\u01a5[\3\2\2\2\u01a6\u01a7\7}\2\2\u01a7]\3\2\2\2"+
		"\u01a8\u01a9\7\177\2\2\u01a9_\3\2\2\2\u01aa\u01ab\7*\2\2\u01aba\3\2\2"+
		"\2\u01ac\u01ad\7+\2\2\u01adc\3\2\2\2\u01ae\u01af\7<\2\2\u01afe\3\2\2\2"+
		"\u01b0\u01b1\7=\2\2\u01b1g\3\2\2\2\u01b2\u01b3\7.\2\2\u01b3i\3\2\2\2\u01b4"+
		"\u01b5\7-\2\2\u01b5k\3\2\2\2\u01b6\u01b7\7/\2\2\u01b7m\3\2\2\2\u01b8\u01b9"+
		"\7,\2\2\u01b9o\3\2\2\2\u01ba\u01bb\7\61\2\2\u01bbq\3\2\2\2\u01bc\u01bd"+
		"\7`\2\2\u01bds\3\2\2\2\u01be\u01bf\7\60\2\2\u01bfu\3\2\2\2\u01c0\u01c1"+
		"\7?\2\2\u01c1w\3\2\2\2\u01c2\u01c3\7>\2\2\u01c3y\3\2\2\2\u01c4\u01c5\7"+
		">\2\2\u01c5\u01c6\7?\2\2\u01c6{\3\2\2\2\u01c7\u01c8\7>\2\2\u01c8\u01c9"+
		"\7/\2\2\u01c9}\3\2\2\2\u01ca\u01cc\t\16\2\2\u01cb\u01ca\3\2\2\2\u01cc"+
		"\u01cd\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\3\2"+
		"\2\2\u01cf\u01d0\b?\2\2\u01d0\177\3\2\2\2\u01d1\u01d2\7*\2\2\u01d2\u01d3"+
		"\7,\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d5\b@\13\2\u01d5\u01d6\b@\f\2\u01d6"+
		"\u0081\3\2\2\2\u01d7\u01d8\7*\2\2\u01d8\u01d9\7,\2\2\u01d9\u01da\3\2\2"+
		"\2\u01da\u01e5\5\u0084B\2\u01db\u01dc\7*\2\2\u01dc\u01dd\7,\2\2\u01dd"+
		"\u01de\3\2\2\2\u01de\u01e5\5\u0082A\2\u01df\u01e0\7*\2\2\u01e0\u01e5\n"+
		"\17\2\2\u01e1\u01e2\7,\2\2\u01e2\u01e5\n\20\2\2\u01e3\u01e5\n\21\2\2\u01e4"+
		"\u01d7\3\2\2\2\u01e4\u01db\3\2\2\2\u01e4\u01df\3\2\2\2\u01e4\u01e1\3\2"+
		"\2\2\u01e4\u01e3\3\2\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6"+
		"\u01e7\3\2\2\2\u01e7\u01ea\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e9\u01eb\7\2"+
		"\2\3\u01ea\u01e9\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec"+
		"\u01ed\bA\r\2\u01ed\u01ee\3\2\2\2\u01ee\u01ef\bA\16\2\u01ef\u01f0\bA\2"+
		"\2\u01f0\u0083\3\2\2\2\u01f1\u01f2\7*\2\2\u01f2\u01f3\7,\2\2\u01f3\u01f4"+
		"\3\2\2\2\u01f4\u01fb\5\u0084B\2\u01f5\u01f6\7*\2\2\u01f6\u01fb\n\17\2"+
		"\2\u01f7\u01f8\7,\2\2\u01f8\u01fb\n\20\2\2\u01f9\u01fb\n\21\2\2\u01fa"+
		"\u01f1\3\2\2\2\u01fa\u01f5\3\2\2\2\u01fa\u01f7\3\2\2\2\u01fa\u01f9\3\2"+
		"\2\2\u01fb\u01fe\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fd"+
		"\u01ff\3\2\2\2\u01fe\u01fc\3\2\2\2\u01ff\u0200\7,\2\2\u0200\u0201\7+\2"+
		"\2\u0201\u0202\3\2\2\2\u0202\u0203\bB\16\2\u0203\u0204\bB\2\2\u0204\u0085"+
		"\3\2\2\2$\2\3\u008c\u0092\u0096\u009e\u00a2\u010f\u0111\u0118\u011a\u0123"+
		"\u0128\u012c\u0134\u013e\u0148\u014d\u0155\u0160\u0162\u0170\u0172\u0180"+
		"\u0182\u018f\u0191\u019d\u01cd\u01e4\u01e6\u01ea\u01fa\u01fc\17\b\2\2"+
		"\3!\2\3\"\3\3#\4\3(\5\3*\6\3+\7\3,\b\3-\t\5\2\2\4\3\2\3A\n\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}