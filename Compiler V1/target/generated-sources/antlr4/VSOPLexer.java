// Generated from VSOPLexer.g4 by ANTLR 4.9.1
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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SINGLELINE_COMMENT=1, AND=2, NOT=3, IN=4, CLASS=5, EXTENDS=6, ISNULL=7, 
		LET=8, NEW=9, SELF=10, IF=11, THEN=12, ELSE=13, WHILE=14, DO=15, TRUE=16, 
		FALSE=17, BOOL=18, UNIT=19, INT32=20, STRING=21, TYPE_INDENTIFIER=22, 
		OBJECT_IDENTIFIER=23, INTEGER_LITERAL=24, INVALID_HEX=25, INVALID_DECIMAL=26, 
		STRING_LITERAL=27, BAD_ESCAPE_LITERAL=28, RAW_LINEFEED_LITERAL=29, BAD_STRING_LITERAL=30, 
		LBRACE=31, RBRACE=32, LPAR=33, RPAR=34, COLON=35, SEMICOLON=36, COMMA=37, 
		PLUS=38, MINUS=39, TIMES=40, DIV=41, POW=42, DOT=43, EQUAL=44, LOWER=45, 
		LOWER_EQUAL=46, ASSIGN=47, WS=48, MULTILINE_COMMENT=49, LPS=50;
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
			"TRUE", "FALSE", "BOOL", "UNIT", "INT32", "STRING", "TYPE_INDENTIFIER", 
			"OBJECT_IDENTIFIER", "HexLitteral", "DecimalLitteral", "INTEGER_LITERAL", 
			"INVALID_HEX", "INVALID_DECIMAL", "LineSkip", "EscapeSequence", "EscapeChar", 
			"RegularChar", "STRING_LITERAL", "BadEscapeChar", "BAD_ESCAPE_LITERAL", 
			"RAW_LINEFEED_LITERAL", "BAD_STRING_LITERAL", "LBRACE", "RBRACE", "LPAR", 
			"RPAR", "COLON", "SEMICOLON", "COMMA", "PLUS", "MINUS", "TIMES", "DIV", 
			"POW", "DOT", "EQUAL", "LOWER", "LOWER_EQUAL", "ASSIGN", "WS", "LPS", 
			"MULTILINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'and'", "'not'", "'in'", "'class'", "'extends'", "'isnull'", 
			"'let'", "'new'", "'self'", "'if'", "'then'", "'else'", "'while'", "'do'", 
			"'true'", "'false'", "'bool'", "'unit'", "'int32'", "'string'", null, 
			null, null, null, null, null, null, null, null, "'{'", "'}'", "'('", 
			"')'", "':'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'^'", "'.'", 
			"'='", "'<'", "'<='", "'<-'", null, null, "'(*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SINGLELINE_COMMENT", "AND", "NOT", "IN", "CLASS", "EXTENDS", "ISNULL", 
			"LET", "NEW", "SELF", "IF", "THEN", "ELSE", "WHILE", "DO", "TRUE", "FALSE", 
			"BOOL", "UNIT", "INT32", "STRING", "TYPE_INDENTIFIER", "OBJECT_IDENTIFIER", 
			"INTEGER_LITERAL", "INVALID_HEX", "INVALID_DECIMAL", "STRING_LITERAL", 
			"BAD_ESCAPE_LITERAL", "RAW_LINEFEED_LITERAL", "BAD_STRING_LITERAL", "LBRACE", 
			"RBRACE", "LPAR", "RPAR", "COLON", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
			"TIMES", "DIV", "POW", "DOT", "EQUAL", "LOWER", "LOWER_EQUAL", "ASSIGN", 
			"WS", "MULTILINE_COMMENT", "LPS"
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
			BAD_STRING_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void INVALID_HEX_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

				getErrorListenerDispatch().syntaxError(this, "", getLine(), getCharPositionInLine(), "Ill formed hex literal", null);

			break;
		}
	}
	private void INVALID_DECIMAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

				getErrorListenerDispatch().syntaxError(this, "", getLine(), getCharPositionInLine(), "Ill formed decimal literal", null);

			break;
		}
	}
	private void STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

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

			break;
		}
	}
	private void BAD_ESCAPE_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

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
		case 4:

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

			break;
		}
	}
	private void BAD_STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:

				getErrorListenerDispatch().syntaxError(this, "", _tokenStartLine, _tokenStartCharPositionInLine, "Ill formed string literal", null);

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u01d9\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4"+
		"+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4"+
		"\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4"+
		"=\t=\4>\t>\4?\t?\4@\t@\3\2\3\2\3\3\3\3\3\4\3\4\5\4\u0089\n\4\3\5\3\5\3"+
		"\6\3\6\5\6\u008f\n\6\3\7\3\7\5\7\u0093\n\7\3\b\3\b\3\b\3\b\7\b\u0099\n"+
		"\b\f\b\16\b\u009c\13\b\3\b\5\b\u009f\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u010c"+
		"\n\35\f\35\16\35\u010f\13\35\3\36\3\36\3\36\3\36\7\36\u0115\n\36\f\36"+
		"\16\36\u0118\13\36\3\37\3\37\3\37\3\37\6\37\u011e\n\37\r\37\16\37\u011f"+
		"\3 \6 \u0123\n \r \16 \u0124\3!\6!\u0128\n!\r!\16!\u0129\3!\5!\u012d\n"+
		"!\3\"\3\"\6\"\u0131\n\"\r\"\16\"\u0132\3\"\3\"\3\"\3\"\3#\3#\6#\u013b"+
		"\n#\r#\16#\u013c\3#\3#\3#\3#\3$\3$\3$\3$\5$\u0147\n$\3$\7$\u014a\n$\f"+
		"$\16$\u014d\13$\3%\3%\3%\3%\3%\5%\u0154\n%\3&\3&\3&\3\'\3\'\3(\3(\3(\3"+
		"(\7(\u015f\n(\f(\16(\u0162\13(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\7*\u016f"+
		"\n*\f*\16*\u0172\13*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\7+\u017f\n+\f+\16"+
		"+\u0182\13+\3+\3+\3+\3+\3+\3,\3,\7,\u018b\n,\f,\16,\u018e\13,\3,\3,\3"+
		",\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3<\3"+
		"=\3=\3=\3>\6>\u01ba\n>\r>\16>\u01bb\3>\3>\3?\3?\3?\3?\3?\3?\3@\3@\3@\3"+
		"@\3@\3@\3@\3@\3@\7@\u01cf\n@\f@\16@\u01d2\13@\3@\3@\3@\3@\3@\3@\b\u009a"+
		"\u0160\u0170\u0180\u018c\u01d0\2A\4\2\6\2\b\2\n\2\f\2\16\2\20\3\22\4\24"+
		"\5\26\6\30\7\32\b\34\t\36\n \13\"\f$\r&\16(\17*\20,\21.\22\60\23\62\24"+
		"\64\25\66\268\27:\30<\31>\2@\2B\32D\33F\34H\2J\2L\2N\2P\35R\2T\36V\37"+
		"X Z!\\\"^#`$b%d&f\'h(j)l*n+p,r-t.v/x\60z\61|\62~\64\u0080\63\4\2\3\22"+
		"\3\2c|\3\2C\\\3\2\62\63\3\2\64;\4\2CHch\3\3\f\f\4\2I\\i|\4\2C\\c|\4\2"+
		"\13\13\"\"\b\2$$^^ddppttvv\6\2\f\f\17\17$$^^\4\2\f\f\17\17\5\2\13\f\16"+
		"\17\"\"\3\2,,\3\2++\4\2**,,\2\u01ef\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2"+
		"\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2"+
		"\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2"+
		",\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2"+
		"\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2"+
		"P\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3"+
		"\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2"+
		"\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2\2r\3\2\2\2\2t\3\2\2\2\2v\3\2\2\2\2"+
		"x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~\3\2\2\2\3\u0080\3\2\2\2\4\u0082\3\2"+
		"\2\2\6\u0084\3\2\2\2\b\u0088\3\2\2\2\n\u008a\3\2\2\2\f\u008e\3\2\2\2\16"+
		"\u0092\3\2\2\2\20\u0094\3\2\2\2\22\u00a2\3\2\2\2\24\u00a6\3\2\2\2\26\u00aa"+
		"\3\2\2\2\30\u00ad\3\2\2\2\32\u00b3\3\2\2\2\34\u00bb\3\2\2\2\36\u00c2\3"+
		"\2\2\2 \u00c6\3\2\2\2\"\u00ca\3\2\2\2$\u00cf\3\2\2\2&\u00d2\3\2\2\2(\u00d7"+
		"\3\2\2\2*\u00dc\3\2\2\2,\u00e2\3\2\2\2.\u00e5\3\2\2\2\60\u00ea\3\2\2\2"+
		"\62\u00f0\3\2\2\2\64\u00f5\3\2\2\2\66\u00fa\3\2\2\28\u0100\3\2\2\2:\u0107"+
		"\3\2\2\2<\u0110\3\2\2\2>\u0119\3\2\2\2@\u0122\3\2\2\2B\u012c\3\2\2\2D"+
		"\u012e\3\2\2\2F\u0138\3\2\2\2H\u0142\3\2\2\2J\u0153\3\2\2\2L\u0155\3\2"+
		"\2\2N\u0158\3\2\2\2P\u015a\3\2\2\2R\u0166\3\2\2\2T\u0169\3\2\2\2V\u0178"+
		"\3\2\2\2X\u0188\3\2\2\2Z\u0194\3\2\2\2\\\u0196\3\2\2\2^\u0198\3\2\2\2"+
		"`\u019a\3\2\2\2b\u019c\3\2\2\2d\u019e\3\2\2\2f\u01a0\3\2\2\2h\u01a2\3"+
		"\2\2\2j\u01a4\3\2\2\2l\u01a6\3\2\2\2n\u01a8\3\2\2\2p\u01aa\3\2\2\2r\u01ac"+
		"\3\2\2\2t\u01ae\3\2\2\2v\u01b0\3\2\2\2x\u01b2\3\2\2\2z\u01b5\3\2\2\2|"+
		"\u01b9\3\2\2\2~\u01bf\3\2\2\2\u0080\u01d0\3\2\2\2\u0082\u0083\t\2\2\2"+
		"\u0083\5\3\2\2\2\u0084\u0085\t\3\2\2\u0085\7\3\2\2\2\u0086\u0089\5\4\2"+
		"\2\u0087\u0089\5\6\3\2\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\t"+
		"\3\2\2\2\u008a\u008b\t\4\2\2\u008b\13\3\2\2\2\u008c\u008f\5\n\5\2\u008d"+
		"\u008f\t\5\2\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f\r\3\2\2\2"+
		"\u0090\u0093\5\f\6\2\u0091\u0093\t\6\2\2\u0092\u0090\3\2\2\2\u0092\u0091"+
		"\3\2\2\2\u0093\17\3\2\2\2\u0094\u0095\7\61\2\2\u0095\u0096\7\61\2\2\u0096"+
		"\u009a\3\2\2\2\u0097\u0099\13\2\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3"+
		"\2\2\2\u009a\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009d\u009f\t\7\2\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\u00a1\b\b\2\2\u00a1\21\3\2\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4"+
		"\7p\2\2\u00a4\u00a5\7f\2\2\u00a5\23\3\2\2\2\u00a6\u00a7\7p\2\2\u00a7\u00a8"+
		"\7q\2\2\u00a8\u00a9\7v\2\2\u00a9\25\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac"+
		"\7p\2\2\u00ac\27\3\2\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7n\2\2\u00af\u00b0"+
		"\7c\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7u\2\2\u00b2\31\3\2\2\2\u00b3\u00b4"+
		"\7g\2\2\u00b4\u00b5\7z\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7p\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7u\2\2\u00ba\33\3\2\2\2\u00bb"+
		"\u00bc\7k\2\2\u00bc\u00bd\7u\2\2\u00bd\u00be\7p\2\2\u00be\u00bf\7w\2\2"+
		"\u00bf\u00c0\7n\2\2\u00c0\u00c1\7n\2\2\u00c1\35\3\2\2\2\u00c2\u00c3\7"+
		"n\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7v\2\2\u00c5\37\3\2\2\2\u00c6\u00c7"+
		"\7p\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7y\2\2\u00c9!\3\2\2\2\u00ca\u00cb"+
		"\7u\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7h\2\2\u00ce"+
		"#\3\2\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7h\2\2\u00d1%\3\2\2\2\u00d2\u00d3"+
		"\7v\2\2\u00d3\u00d4\7j\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7p\2\2\u00d6"+
		"\'\3\2\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7n\2\2\u00d9\u00da\7u\2\2\u00da"+
		"\u00db\7g\2\2\u00db)\3\2\2\2\u00dc\u00dd\7y\2\2\u00dd\u00de\7j\2\2\u00de"+
		"\u00df\7k\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7g\2\2\u00e1+\3\2\2\2\u00e2"+
		"\u00e3\7f\2\2\u00e3\u00e4\7q\2\2\u00e4-\3\2\2\2\u00e5\u00e6\7v\2\2\u00e6"+
		"\u00e7\7t\2\2\u00e7\u00e8\7w\2\2\u00e8\u00e9\7g\2\2\u00e9/\3\2\2\2\u00ea"+
		"\u00eb\7h\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7u\2\2"+
		"\u00ee\u00ef\7g\2\2\u00ef\61\3\2\2\2\u00f0\u00f1\7d\2\2\u00f1\u00f2\7"+
		"q\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7n\2\2\u00f4\63\3\2\2\2\u00f5\u00f6"+
		"\7w\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8\7k\2\2\u00f8\u00f9\7v\2\2\u00f9"+
		"\65\3\2\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7v\2\2\u00fd"+
		"\u00fe\7\65\2\2\u00fe\u00ff\7\64\2\2\u00ff\67\3\2\2\2\u0100\u0101\7u\2"+
		"\2\u0101\u0102\7v\2\2\u0102\u0103\7t\2\2\u0103\u0104\7k\2\2\u0104\u0105"+
		"\7p\2\2\u0105\u0106\7i\2\2\u01069\3\2\2\2\u0107\u010d\5\6\3\2\u0108\u010c"+
		"\5\b\4\2\u0109\u010c\5\f\6\2\u010a\u010c\7a\2\2\u010b\u0108\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010d\u010e\3\2\2\2\u010e;\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0116"+
		"\5\4\2\2\u0111\u0115\5\b\4\2\u0112\u0115\5\f\6\2\u0113\u0115\7a\2\2\u0114"+
		"\u0111\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2"+
		"\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117=\3\2\2\2\u0118\u0116"+
		"\3\2\2\2\u0119\u011a\7\62\2\2\u011a\u011b\7z\2\2\u011b\u011d\3\2\2\2\u011c"+
		"\u011e\5\16\7\2\u011d\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u011d\3"+
		"\2\2\2\u011f\u0120\3\2\2\2\u0120?\3\2\2\2\u0121\u0123\5\f\6\2\u0122\u0121"+
		"\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"A\3\2\2\2\u0126\u0128\5\f\6\2\u0127\u0126\3\2\2\2\u0128\u0129\3\2\2\2"+
		"\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012d"+
		"\5>\37\2\u012c\u0127\3\2\2\2\u012c\u012b\3\2\2\2\u012dC\3\2\2\2\u012e"+
		"\u0130\5>\37\2\u012f\u0131\t\b\2\2\u0130\u012f\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0135\b\"\3\2\u0135\u0136\3\2\2\2\u0136\u0137\b\"\4\2\u0137E\3\2\2\2"+
		"\u0138\u013a\5@ \2\u0139\u013b\t\t\2\2\u013a\u0139\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u013f\b#\5\2\u013f\u0140\3\2\2\2\u0140\u0141\b#\4\2\u0141G\3\2\2\2\u0142"+
		"\u0146\7^\2\2\u0143\u0147\7\f\2\2\u0144\u0145\7\17\2\2\u0145\u0147\7\f"+
		"\2\2\u0146\u0143\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u014b\3\2\2\2\u0148"+
		"\u014a\t\n\2\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2"+
		"\2\2\u014b\u014c\3\2\2\2\u014cI\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u0154"+
		"\t\13\2\2\u014f\u0150\7z\2\2\u0150\u0151\5\16\7\2\u0151\u0152\5\16\7\2"+
		"\u0152\u0154\3\2\2\2\u0153\u014e\3\2\2\2\u0153\u014f\3\2\2\2\u0154K\3"+
		"\2\2\2\u0155\u0156\7^\2\2\u0156\u0157\5J%\2\u0157M\3\2\2\2\u0158\u0159"+
		"\n\f\2\2\u0159O\3\2\2\2\u015a\u0160\7$\2\2\u015b\u015f\5N\'\2\u015c\u015f"+
		"\5L&\2\u015d\u015f\5H$\2\u015e\u015b\3\2\2\2\u015e\u015c\3\2\2\2\u015e"+
		"\u015d\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u0161\3\2\2\2\u0160\u015e\3\2"+
		"\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0164\7$\2\2\u0164"+
		"\u0165\b(\6\2\u0165Q\3\2\2\2\u0166\u0167\7^\2\2\u0167\u0168\13\2\2\2\u0168"+
		"S\3\2\2\2\u0169\u0170\7$\2\2\u016a\u016f\5N\'\2\u016b\u016f\5L&\2\u016c"+
		"\u016f\5H$\2\u016d\u016f\5R)\2\u016e\u016a\3\2\2\2\u016e\u016b\3\2\2\2"+
		"\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u0171"+
		"\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0173\3\2\2\2\u0172\u0170\3\2\2\2\u0173"+
		"\u0174\7$\2\2\u0174\u0175\b*\7\2\u0175\u0176\3\2\2\2\u0176\u0177\b*\4"+
		"\2\u0177U\3\2\2\2\u0178\u0180\7$\2\2\u0179\u017f\5N\'\2\u017a\u017f\5"+
		"L&\2\u017b\u017f\5H$\2\u017c\u017f\5R)\2\u017d\u017f\t\r\2\2\u017e\u0179"+
		"\3\2\2\2\u017e\u017a\3\2\2\2\u017e\u017b\3\2\2\2\u017e\u017c\3\2\2\2\u017e"+
		"\u017d\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u0181\3\2\2\2\u0180\u017e\3\2"+
		"\2\2\u0181\u0183\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0184\7$\2\2\u0184"+
		"\u0185\b+\b\2\u0185\u0186\3\2\2\2\u0186\u0187\b+\4\2\u0187W\3\2\2\2\u0188"+
		"\u018c\7$\2\2\u0189\u018b\13\2\2\2\u018a\u0189\3\2\2\2\u018b\u018e\3\2"+
		"\2\2\u018c\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018f\3\2\2\2\u018e"+
		"\u018c\3\2\2\2\u018f\u0190\7$\2\2\u0190\u0191\b,\t\2\u0191\u0192\3\2\2"+
		"\2\u0192\u0193\b,\4\2\u0193Y\3\2\2\2\u0194\u0195\7}\2\2\u0195[\3\2\2\2"+
		"\u0196\u0197\7\177\2\2\u0197]\3\2\2\2\u0198\u0199\7*\2\2\u0199_\3\2\2"+
		"\2\u019a\u019b\7+\2\2\u019ba\3\2\2\2\u019c\u019d\7<\2\2\u019dc\3\2\2\2"+
		"\u019e\u019f\7=\2\2\u019fe\3\2\2\2\u01a0\u01a1\7.\2\2\u01a1g\3\2\2\2\u01a2"+
		"\u01a3\7-\2\2\u01a3i\3\2\2\2\u01a4\u01a5\7/\2\2\u01a5k\3\2\2\2\u01a6\u01a7"+
		"\7,\2\2\u01a7m\3\2\2\2\u01a8\u01a9\7\61\2\2\u01a9o\3\2\2\2\u01aa\u01ab"+
		"\7`\2\2\u01abq\3\2\2\2\u01ac\u01ad\7\60\2\2\u01ads\3\2\2\2\u01ae\u01af"+
		"\7?\2\2\u01afu\3\2\2\2\u01b0\u01b1\7>\2\2\u01b1w\3\2\2\2\u01b2\u01b3\7"+
		">\2\2\u01b3\u01b4\7?\2\2\u01b4y\3\2\2\2\u01b5\u01b6\7>\2\2\u01b6\u01b7"+
		"\7/\2\2\u01b7{\3\2\2\2\u01b8\u01ba\t\16\2\2\u01b9\u01b8\3\2\2\2\u01ba"+
		"\u01bb\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\3\2"+
		"\2\2\u01bd\u01be\b>\4\2\u01be}\3\2\2\2\u01bf\u01c0\7*\2\2\u01c0\u01c1"+
		"\7,\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\b?\n\2\u01c3\u01c4\b?\13\2\u01c4"+
		"\177\3\2\2\2\u01c5\u01c6\7*\2\2\u01c6\u01c7\7,\2\2\u01c7\u01c8\3\2\2\2"+
		"\u01c8\u01cf\5\u0080@\2\u01c9\u01ca\7*\2\2\u01ca\u01cf\n\17\2\2\u01cb"+
		"\u01cc\7,\2\2\u01cc\u01cf\n\20\2\2\u01cd\u01cf\n\21\2\2\u01ce\u01c5\3"+
		"\2\2\2\u01ce\u01c9\3\2\2\2\u01ce\u01cb\3\2\2\2\u01ce\u01cd\3\2\2\2\u01cf"+
		"\u01d2\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d1\u01d3\3\2"+
		"\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d4\7,\2\2\u01d4\u01d5\7+\2\2\u01d5\u01d6"+
		"\3\2\2\2\u01d6\u01d7\b@\f\2\u01d7\u01d8\b@\2\2\u01d8\u0081\3\2\2\2 \2"+
		"\3\u0088\u008e\u0092\u009a\u009e\u010b\u010d\u0114\u0116\u011f\u0124\u0129"+
		"\u012c\u0132\u013c\u0146\u014b\u0153\u015e\u0160\u016e\u0170\u017e\u0180"+
		"\u018c\u01bb\u01ce\u01d0\r\2\3\2\3\"\2\b\2\2\3#\3\3(\4\3*\5\3+\6\3,\7"+
		"\5\2\2\4\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}