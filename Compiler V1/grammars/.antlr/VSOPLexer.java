// Generated from e:\Data\David\git\Compiler-VSOP-LLVM\Compiler V1\grammars\VSOPLexer.g4 by ANTLR 4.8
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
		WS=1, SINGLELINE_COMMENT=2, MULTILINE_COMMENT=3, AND=4, NOT=5, IN=6, CLASS=7, 
		EXTENDS=8, ISNULL=9, LET=10, NEW=11, SELF=12, IF=13, THEN=14, ELSE=15, 
		WHILE=16, DO=17, TRUE=18, FALSE=19, BOOL=20, UNIT=21, INT32=22, STRING=23, 
		OBJECT_IDENTIFIER=24, INTEGER_LITERAL=25, STRING_LITERAL=26, LBRACE=27, 
		RBRACE=28, LPAR=29, RPAR=30, COLON=31, SEMICOLON=32, COMMA=33, PLUS=34, 
		MINUS=35, TIMES=36, DIV=37, POW=38, DOT=39, EQUAL=40, LOWER=41, LOWER_EQUAL=42, 
		ASSIGN=43;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LowercaseLetter", "UppercaseLetter", "Letter", "BinDigit", "Digit", 
			"HexDigit", "WS", "SINGLELINE_COMMENT", "MULTILINE_COMMENT", "AND", "NOT", 
			"IN", "CLASS", "EXTENDS", "ISNULL", "LET", "NEW", "SELF", "IF", "THEN", 
			"ELSE", "WHILE", "DO", "TRUE", "FALSE", "BOOL", "UNIT", "INT32", "STRING", 
			"OBJECT_IDENTIFIER", "INTEGER_LITERAL", "EscapeSequence", "EscapeChar", 
			"RegularChar", "STRING_LITERAL", "LBRACE", "RBRACE", "LPAR", "RPAR", 
			"COLON", "SEMICOLON", "COMMA", "PLUS", "MINUS", "TIMES", "DIV", "POW", 
			"DOT", "EQUAL", "LOWER", "LOWER_EQUAL", "ASSIGN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'and'", "'not'", "'in'", "'class'", "'extends'", 
			"'isnull'", "'let'", "'new'", "'self'", "'if'", "'then'", "'else'", "'while'", 
			"'do'", "'true'", "'false'", "'bool'", "'unit'", "'int32'", "'string'", 
			null, null, null, "'{'", "'}'", "'('", "')'", "':'", "';'", "','", "'+'", 
			"'-'", "'*'", "'/'", "'^'", "'.'", "'='", "'<'", "'<='", "'<-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "SINGLELINE_COMMENT", "MULTILINE_COMMENT", "AND", "NOT", 
			"IN", "CLASS", "EXTENDS", "ISNULL", "LET", "NEW", "SELF", "IF", "THEN", 
			"ELSE", "WHILE", "DO", "TRUE", "FALSE", "BOOL", "UNIT", "INT32", "STRING", 
			"OBJECT_IDENTIFIER", "INTEGER_LITERAL", "STRING_LITERAL", "LBRACE", "RBRACE", 
			"LPAR", "RPAR", "COLON", "SEMICOLON", "COMMA", "PLUS", "MINUS", "TIMES", 
			"DIV", "POW", "DOT", "EQUAL", "LOWER", "LOWER_EQUAL", "ASSIGN"
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u015f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\5\4r\n\4\3\5\3\5\3\6\3\6\5\6x\n"+
		"\6\3\7\3\7\5\7|\n\7\3\b\6\b\177\n\b\r\b\16\b\u0080\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\7\t\u0089\n\t\f\t\16\t\u008c\13\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\7\n\u0096\n\n\f\n\16\n\u0099\13\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3\37\7\37\u0109\n\37\f\37\16\37\u010c\13\37\3 \6 \u010f\n \r"+
		" \16 \u0110\3 \3 \3 \3 \6 \u0117\n \r \16 \u0118\5 \u011b\n \3!\3!\3!"+
		"\3!\3!\3!\3!\7!\u0124\n!\f!\16!\u0127\13!\5!\u0129\n!\3\"\3\"\3\"\3#\3"+
		"#\5#\u0130\n#\3$\3$\3$\7$\u0135\n$\f$\16$\u0138\13$\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\5\u008a\u0097"+
		"\u0136\2\66\3\2\5\2\7\2\t\2\13\2\r\2\17\3\21\4\23\5\25\6\27\7\31\b\33"+
		"\t\35\n\37\13!\f#\r%\16\'\17)\20+\21-\22/\23\61\24\63\25\65\26\67\279"+
		"\30;\31=\32?\33A\2C\2E\2G\34I\35K\36M\37O Q!S\"U#W$Y%[&]\'_(a)c*e+g,i"+
		"-\3\2\13\3\2c|\3\2C\\\3\2\62\63\3\2\64;\4\2CHch\5\2\13\f\16\17\"\"\5\2"+
		"\f\fGHQQ\b\2$$^^ddppttvv\4\2\13\13\"\"\2\u0167\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2"+
		"\5m\3\2\2\2\7q\3\2\2\2\ts\3\2\2\2\13w\3\2\2\2\r{\3\2\2\2\17~\3\2\2\2\21"+
		"\u0084\3\2\2\2\23\u0091\3\2\2\2\25\u009f\3\2\2\2\27\u00a3\3\2\2\2\31\u00a7"+
		"\3\2\2\2\33\u00aa\3\2\2\2\35\u00b0\3\2\2\2\37\u00b8\3\2\2\2!\u00bf\3\2"+
		"\2\2#\u00c3\3\2\2\2%\u00c7\3\2\2\2\'\u00cc\3\2\2\2)\u00cf\3\2\2\2+\u00d4"+
		"\3\2\2\2-\u00d9\3\2\2\2/\u00df\3\2\2\2\61\u00e2\3\2\2\2\63\u00e7\3\2\2"+
		"\2\65\u00ed\3\2\2\2\67\u00f2\3\2\2\29\u00f7\3\2\2\2;\u00fd\3\2\2\2=\u0104"+
		"\3\2\2\2?\u011a\3\2\2\2A\u0128\3\2\2\2C\u012a\3\2\2\2E\u012f\3\2\2\2G"+
		"\u0131\3\2\2\2I\u013b\3\2\2\2K\u013d\3\2\2\2M\u013f\3\2\2\2O\u0141\3\2"+
		"\2\2Q\u0143\3\2\2\2S\u0145\3\2\2\2U\u0147\3\2\2\2W\u0149\3\2\2\2Y\u014b"+
		"\3\2\2\2[\u014d\3\2\2\2]\u014f\3\2\2\2_\u0151\3\2\2\2a\u0153\3\2\2\2c"+
		"\u0155\3\2\2\2e\u0157\3\2\2\2g\u0159\3\2\2\2i\u015c\3\2\2\2kl\t\2\2\2"+
		"l\4\3\2\2\2mn\t\3\2\2n\6\3\2\2\2or\5\3\2\2pr\5\5\3\2qo\3\2\2\2qp\3\2\2"+
		"\2r\b\3\2\2\2st\t\4\2\2t\n\3\2\2\2ux\5\t\5\2vx\t\5\2\2wu\3\2\2\2wv\3\2"+
		"\2\2x\f\3\2\2\2y|\5\13\6\2z|\t\6\2\2{y\3\2\2\2{z\3\2\2\2|\16\3\2\2\2}"+
		"\177\t\7\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3"+
		"\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\b\b\2\2\u0083\20\3\2\2\2\u0084"+
		"\u0085\7\61\2\2\u0085\u0086\7\61\2\2\u0086\u008a\3\2\2\2\u0087\u0089\13"+
		"\2\2\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u008b\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\t\b"+
		"\2\2\u008e\u008f\3\2\2\2\u008f\u0090\b\t\2\2\u0090\22\3\2\2\2\u0091\u0092"+
		"\7*\2\2\u0092\u0093\7,\2\2\u0093\u0097\3\2\2\2\u0094\u0096\13\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0098\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7,\2\2\u009b"+
		"\u009c\7+\2\2\u009c\u009d\3\2\2\2\u009d\u009e\b\n\2\2\u009e\24\3\2\2\2"+
		"\u009f\u00a0\7c\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7f\2\2\u00a2\26\3\2"+
		"\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7v\2\2\u00a6\30"+
		"\3\2\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7p\2\2\u00a9\32\3\2\2\2\u00aa"+
		"\u00ab\7e\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7u\2\2"+
		"\u00ae\u00af\7u\2\2\u00af\34\3\2\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7"+
		"z\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6"+
		"\7f\2\2\u00b6\u00b7\7u\2\2\u00b7\36\3\2\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba"+
		"\7u\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7w\2\2\u00bc\u00bd\7n\2\2\u00bd"+
		"\u00be\7n\2\2\u00be \3\2\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7g\2\2\u00c1"+
		"\u00c2\7v\2\2\u00c2\"\3\2\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7g\2\2\u00c5"+
		"\u00c6\7y\2\2\u00c6$\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7g\2\2\u00c9"+
		"\u00ca\7n\2\2\u00ca\u00cb\7h\2\2\u00cb&\3\2\2\2\u00cc\u00cd\7k\2\2\u00cd"+
		"\u00ce\7h\2\2\u00ce(\3\2\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7j\2\2\u00d1"+
		"\u00d2\7g\2\2\u00d2\u00d3\7p\2\2\u00d3*\3\2\2\2\u00d4\u00d5\7g\2\2\u00d5"+
		"\u00d6\7n\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8\7g\2\2\u00d8,\3\2\2\2\u00d9"+
		"\u00da\7y\2\2\u00da\u00db\7j\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7n\2\2"+
		"\u00dd\u00de\7g\2\2\u00de.\3\2\2\2\u00df\u00e0\7f\2\2\u00e0\u00e1\7q\2"+
		"\2\u00e1\60\3\2\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5"+
		"\7w\2\2\u00e5\u00e6\7g\2\2\u00e6\62\3\2\2\2\u00e7\u00e8\7h\2\2\u00e8\u00e9"+
		"\7c\2\2\u00e9\u00ea\7n\2\2\u00ea\u00eb\7u\2\2\u00eb\u00ec\7g\2\2\u00ec"+
		"\64\3\2\2\2\u00ed\u00ee\7d\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0\7q\2\2\u00f0"+
		"\u00f1\7n\2\2\u00f1\66\3\2\2\2\u00f2\u00f3\7w\2\2\u00f3\u00f4\7p\2\2\u00f4"+
		"\u00f5\7k\2\2\u00f5\u00f6\7v\2\2\u00f68\3\2\2\2\u00f7\u00f8\7k\2\2\u00f8"+
		"\u00f9\7p\2\2\u00f9\u00fa\7v\2\2\u00fa\u00fb\7\65\2\2\u00fb\u00fc\7\64"+
		"\2\2\u00fc:\3\2\2\2\u00fd\u00fe\7u\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100"+
		"\7t\2\2\u0100\u0101\7k\2\2\u0101\u0102\7p\2\2\u0102\u0103\7i\2\2\u0103"+
		"<\3\2\2\2\u0104\u010a\5\3\2\2\u0105\u0109\5\7\4\2\u0106\u0109\5\13\6\2"+
		"\u0107\u0109\7a\2\2\u0108\u0105\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0107"+
		"\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		">\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\5\13\6\2\u010e\u010d\3\2\2\2"+
		"\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u011b"+
		"\3\2\2\2\u0112\u0113\7\62\2\2\u0113\u0114\7z\2\2\u0114\u0116\3\2\2\2\u0115"+
		"\u0117\5\r\7\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2"+
		"\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u010e\3\2\2\2\u011a"+
		"\u0112\3\2\2\2\u011b@\3\2\2\2\u011c\u0129\t\t\2\2\u011d\u011e\7z\2\2\u011e"+
		"\u011f\5\r\7\2\u011f\u0120\5\r\7\2\u0120\u0129\3\2\2\2\u0121\u0125\7\f"+
		"\2\2\u0122\u0124\t\n\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2"+
		"\2\2\u0128\u011c\3\2\2\2\u0128\u011d\3\2\2\2\u0128\u0121\3\2\2\2\u0129"+
		"B\3\2\2\2\u012a\u012b\7^\2\2\u012b\u012c\5A!\2\u012cD\3\2\2\2\u012d\u0130"+
		"\5\7\4\2\u012e\u0130\5\13\6\2\u012f\u012d\3\2\2\2\u012f\u012e\3\2\2\2"+
		"\u0130F\3\2\2\2\u0131\u0136\7$\2\2\u0132\u0135\5E#\2\u0133\u0135\5C\""+
		"\2\u0134\u0132\3\2\2\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0137"+
		"\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139"+
		"\u013a\7$\2\2\u013aH\3\2\2\2\u013b\u013c\7}\2\2\u013cJ\3\2\2\2\u013d\u013e"+
		"\7\177\2\2\u013eL\3\2\2\2\u013f\u0140\7*\2\2\u0140N\3\2\2\2\u0141\u0142"+
		"\7+\2\2\u0142P\3\2\2\2\u0143\u0144\7<\2\2\u0144R\3\2\2\2\u0145\u0146\7"+
		"=\2\2\u0146T\3\2\2\2\u0147\u0148\7.\2\2\u0148V\3\2\2\2\u0149\u014a\7-"+
		"\2\2\u014aX\3\2\2\2\u014b\u014c\7/\2\2\u014cZ\3\2\2\2\u014d\u014e\7,\2"+
		"\2\u014e\\\3\2\2\2\u014f\u0150\7\61\2\2\u0150^\3\2\2\2\u0151\u0152\7`"+
		"\2\2\u0152`\3\2\2\2\u0153\u0154\7\60\2\2\u0154b\3\2\2\2\u0155\u0156\7"+
		"?\2\2\u0156d\3\2\2\2\u0157\u0158\7>\2\2\u0158f\3\2\2\2\u0159\u015a\7>"+
		"\2\2\u015a\u015b\7?\2\2\u015bh\3\2\2\2\u015c\u015d\7>\2\2\u015d\u015e"+
		"\7/\2\2\u015ej\3\2\2\2\23\2qw{\u0080\u008a\u0097\u0108\u010a\u0110\u0118"+
		"\u011a\u0125\u0128\u012f\u0134\u0136\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}