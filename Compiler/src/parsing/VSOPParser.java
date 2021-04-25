// Generated from e:\Data\David\git\Compiler-VSOP-LLVM\Compiler\grammars\VSOPParser.g4 by ANTLR 4.8
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
		RULE_program = 0, RULE_clazz = 1, RULE_classBody = 2, RULE_field = 3, 
		RULE_method = 4, RULE_type = 5, RULE_formals = 6, RULE_formal = 7, RULE_block = 8, 
		RULE_expr = 9, RULE_args = 10, RULE_literal = 11, RULE_booleanLiteral = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "clazz", "classBody", "field", "method", "type", "formals", 
			"formal", "block", "expr", "args", "literal", "booleanLiteral"
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

	public static class ProgramContext extends ParserRuleContext {
		public List<ClazzContext> clazz() {
			return getRuleContexts(ClazzContext.class);
		}
		public ClazzContext clazz(int i) {
			return getRuleContext(ClazzContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				clazz();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
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

	public static class ClazzContext extends ParserRuleContext {
		public Token id;
		public Token idext;
		public TerminalNode CLASS() { return getToken(VSOPParser.CLASS, 0); }
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public List<TerminalNode> TYPE_IDENTIFIER() { return getTokens(VSOPParser.TYPE_IDENTIFIER); }
		public TerminalNode TYPE_IDENTIFIER(int i) {
			return getToken(VSOPParser.TYPE_IDENTIFIER, i);
		}
		public TerminalNode EXTENDS() { return getToken(VSOPParser.EXTENDS, 0); }
		public ClazzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clazz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterClazz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitClazz(this);
		}
	}

	public final ClazzContext clazz() throws RecognitionException {
		ClazzContext _localctx = new ClazzContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clazz);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(CLASS);
			setState(32);
			((ClazzContext)_localctx).id = match(TYPE_IDENTIFIER);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(33);
				match(EXTENDS);
				setState(34);
				((ClazzContext)_localctx).idext = match(TYPE_IDENTIFIER);
				}
			}

			setState(37);
			classBody();
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

	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(VSOPParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(VSOPParser.RBRACE, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(LBRACE);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBJECT_IDENTIFIER) {
				{
				setState(42);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(40);
					field();
					}
					break;
				case 2:
					{
					setState(41);
					method();
					}
					break;
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			match(RBRACE);
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

	public static class FieldContext extends ParserRuleContext {
		public Token id;
		public TerminalNode COLON() { return getToken(VSOPParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(VSOPParser.SEMICOLON, 0); }
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(VSOPParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((FieldContext)_localctx).id = match(OBJECT_IDENTIFIER);
			setState(50);
			match(COLON);
			setState(51);
			type();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(52);
				match(ASSIGN);
				setState(53);
				expr(0);
				}
			}

			setState(56);
			match(SEMICOLON);
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

	public static class MethodContext extends ParserRuleContext {
		public Token id;
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public FormalsContext formals() {
			return getRuleContext(FormalsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public TerminalNode COLON() { return getToken(VSOPParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitMethod(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((MethodContext)_localctx).id = match(OBJECT_IDENTIFIER);
			setState(59);
			match(LPAR);
			setState(60);
			formals();
			setState(61);
			match(RPAR);
			setState(62);
			match(COLON);
			setState(63);
			type();
			setState(64);
			block();
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE_IDENTIFIER() { return getToken(VSOPParser.TYPE_IDENTIFIER, 0); }
		public TerminalNode INT32() { return getToken(VSOPParser.INT32, 0); }
		public TerminalNode BOOL() { return getToken(VSOPParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(VSOPParser.STRING, 0); }
		public TerminalNode UNIT() { return getToken(VSOPParser.UNIT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << UNIT) | (1L << INT32) | (1L << STRING) | (1L << TYPE_IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class FormalsContext extends ParserRuleContext {
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(VSOPParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(VSOPParser.COMMA, i);
		}
		public FormalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterFormals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitFormals(this);
		}
	}

	public final FormalsContext formals() throws RecognitionException {
		FormalsContext _localctx = new FormalsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OBJECT_IDENTIFIER) {
				{
				setState(68);
				formal();
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(69);
					match(COMMA);
					setState(70);
					formal();
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

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

	public static class FormalContext extends ParserRuleContext {
		public Token id;
		public TerminalNode COLON() { return getToken(VSOPParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitFormal(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			((FormalContext)_localctx).id = match(OBJECT_IDENTIFIER);
			setState(79);
			match(COLON);
			setState(80);
			type();
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

	public static class BlockContext extends ParserRuleContext {
		public ExprContext ex1;
		public ExprContext ex2;
		public TerminalNode LBRACE() { return getToken(VSOPParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(VSOPParser.RBRACE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(VSOPParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(VSOPParser.SEMICOLON, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(LBRACE);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << ISNULL) | (1L << LET) | (1L << NEW) | (1L << SELF) | (1L << IF) | (1L << WHILE) | (1L << TRUE) | (1L << FALSE) | (1L << OBJECT_IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL) | (1L << LBRACE) | (1L << LPAR) | (1L << RPAR) | (1L << MINUS))) != 0)) {
				{
				setState(83);
				((BlockContext)_localctx).ex1 = expr(0);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(84);
					match(SEMICOLON);
					setState(85);
					((BlockContext)_localctx).ex2 = expr(0);
					}
					}
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(93);
			match(RBRACE);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssContext extends ExprContext {
		public Token id;
		public TerminalNode ASSIGN() { return getToken(VSOPParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public AssContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterAss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitAss(this);
		}
	}
	public static class NewContext extends ExprContext {
		public Token id;
		public TerminalNode NEW() { return getToken(VSOPParser.NEW, 0); }
		public TerminalNode TYPE_IDENTIFIER() { return getToken(VSOPParser.TYPE_IDENTIFIER, 0); }
		public NewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitNew(this);
		}
	}
	public static class MinusContext extends ExprContext {
		public TerminalNode MINUS() { return getToken(VSOPParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitMinus(this);
		}
	}
	public static class IsnullContext extends ExprContext {
		public TerminalNode ISNULL() { return getToken(VSOPParser.ISNULL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsnullContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterIsnull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitIsnull(this);
		}
	}
	public static class BlContext extends ExprContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterBl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitBl(this);
		}
	}
	public static class WhileContext extends ExprContext {
		public TerminalNode WHILE() { return getToken(VSOPParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(VSOPParser.DO, 0); }
		public WhileContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitWhile(this);
		}
	}
	public static class CallContext extends ExprContext {
		public Token id;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(VSOPParser.DOT, 0); }
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public CallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitCall(this);
		}
	}
	public static class UnitContext extends ExprContext {
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public UnitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitUnit(this);
		}
	}
	public static class NotContext extends ExprContext {
		public TerminalNode NOT() { return getToken(VSOPParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitNot(this);
		}
	}
	public static class LitContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitLit(this);
		}
	}
	public static class SelfContext extends ExprContext {
		public TerminalNode SELF() { return getToken(VSOPParser.SELF, 0); }
		public SelfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterSelf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitSelf(this);
		}
	}
	public static class SelfcallContext extends ExprContext {
		public Token id;
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public SelfcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterSelfcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitSelfcall(this);
		}
	}
	public static class OiContext extends ExprContext {
		public Token id;
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public OiContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterOi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitOi(this);
		}
	}
	public static class LetContext extends ExprContext {
		public Token id;
		public ExprContext as;
		public ExprContext ex;
		public TerminalNode LET() { return getToken(VSOPParser.LET, 0); }
		public TerminalNode COLON() { return getToken(VSOPParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IN() { return getToken(VSOPParser.IN, 0); }
		public TerminalNode OBJECT_IDENTIFIER() { return getToken(VSOPParser.OBJECT_IDENTIFIER, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(VSOPParser.ASSIGN, 0); }
		public LetContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitLet(this);
		}
	}
	public static class BraceExprContext extends ExprContext {
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public BraceExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterBraceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitBraceExpr(this);
		}
	}
	public static class IfContext extends ExprContext {
		public TerminalNode IF() { return getToken(VSOPParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(VSOPParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(VSOPParser.ELSE, 0); }
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitIf(this);
		}
	}
	public static class BinopContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode POW() { return getToken(VSOPParser.POW, 0); }
		public TerminalNode TIMES() { return getToken(VSOPParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(VSOPParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(VSOPParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(VSOPParser.MINUS, 0); }
		public TerminalNode EQUAL() { return getToken(VSOPParser.EQUAL, 0); }
		public TerminalNode LOWER() { return getToken(VSOPParser.LOWER, 0); }
		public TerminalNode LOWER_EQUAL() { return getToken(VSOPParser.LOWER_EQUAL, 0); }
		public TerminalNode AND() { return getToken(VSOPParser.AND, 0); }
		public BinopContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterBinop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitBinop(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new BlContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(96);
				block();
				}
				break;
			case 2:
				{
				_localctx = new BraceExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				match(LPAR);
				setState(98);
				expr(0);
				setState(99);
				match(RPAR);
				}
				break;
			case 3:
				{
				_localctx = new UnitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				match(RPAR);
				setState(102);
				match(LPAR);
				}
				break;
			case 4:
				{
				_localctx = new LitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				literal();
				}
				break;
			case 5:
				{
				_localctx = new SelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(SELF);
				}
				break;
			case 6:
				{
				_localctx = new OiContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				((OiContext)_localctx).id = match(OBJECT_IDENTIFIER);
				}
				break;
			case 7:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(NEW);
				setState(107);
				((NewContext)_localctx).id = match(TYPE_IDENTIFIER);
				}
				break;
			case 8:
				{
				_localctx = new SelfcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				((SelfcallContext)_localctx).id = match(OBJECT_IDENTIFIER);
				setState(109);
				match(LPAR);
				setState(110);
				args();
				setState(111);
				match(RPAR);
				}
				break;
			case 9:
				{
				_localctx = new IsnullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(ISNULL);
				setState(114);
				expr(11);
				}
				break;
			case 10:
				{
				_localctx = new MinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(MINUS);
				setState(116);
				expr(10);
				}
				break;
			case 11:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(NOT);
				setState(118);
				expr(6);
				}
				break;
			case 12:
				{
				_localctx = new AssContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				((AssContext)_localctx).id = match(OBJECT_IDENTIFIER);
				setState(120);
				match(ASSIGN);
				setState(121);
				expr(4);
				}
				break;
			case 13:
				{
				_localctx = new LetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(LET);
				setState(123);
				((LetContext)_localctx).id = match(OBJECT_IDENTIFIER);
				setState(124);
				match(COLON);
				setState(125);
				type();
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(126);
					match(ASSIGN);
					setState(127);
					((LetContext)_localctx).as = expr(0);
					}
				}

				setState(130);
				match(IN);
				setState(131);
				((LetContext)_localctx).ex = expr(3);
				}
				break;
			case 14:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(WHILE);
				setState(134);
				expr(0);
				setState(135);
				match(DO);
				setState(136);
				expr(2);
				}
				break;
			case 15:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(IF);
				setState(139);
				expr(0);
				setState(140);
				match(THEN);
				setState(141);
				expr(0);
				setState(144);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(142);
					match(ELSE);
					setState(143);
					expr(0);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(172);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(170);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new BinopContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(149);
						((BinopContext)_localctx).op = match(POW);
						setState(150);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new BinopContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(151);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(152);
						((BinopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==TIMES || _la==DIV) ) {
							((BinopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(153);
						expr(10);
						}
						break;
					case 3:
						{
						_localctx = new BinopContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(155);
						((BinopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((BinopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(156);
						expr(9);
						}
						break;
					case 4:
						{
						_localctx = new BinopContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(158);
						((BinopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << LOWER) | (1L << LOWER_EQUAL))) != 0)) ) {
							((BinopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(159);
						expr(8);
						}
						break;
					case 5:
						{
						_localctx = new BinopContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(161);
						((BinopContext)_localctx).op = match(AND);
						setState(162);
						expr(6);
						}
						break;
					case 6:
						{
						_localctx = new CallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(164);
						match(DOT);
						setState(165);
						((CallContext)_localctx).id = match(OBJECT_IDENTIFIER);
						setState(166);
						match(LPAR);
						setState(167);
						args();
						setState(168);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(174);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public ExprContext expr;
		public List<ExprContext> ex = new ArrayList<ExprContext>();
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(VSOPParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(VSOPParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(175);
				((ArgsContext)_localctx).expr = expr(0);
				((ArgsContext)_localctx).ex.add(((ArgsContext)_localctx).expr);
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(176);
					match(COMMA);
					setState(177);
					((ArgsContext)_localctx).expr = expr(0);
					((ArgsContext)_localctx).ex.add(((ArgsContext)_localctx).expr);
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(VSOPParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(VSOPParser.RPAR, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(VSOPParser.INTEGER_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(VSOPParser.STRING_LITERAL, 0); }
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(LPAR);
				setState(186);
				match(RPAR);
				}
				break;
			case INTEGER_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(INTEGER_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				match(STRING_LITERAL);
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(189);
				booleanLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BooleanLiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(VSOPParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(VSOPParser.FALSE, 0); }
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VSOPParserListener ) ((VSOPParserListener)listener).exitBooleanLiteral(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u00c5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\6\2\36\n\2\r\2\16\2\37\3\3\3\3\3\3"+
		"\3\3\5\3&\n\3\3\3\3\3\3\4\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\5\59\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\b\7\bJ\n\b\f\b\16\bM\13\b\5\bO\n\b\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\7\nY\n\n\f\n\16\n\\\13\n\5\n^\n\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u0083\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u0093\n\13\5\13\u0095\n\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\7\13\u00ad\n\13\f\13\16\13\u00b0\13\13\3"+
		"\f\3\f\3\f\7\f\u00b5\n\f\f\f\16\f\u00b8\13\f\5\f\u00ba\n\f\3\r\3\r\3\r"+
		"\3\r\3\r\5\r\u00c1\n\r\3\16\3\16\3\16\2\3\24\17\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\2\7\3\2\24\30\3\2+,\3\2)*\3\2/\61\3\2\22\23\2\u00db\2\35\3\2"+
		"\2\2\4!\3\2\2\2\6)\3\2\2\2\b\63\3\2\2\2\n<\3\2\2\2\fD\3\2\2\2\16N\3\2"+
		"\2\2\20P\3\2\2\2\22T\3\2\2\2\24\u0094\3\2\2\2\26\u00b9\3\2\2\2\30\u00c0"+
		"\3\2\2\2\32\u00c2\3\2\2\2\34\36\5\4\3\2\35\34\3\2\2\2\36\37\3\2\2\2\37"+
		"\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2!\"\7\7\2\2\"%\7\30\2\2#$\7\b\2\2$&"+
		"\7\30\2\2%#\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\6\4\2(\5\3\2\2\2).\7\"\2"+
		"\2*-\5\b\5\2+-\5\n\6\2,*\3\2\2\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2"+
		"\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7#\2\2\62\7\3\2\2\2\63\64\7\31\2\2"+
		"\64\65\7&\2\2\658\5\f\7\2\66\67\7\62\2\2\679\5\24\13\28\66\3\2\2\289\3"+
		"\2\2\29:\3\2\2\2:;\7\'\2\2;\t\3\2\2\2<=\7\31\2\2=>\7$\2\2>?\5\16\b\2?"+
		"@\7%\2\2@A\7&\2\2AB\5\f\7\2BC\5\22\n\2C\13\3\2\2\2DE\t\2\2\2E\r\3\2\2"+
		"\2FK\5\20\t\2GH\7(\2\2HJ\5\20\t\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2"+
		"\2\2LO\3\2\2\2MK\3\2\2\2NF\3\2\2\2NO\3\2\2\2O\17\3\2\2\2PQ\7\31\2\2QR"+
		"\7&\2\2RS\5\f\7\2S\21\3\2\2\2T]\7\"\2\2UZ\5\24\13\2VW\7\'\2\2WY\5\24\13"+
		"\2XV\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2]U\3\2"+
		"\2\2]^\3\2\2\2^_\3\2\2\2_`\7#\2\2`\23\3\2\2\2ab\b\13\1\2b\u0095\5\22\n"+
		"\2cd\7$\2\2de\5\24\13\2ef\7%\2\2f\u0095\3\2\2\2gh\7%\2\2h\u0095\7$\2\2"+
		"i\u0095\5\30\r\2j\u0095\7\f\2\2k\u0095\7\31\2\2lm\7\13\2\2m\u0095\7\30"+
		"\2\2no\7\31\2\2op\7$\2\2pq\5\26\f\2qr\7%\2\2r\u0095\3\2\2\2st\7\t\2\2"+
		"t\u0095\5\24\13\ruv\7*\2\2v\u0095\5\24\13\fwx\7\5\2\2x\u0095\5\24\13\b"+
		"yz\7\31\2\2z{\7\62\2\2{\u0095\5\24\13\6|}\7\n\2\2}~\7\31\2\2~\177\7&\2"+
		"\2\177\u0082\5\f\7\2\u0080\u0081\7\62\2\2\u0081\u0083\5\24\13\2\u0082"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\6"+
		"\2\2\u0085\u0086\5\24\13\5\u0086\u0095\3\2\2\2\u0087\u0088\7\20\2\2\u0088"+
		"\u0089\5\24\13\2\u0089\u008a\7\21\2\2\u008a\u008b\5\24\13\4\u008b\u0095"+
		"\3\2\2\2\u008c\u008d\7\r\2\2\u008d\u008e\5\24\13\2\u008e\u008f\7\16\2"+
		"\2\u008f\u0092\5\24\13\2\u0090\u0091\7\17\2\2\u0091\u0093\5\24\13\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094a\3\2\2\2"+
		"\u0094c\3\2\2\2\u0094g\3\2\2\2\u0094i\3\2\2\2\u0094j\3\2\2\2\u0094k\3"+
		"\2\2\2\u0094l\3\2\2\2\u0094n\3\2\2\2\u0094s\3\2\2\2\u0094u\3\2\2\2\u0094"+
		"w\3\2\2\2\u0094y\3\2\2\2\u0094|\3\2\2\2\u0094\u0087\3\2\2\2\u0094\u008c"+
		"\3\2\2\2\u0095\u00ae\3\2\2\2\u0096\u0097\f\16\2\2\u0097\u0098\7-\2\2\u0098"+
		"\u00ad\5\24\13\17\u0099\u009a\f\13\2\2\u009a\u009b\t\3\2\2\u009b\u00ad"+
		"\5\24\13\f\u009c\u009d\f\n\2\2\u009d\u009e\t\4\2\2\u009e\u00ad\5\24\13"+
		"\13\u009f\u00a0\f\t\2\2\u00a0\u00a1\t\5\2\2\u00a1\u00ad\5\24\13\n\u00a2"+
		"\u00a3\f\7\2\2\u00a3\u00a4\7\4\2\2\u00a4\u00ad\5\24\13\b\u00a5\u00a6\f"+
		"\20\2\2\u00a6\u00a7\7.\2\2\u00a7\u00a8\7\31\2\2\u00a8\u00a9\7$\2\2\u00a9"+
		"\u00aa\5\26\f\2\u00aa\u00ab\7%\2\2\u00ab\u00ad\3\2\2\2\u00ac\u0096\3\2"+
		"\2\2\u00ac\u0099\3\2\2\2\u00ac\u009c\3\2\2\2\u00ac\u009f\3\2\2\2\u00ac"+
		"\u00a2\3\2\2\2\u00ac\u00a5\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af\25\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b6"+
		"\5\24\13\2\u00b2\u00b3\7(\2\2\u00b3\u00b5\5\24\13\2\u00b4\u00b2\3\2\2"+
		"\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00ba"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\27\3\2\2\2\u00bb\u00bc\7$\2\2\u00bc\u00c1\7%\2\2\u00bd\u00c1\7\32\2\2"+
		"\u00be\u00c1\7\35\2\2\u00bf\u00c1\5\32\16\2\u00c0\u00bb\3\2\2\2\u00c0"+
		"\u00bd\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\31\3\2\2"+
		"\2\u00c2\u00c3\t\6\2\2\u00c3\33\3\2\2\2\23\37%,.8KNZ]\u0082\u0092\u0094"+
		"\u00ac\u00ae\u00b6\u00b9\u00c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}