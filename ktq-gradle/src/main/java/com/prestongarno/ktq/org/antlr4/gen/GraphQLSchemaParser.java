// Generated from /home/preston/IdeaProjects/ktq-gradle/src/main/resources/GraphQLSchema.g4 by ANTLR 4.7
package com.prestongarno.ktq.org.antlr4.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GraphQLSchemaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		Name=18, BooleanValue=19, NullValue=20, Null=21, IntValue=22, FloatValue=23, 
		Sign=24, IntegerPart=25, NonZeroDigit=26, ExponentPart=27, Digit=28, StringValue=29, 
		Ignored=30;
	public static final int
		RULE_graphqlSchema = 0, RULE_typeDef = 1, RULE_implementationDefs = 2, 
		RULE_inputTypeDef = 3, RULE_interfaceDef = 4, RULE_scalarDef = 5, RULE_unionDef = 6, 
		RULE_unionTypes = 7, RULE_enumDef = 8, RULE_scalarName = 9, RULE_fieldDef = 10, 
		RULE_fieldArgs = 11, RULE_fieldName = 12, RULE_argument = 13, RULE_typeSpec = 14, 
		RULE_listType = 15, RULE_nullable = 16, RULE_typeName = 17, RULE_defaultValue = 18, 
		RULE_value = 19, RULE_enumValue = 20, RULE_arrayValue = 21, RULE_objectValue = 22, 
		RULE_objectField = 23;
	public static final String[] ruleNames = {
		"graphqlSchema", "typeDef", "implementationDefs", "inputTypeDef", "interfaceDef", 
		"scalarDef", "unionDef", "unionTypes", "enumDef", "scalarName", "fieldDef", 
		"fieldArgs", "fieldName", "argument", "typeSpec", "listType", "nullable", 
		"typeName", "defaultValue", "value", "enumValue", "arrayValue", "objectValue", 
		"objectField"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'type'", "'{'", "'}'", "'implements'", "'input'", "'interface'", 
		"'scalar'", "'union'", "'='", "'|'", "'enum'", "':'", "'('", "')'", "'['", 
		"']'", "'!'", null, null, null, "'null'", null, null, "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "Name", "BooleanValue", "NullValue", 
		"Null", "IntValue", "FloatValue", "Sign", "IntegerPart", "NonZeroDigit", 
		"ExponentPart", "Digit", "StringValue", "Ignored"
	};
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
	public String getGrammarFileName() { return "GraphQLSchema.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GraphQLSchemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GraphqlSchemaContext extends ParserRuleContext {
		public List<TypeDefContext> typeDef() {
			return getRuleContexts(TypeDefContext.class);
		}
		public TypeDefContext typeDef(int i) {
			return getRuleContext(TypeDefContext.class,i);
		}
		public List<InputTypeDefContext> inputTypeDef() {
			return getRuleContexts(InputTypeDefContext.class);
		}
		public InputTypeDefContext inputTypeDef(int i) {
			return getRuleContext(InputTypeDefContext.class,i);
		}
		public List<UnionDefContext> unionDef() {
			return getRuleContexts(UnionDefContext.class);
		}
		public UnionDefContext unionDef(int i) {
			return getRuleContext(UnionDefContext.class,i);
		}
		public List<EnumDefContext> enumDef() {
			return getRuleContexts(EnumDefContext.class);
		}
		public EnumDefContext enumDef(int i) {
			return getRuleContext(EnumDefContext.class,i);
		}
		public List<InterfaceDefContext> interfaceDef() {
			return getRuleContexts(InterfaceDefContext.class);
		}
		public InterfaceDefContext interfaceDef(int i) {
			return getRuleContext(InterfaceDefContext.class,i);
		}
		public List<ScalarDefContext> scalarDef() {
			return getRuleContexts(ScalarDefContext.class);
		}
		public ScalarDefContext scalarDef(int i) {
			return getRuleContext(ScalarDefContext.class,i);
		}
		public GraphqlSchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphqlSchema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterGraphqlSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitGraphqlSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitGraphqlSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GraphqlSchemaContext graphqlSchema() throws RecognitionException {
		GraphqlSchemaContext _localctx = new GraphqlSchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_graphqlSchema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10))) != 0)) {
				{
				setState(54);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(48);
					typeDef();
					}
					break;
				case T__4:
					{
					setState(49);
					inputTypeDef();
					}
					break;
				case T__7:
					{
					setState(50);
					unionDef();
					}
					break;
				case T__10:
					{
					setState(51);
					enumDef();
					}
					break;
				case T__5:
					{
					setState(52);
					interfaceDef();
					}
					break;
				case T__6:
					{
					setState(53);
					scalarDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class TypeDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ImplementationDefsContext implementationDefs() {
			return getRuleContext(ImplementationDefsContext.class,0);
		}
		public List<FieldDefContext> fieldDef() {
			return getRuleContexts(FieldDefContext.class);
		}
		public FieldDefContext fieldDef(int i) {
			return getRuleContext(FieldDefContext.class,i);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_typeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__0);
			setState(60);
			typeName();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(61);
				implementationDefs();
				}
			}

			setState(64);
			match(T__1);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				fieldDef();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
			setState(70);
			match(T__2);
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

	public static class ImplementationDefsContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public ImplementationDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implementationDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterImplementationDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitImplementationDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitImplementationDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplementationDefsContext implementationDefs() throws RecognitionException {
		ImplementationDefsContext _localctx = new ImplementationDefsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_implementationDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__3);
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(73);
				typeName();
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
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

	public static class InputTypeDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<FieldDefContext> fieldDef() {
			return getRuleContexts(FieldDefContext.class);
		}
		public FieldDefContext fieldDef(int i) {
			return getRuleContext(FieldDefContext.class,i);
		}
		public InputTypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputTypeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterInputTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitInputTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitInputTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputTypeDefContext inputTypeDef() throws RecognitionException {
		InputTypeDefContext _localctx = new InputTypeDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_inputTypeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__4);
			setState(79);
			typeName();
			setState(80);
			match(T__1);
			setState(82); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				fieldDef();
				}
				}
				setState(84); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
			setState(86);
			match(T__2);
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

	public static class InterfaceDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<FieldDefContext> fieldDef() {
			return getRuleContexts(FieldDefContext.class);
		}
		public FieldDefContext fieldDef(int i) {
			return getRuleContext(FieldDefContext.class,i);
		}
		public InterfaceDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterInterfaceDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitInterfaceDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitInterfaceDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceDefContext interfaceDef() throws RecognitionException {
		InterfaceDefContext _localctx = new InterfaceDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_interfaceDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__5);
			setState(89);
			typeName();
			setState(90);
			match(T__1);
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(91);
				fieldDef();
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
			setState(96);
			match(T__2);
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

	public static class ScalarDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ScalarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterScalarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitScalarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitScalarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarDefContext scalarDef() throws RecognitionException {
		ScalarDefContext _localctx = new ScalarDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_scalarDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__6);
			setState(99);
			typeName();
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

	public static class UnionDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public UnionTypesContext unionTypes() {
			return getRuleContext(UnionTypesContext.class,0);
		}
		public UnionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterUnionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitUnionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitUnionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionDefContext unionDef() throws RecognitionException {
		UnionDefContext _localctx = new UnionDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unionDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__7);
			setState(102);
			typeName();
			setState(103);
			match(T__8);
			setState(104);
			unionTypes();
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

	public static class UnionTypesContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public UnionTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterUnionTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitUnionTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitUnionTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionTypesContext unionTypes() throws RecognitionException {
		UnionTypesContext _localctx = new UnionTypesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unionTypes);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(106);
					typeName();
					setState(107);
					match(T__9);
					}
					} 
				}
				setState(113);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(114);
			typeName();
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

	public static class EnumDefContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ScalarNameContext> scalarName() {
			return getRuleContexts(ScalarNameContext.class);
		}
		public ScalarNameContext scalarName(int i) {
			return getRuleContext(ScalarNameContext.class,i);
		}
		public EnumDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterEnumDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitEnumDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitEnumDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDefContext enumDef() throws RecognitionException {
		EnumDefContext _localctx = new EnumDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_enumDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__10);
			setState(117);
			typeName();
			setState(118);
			match(T__1);
			setState(120); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(119);
				scalarName();
				}
				}
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
			setState(124);
			match(T__2);
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

	public static class ScalarNameContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public ScalarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterScalarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitScalarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitScalarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarNameContext scalarName() throws RecognitionException {
		ScalarNameContext _localctx = new ScalarNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_scalarName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(Name);
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

	public static class FieldDefContext extends ParserRuleContext {
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public TypeSpecContext typeSpec() {
			return getRuleContext(TypeSpecContext.class,0);
		}
		public FieldArgsContext fieldArgs() {
			return getRuleContext(FieldArgsContext.class,0);
		}
		public FieldDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterFieldDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitFieldDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitFieldDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDefContext fieldDef() throws RecognitionException {
		FieldDefContext _localctx = new FieldDefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fieldDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			fieldName();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(129);
				fieldArgs();
				}
			}

			setState(132);
			match(T__11);
			setState(133);
			typeSpec();
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

	public static class FieldArgsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public FieldArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterFieldArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitFieldArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitFieldArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldArgsContext fieldArgs() throws RecognitionException {
		FieldArgsContext _localctx = new FieldArgsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fieldArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(T__12);
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136);
				argument();
				}
				}
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Name );
			setState(141);
			match(T__13);
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

	public static class FieldNameContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(Name);
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

	public static class ArgumentContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public TypeSpecContext typeSpec() {
			return getRuleContext(TypeSpecContext.class,0);
		}
		public NullableContext nullable() {
			return getRuleContext(NullableContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(Name);
			setState(146);
			match(T__11);
			setState(147);
			typeSpec();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(148);
				nullable();
				}
			}

			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(151);
				defaultValue();
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

	public static class TypeSpecContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ListTypeContext listType() {
			return getRuleContext(ListTypeContext.class,0);
		}
		public NullableContext nullable() {
			return getRuleContext(NullableContext.class,0);
		}
		public TypeSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterTypeSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitTypeSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitTypeSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSpecContext typeSpec() throws RecognitionException {
		TypeSpecContext _localctx = new TypeSpecContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Name:
				{
				setState(154);
				typeName();
				}
				break;
			case T__14:
				{
				setState(155);
				listType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(158);
				nullable();
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

	public static class ListTypeContext extends ParserRuleContext {
		public TypeSpecContext typeSpec() {
			return getRuleContext(TypeSpecContext.class,0);
		}
		public ListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterListType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitListType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitListType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTypeContext listType() throws RecognitionException {
		ListTypeContext _localctx = new ListTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_listType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__14);
			setState(162);
			typeSpec();
			setState(163);
			match(T__15);
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

	public static class NullableContext extends ParserRuleContext {
		public NullableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterNullable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitNullable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitNullable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullableContext nullable() throws RecognitionException {
		NullableContext _localctx = new NullableContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_nullable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(T__16);
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

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(Name);
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

	public static class DefaultValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__8);
			setState(170);
			value();
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode IntValue() { return getToken(GraphQLSchemaParser.IntValue, 0); }
		public TerminalNode FloatValue() { return getToken(GraphQLSchemaParser.FloatValue, 0); }
		public TerminalNode StringValue() { return getToken(GraphQLSchemaParser.StringValue, 0); }
		public TerminalNode BooleanValue() { return getToken(GraphQLSchemaParser.BooleanValue, 0); }
		public TerminalNode NullValue() { return getToken(GraphQLSchemaParser.NullValue, 0); }
		public EnumValueContext enumValue() {
			return getRuleContext(EnumValueContext.class,0);
		}
		public ArrayValueContext arrayValue() {
			return getRuleContext(ArrayValueContext.class,0);
		}
		public ObjectValueContext objectValue() {
			return getRuleContext(ObjectValueContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_value);
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntValue:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(IntValue);
				}
				break;
			case FloatValue:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(FloatValue);
				}
				break;
			case StringValue:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				match(StringValue);
				}
				break;
			case BooleanValue:
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				match(BooleanValue);
				}
				break;
			case NullValue:
				enterOuterAlt(_localctx, 5);
				{
				setState(176);
				match(NullValue);
				}
				break;
			case Name:
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
				enumValue();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 7);
				{
				setState(178);
				arrayValue();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 8);
				{
				setState(179);
				objectValue();
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

	public static class EnumValueContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public EnumValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterEnumValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitEnumValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitEnumValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumValueContext enumValue() throws RecognitionException {
		EnumValueContext _localctx = new EnumValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_enumValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(Name);
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

	public static class ArrayValueContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ArrayValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterArrayValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitArrayValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitArrayValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayValueContext arrayValue() throws RecognitionException {
		ArrayValueContext _localctx = new ArrayValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arrayValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(T__14);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__14) | (1L << Name) | (1L << BooleanValue) | (1L << NullValue) | (1L << IntValue) | (1L << FloatValue) | (1L << StringValue))) != 0)) {
				{
				{
				setState(185);
				value();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
			match(T__15);
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

	public static class ObjectValueContext extends ParserRuleContext {
		public List<ObjectFieldContext> objectField() {
			return getRuleContexts(ObjectFieldContext.class);
		}
		public ObjectFieldContext objectField(int i) {
			return getRuleContext(ObjectFieldContext.class,i);
		}
		public ObjectValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterObjectValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitObjectValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitObjectValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectValueContext objectValue() throws RecognitionException {
		ObjectValueContext _localctx = new ObjectValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_objectValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__1);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(194);
				objectField();
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200);
			match(T__2);
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

	public static class ObjectFieldContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(GraphQLSchemaParser.Name, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ObjectFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).enterObjectField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GraphQLSchemaListener ) ((GraphQLSchemaListener)listener).exitObjectField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GraphQLSchemaVisitor ) return ((GraphQLSchemaVisitor<? extends T>)visitor).visitObjectField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectFieldContext objectField() throws RecognitionException {
		ObjectFieldContext _localctx = new ObjectFieldContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_objectField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(Name);
			setState(203);
			match(T__11);
			setState(204);
			value();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00d1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\3\3\3\3\3\5\3A\n\3"+
		"\3\3\3\3\6\3E\n\3\r\3\16\3F\3\3\3\3\3\4\3\4\6\4M\n\4\r\4\16\4N\3\5\3\5"+
		"\3\5\3\5\6\5U\n\5\r\5\16\5V\3\5\3\5\3\6\3\6\3\6\3\6\6\6_\n\6\r\6\16\6"+
		"`\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\tp\n\t\f\t\16"+
		"\ts\13\t\3\t\3\t\3\n\3\n\3\n\3\n\6\n{\n\n\r\n\16\n|\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\5\f\u0085\n\f\3\f\3\f\3\f\3\r\3\r\6\r\u008c\n\r\r\r\16\r\u008d"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u0098\n\17\3\17\5\17\u009b"+
		"\n\17\3\20\3\20\5\20\u009f\n\20\3\20\5\20\u00a2\n\20\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\5\25\u00b7\n\25\3\26\3\26\3\27\3\27\7\27\u00bd\n\27\f\27\16\27"+
		"\u00c0\13\27\3\27\3\27\3\30\3\30\7\30\u00c6\n\30\f\30\16\30\u00c9\13\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\2\2\32\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\2\2\2\u00d4\2:\3\2\2\2\4=\3\2\2\2\6J\3\2\2\2"+
		"\bP\3\2\2\2\nZ\3\2\2\2\fd\3\2\2\2\16g\3\2\2\2\20q\3\2\2\2\22v\3\2\2\2"+
		"\24\u0080\3\2\2\2\26\u0082\3\2\2\2\30\u0089\3\2\2\2\32\u0091\3\2\2\2\34"+
		"\u0093\3\2\2\2\36\u009e\3\2\2\2 \u00a3\3\2\2\2\"\u00a7\3\2\2\2$\u00a9"+
		"\3\2\2\2&\u00ab\3\2\2\2(\u00b6\3\2\2\2*\u00b8\3\2\2\2,\u00ba\3\2\2\2."+
		"\u00c3\3\2\2\2\60\u00cc\3\2\2\2\629\5\4\3\2\639\5\b\5\2\649\5\16\b\2\65"+
		"9\5\22\n\2\669\5\n\6\2\679\5\f\7\28\62\3\2\2\28\63\3\2\2\28\64\3\2\2\2"+
		"8\65\3\2\2\28\66\3\2\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\3"+
		"\3\2\2\2<:\3\2\2\2=>\7\3\2\2>@\5$\23\2?A\5\6\4\2@?\3\2\2\2@A\3\2\2\2A"+
		"B\3\2\2\2BD\7\4\2\2CE\5\26\f\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2"+
		"GH\3\2\2\2HI\7\5\2\2I\5\3\2\2\2JL\7\6\2\2KM\5$\23\2LK\3\2\2\2MN\3\2\2"+
		"\2NL\3\2\2\2NO\3\2\2\2O\7\3\2\2\2PQ\7\7\2\2QR\5$\23\2RT\7\4\2\2SU\5\26"+
		"\f\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\7\5\2\2Y\t\3"+
		"\2\2\2Z[\7\b\2\2[\\\5$\23\2\\^\7\4\2\2]_\5\26\f\2^]\3\2\2\2_`\3\2\2\2"+
		"`^\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\5\2\2c\13\3\2\2\2de\7\t\2\2ef\5$\23"+
		"\2f\r\3\2\2\2gh\7\n\2\2hi\5$\23\2ij\7\13\2\2jk\5\20\t\2k\17\3\2\2\2lm"+
		"\5$\23\2mn\7\f\2\2np\3\2\2\2ol\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r"+
		"t\3\2\2\2sq\3\2\2\2tu\5$\23\2u\21\3\2\2\2vw\7\r\2\2wx\5$\23\2xz\7\4\2"+
		"\2y{\5\24\13\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177"+
		"\7\5\2\2\177\23\3\2\2\2\u0080\u0081\7\24\2\2\u0081\25\3\2\2\2\u0082\u0084"+
		"\5\32\16\2\u0083\u0085\5\30\r\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2"+
		"\2\u0085\u0086\3\2\2\2\u0086\u0087\7\16\2\2\u0087\u0088\5\36\20\2\u0088"+
		"\27\3\2\2\2\u0089\u008b\7\17\2\2\u008a\u008c\5\34\17\2\u008b\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0090\7\20\2\2\u0090\31\3\2\2\2\u0091\u0092\7\24"+
		"\2\2\u0092\33\3\2\2\2\u0093\u0094\7\24\2\2\u0094\u0095\7\16\2\2\u0095"+
		"\u0097\5\36\20\2\u0096\u0098\5\"\22\2\u0097\u0096\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u009b\5&\24\2\u009a\u0099\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\35\3\2\2\2\u009c\u009f\5$\23\2\u009d\u009f\5 \21"+
		"\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u00a2"+
		"\5\"\22\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\37\3\2\2\2\u00a3"+
		"\u00a4\7\21\2\2\u00a4\u00a5\5\36\20\2\u00a5\u00a6\7\22\2\2\u00a6!\3\2"+
		"\2\2\u00a7\u00a8\7\23\2\2\u00a8#\3\2\2\2\u00a9\u00aa\7\24\2\2\u00aa%\3"+
		"\2\2\2\u00ab\u00ac\7\13\2\2\u00ac\u00ad\5(\25\2\u00ad\'\3\2\2\2\u00ae"+
		"\u00b7\7\30\2\2\u00af\u00b7\7\31\2\2\u00b0\u00b7\7\37\2\2\u00b1\u00b7"+
		"\7\25\2\2\u00b2\u00b7\7\26\2\2\u00b3\u00b7\5*\26\2\u00b4\u00b7\5,\27\2"+
		"\u00b5\u00b7\5.\30\2\u00b6\u00ae\3\2\2\2\u00b6\u00af\3\2\2\2\u00b6\u00b0"+
		"\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7)\3\2\2\2\u00b8\u00b9\7\24\2\2"+
		"\u00b9+\3\2\2\2\u00ba\u00be\7\21\2\2\u00bb\u00bd\5(\25\2\u00bc\u00bb\3"+
		"\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\22\2\2\u00c2-\3\2\2\2"+
		"\u00c3\u00c7\7\4\2\2\u00c4\u00c6\5\60\31\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9"+
		"\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00cb\7\5\2\2\u00cb/\3\2\2\2\u00cc\u00cd\7\24\2\2"+
		"\u00cd\u00ce\7\16\2\2\u00ce\u00cf\5(\25\2\u00cf\61\3\2\2\2\248:@FNV`q"+
		"|\u0084\u008d\u0097\u009a\u009e\u00a1\u00b6\u00be\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}