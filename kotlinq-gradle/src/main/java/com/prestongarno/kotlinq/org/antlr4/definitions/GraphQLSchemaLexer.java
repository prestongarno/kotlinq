// Generated from /home/preston/IdeaProjects/kotlinq/kotlinq-gradle/src/main/resources/GraphQLSchema.g4 by ANTLR 4.7
package com.prestongarno.kotlinq.org.antlr4.definitions;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GraphQLSchemaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		Name=10, BooleanValue=11, NullValue=12, Null=13, IntValue=14, FloatValue=15, 
		Sign=16, IntegerPart=17, NonZeroDigit=18, ExponentPart=19, Digit=20, StringValue=21, 
		Ignored=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"Name", "BooleanValue", "NullValue", "Null", "IntValue", "FloatValue", 
		"Sign", "IntegerPart", "NonZeroDigit", "ExponentPart", "Digit", "StringValue", 
		"EscapedChar", "Unicode", "DoubleQuote", "Hex", "Ignored", "Comment", 
		"Directive", "LineTerminator", "Whitespace", "Comma"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'('", "')'", "'['", "']'", "'!'", "'='", "'{'", "'}'", null, 
		null, null, "'null'", null, null, "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "Name", "BooleanValue", 
		"NullValue", "Null", "IntValue", "FloatValue", "Sign", "IntegerPart", 
		"NonZeroDigit", "ExponentPart", "Digit", "StringValue", "Ignored"
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


	public GraphQLSchemaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GraphQLSchema.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00cf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\7\13V\n\13\f\13\16\13Y\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\fd\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\5\17n\n\17\3"+
		"\17\3\17\3\20\5\20s\n\20\3\20\3\20\3\20\6\20x\n\20\r\20\16\20y\5\20|\n"+
		"\20\3\20\5\20\177\n\20\3\21\3\21\3\22\3\22\3\22\3\22\6\22\u0087\n\22\r"+
		"\22\16\22\u0088\5\22\u008b\n\22\3\23\3\23\3\24\3\24\5\24\u0091\n\24\3"+
		"\24\6\24\u0094\n\24\r\24\16\24\u0095\3\25\3\25\3\26\3\26\3\26\7\26\u009d"+
		"\n\26\f\26\16\26\u00a0\13\26\3\26\3\26\3\27\3\27\3\27\5\27\u00a7\n\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\5\33\u00b8\n\33\3\33\3\33\3\34\3\34\7\34\u00be\n\34\f\34\16\34\u00c1"+
		"\13\34\3\35\3\35\7\35\u00c5\n\35\f\35\16\35\u00c8\13\35\3\36\3\36\3\37"+
		"\3\37\3 \3 \2\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\63\2\65\30\67"+
		"\29\2;\2=\2?\2\3\2\n\4\2C\\c|\6\2\62;C\\aac|\4\2GGgg\7\2\f\f\17\17$$^"+
		"^\u202a\u202b\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\f\f\17\17\u202a"+
		"\u202b\6\2\13\13\r\16\"\"\u00a2\u00a2\2\u00da\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2\65\3\2\2\2\3A\3\2\2\2\5C\3\2\2\2\7E\3\2\2"+
		"\2\tG\3\2\2\2\13I\3\2\2\2\rK\3\2\2\2\17M\3\2\2\2\21O\3\2\2\2\23Q\3\2\2"+
		"\2\25S\3\2\2\2\27c\3\2\2\2\31e\3\2\2\2\33g\3\2\2\2\35m\3\2\2\2\37r\3\2"+
		"\2\2!\u0080\3\2\2\2#\u008a\3\2\2\2%\u008c\3\2\2\2\'\u008e\3\2\2\2)\u0097"+
		"\3\2\2\2+\u0099\3\2\2\2-\u00a3\3\2\2\2/\u00a8\3\2\2\2\61\u00ae\3\2\2\2"+
		"\63\u00b0\3\2\2\2\65\u00b7\3\2\2\2\67\u00bb\3\2\2\29\u00c2\3\2\2\2;\u00c9"+
		"\3\2\2\2=\u00cb\3\2\2\2?\u00cd\3\2\2\2AB\7<\2\2B\4\3\2\2\2CD\7*\2\2D\6"+
		"\3\2\2\2EF\7+\2\2F\b\3\2\2\2GH\7]\2\2H\n\3\2\2\2IJ\7_\2\2J\f\3\2\2\2K"+
		"L\7#\2\2L\16\3\2\2\2MN\7?\2\2N\20\3\2\2\2OP\7}\2\2P\22\3\2\2\2QR\7\177"+
		"\2\2R\24\3\2\2\2SW\t\2\2\2TV\t\3\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3"+
		"\2\2\2X\26\3\2\2\2YW\3\2\2\2Z[\7v\2\2[\\\7t\2\2\\]\7w\2\2]d\7g\2\2^_\7"+
		"h\2\2_`\7c\2\2`a\7n\2\2ab\7u\2\2bd\7g\2\2cZ\3\2\2\2c^\3\2\2\2d\30\3\2"+
		"\2\2ef\5\33\16\2f\32\3\2\2\2gh\7p\2\2hi\7w\2\2ij\7n\2\2jk\7n\2\2k\34\3"+
		"\2\2\2ln\5!\21\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2op\5#\22\2p\36\3\2\2\2q"+
		"s\5!\21\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2t{\5#\22\2uw\7\60\2\2vx\5)\25\2"+
		"wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{u\3\2\2\2{|\3\2\2\2"+
		"|~\3\2\2\2}\177\5\'\24\2~}\3\2\2\2~\177\3\2\2\2\177 \3\2\2\2\u0080\u0081"+
		"\7/\2\2\u0081\"\3\2\2\2\u0082\u008b\7\62\2\2\u0083\u008b\5%\23\2\u0084"+
		"\u0086\5%\23\2\u0085\u0087\5)\25\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0082\3\2\2\2\u008a\u0083\3\2\2\2\u008a\u0084\3\2\2\2\u008b$\3\2\2\2"+
		"\u008c\u008d\4\63;\2\u008d&\3\2\2\2\u008e\u0090\t\4\2\2\u008f\u0091\5"+
		"!\21\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092"+
		"\u0094\5)\25\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096(\3\2\2\2\u0097\u0098\4\62;\2\u0098*\3\2"+
		"\2\2\u0099\u009e\5\61\31\2\u009a\u009d\n\5\2\2\u009b\u009d\5-\27\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a2\5\61\31\2\u00a2,\3\2\2\2\u00a3\u00a6\7^\2\2\u00a4\u00a7\t\6\2\2"+
		"\u00a5\u00a7\5/\30\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7.\3"+
		"\2\2\2\u00a8\u00a9\7w\2\2\u00a9\u00aa\5\63\32\2\u00aa\u00ab\5\63\32\2"+
		"\u00ab\u00ac\5\63\32\2\u00ac\u00ad\5\63\32\2\u00ad\60\3\2\2\2\u00ae\u00af"+
		"\7$\2\2\u00af\62\3\2\2\2\u00b0\u00b1\t\7\2\2\u00b1\64\3\2\2\2\u00b2\u00b8"+
		"\5=\37\2\u00b3\u00b8\5? \2\u00b4\u00b8\5;\36\2\u00b5\u00b8\5\67\34\2\u00b6"+
		"\u00b8\59\35\2\u00b7\u00b2\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b7\u00b4\3\2"+
		"\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\b\33\2\2\u00ba\66\3\2\2\2\u00bb\u00bf\7%\2\2\u00bc\u00be\n\b\2"+
		"\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c08\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c6\7B\2\2\u00c3\u00c5"+
		"\n\b\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7:\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\t\b\2\2"+
		"\u00ca<\3\2\2\2\u00cb\u00cc\t\t\2\2\u00cc>\3\2\2\2\u00cd\u00ce\7.\2\2"+
		"\u00ce@\3\2\2\2\24\2Wcmry{~\u0088\u008a\u0090\u0095\u009c\u009e\u00a6"+
		"\u00b7\u00bf\u00c6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}