// Generated from VSOPParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VSOPParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SINGLELINE_COMMENT=1, AND=2, NOT=3, IN=4, CLASS=5, EXTENDS=6, ISNULL=7, 
		LET=8, NEW=9, SELF=10, IF=11, THEN=12, ELSE=13, WHILE=14, DO=15, TRUE=16, 
		FALSE=17, BOOL=18, UNIT=19, INT32=20, STRING=21, TYPE_INDENTIFIER=22, 
		OBJECT_IDENTIFIER=23, INTEGER_LITERAL=24, INVALID_HEX=25, INVALID_DECIMAL=26, 
		STRING_LITERAL=27, BAD_ESCAPE_LITERAL=28, BAD_STRING_LITERAL=29, LBRACE=30, 
		RBRACE=31, LPAR=32, RPAR=33, COLON=34, SEMICOLON=35, COMMA=36, PLUS=37, 
		MINUS=38, TIMES=39, DIV=40, POW=41, DOT=42, EQUAL=43, LOWER=44, LOWER_EQUAL=45, 
		ASSIGN=46, WS=47, MULTILINE_COMMENT=48, LPS=49;
	public static final int
		RULE_no = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"no"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'and'", "'not'", "'in'", "'class'", "'extends'", "'isnull'", 
			"'let'", "'new'", "'self'", "'if'", "'then'", "'else'", "'while'", "'do'", 
			"'true'", "'false'", "'bool'", "'unit'", "'int32'", "'string'", null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "'('", "')'", 
			"':'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'^'", "'.'", "'='", 
			"'<'", "'<='", "'<-'", null, null, "'(*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SINGLELINE_COMMENT", "AND", "NOT", "IN", "CLASS", "EXTENDS", "ISNULL", 
			"LET", "NEW", "SELF", "IF", "THEN", "ELSE", "WHILE", "DO", "TRUE", "FALSE", 
			"BOOL", "UNIT", "INT32", "STRING", "TYPE_INDENTIFIER", "OBJECT_IDENTIFIER", 
			"INTEGER_LITERAL", "INVALID_HEX", "INVALID_DECIMAL", "STRING_LITERAL", 
			"BAD_ESCAPE_LITERAL", "BAD_STRING_LITERAL", "LBRACE", "RBRACE", "LPAR", 
			"RPAR", "COLON", "SEMICOLON", "COMMA", "PLUS", "MINUS", "TIMES", "DIV", 
			"POW", "DOT", "EQUAL", "LOWER", "LOWER_EQUAL", "ASSIGN", "WS", "MULTILINE_COMMENT", 
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

	@Override
	public String getGrammarFileName() { return "VSOPParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VSOPParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NoContext extends ParserRuleContext {
		public NoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterNo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitNo(this);
		}
	}

	public final NoContext no() throws RecognitionException {
		NoContext _localctx = new NoContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_no);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\7\4\2\t\2\3\2"+
		"\3\2\3\2\2\2\3\2\2\2\2\5\2\4\3\2\2\2\4\5\3\2\2\2\5\3\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}