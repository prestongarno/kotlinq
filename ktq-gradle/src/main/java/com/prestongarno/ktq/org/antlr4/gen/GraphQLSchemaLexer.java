// Generated from /home/preston/IdeaProjects/ktq-gradle/src/main/resources/GraphQLSchema.g4 by ANTLR 4.7
package com.prestongarno.ktq.org.antlr4.gen;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		Name=18, BooleanValue=19, NullValue=20, Null=21, IntValue=22, FloatValue=23, 
		Sign=24, IntegerPart=25, NonZeroDigit=26, ExponentPart=27, Digit=28, StringValue=29, 
		Ignored=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"Name", "BooleanValue", "NullValue", "Null", "IntValue", "FloatValue", 
		"Sign", "IntegerPart", "NonZeroDigit", "ExponentPart", "Digit", "StringValue", 
		"EscapedChar", "Unicode", "DoubleQuote", "Hex", "Ignored", "Comment", 
		"LineTerminator", "Whitespace", "Comma"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\7\23"+
		"\u0098\n\23\f\23\16\23\u009b\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u00a6\n\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\5\27"+
		"\u00b0\n\27\3\27\3\27\3\30\5\30\u00b5\n\30\3\30\3\30\3\30\6\30\u00ba\n"+
		"\30\r\30\16\30\u00bb\5\30\u00be\n\30\3\30\5\30\u00c1\n\30\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\6\32\u00c9\n\32\r\32\16\32\u00ca\5\32\u00cd\n\32\3"+
		"\33\3\33\3\34\3\34\5\34\u00d3\n\34\3\34\6\34\u00d6\n\34\r\34\16\34\u00d7"+
		"\3\35\3\35\3\36\3\36\3\36\7\36\u00df\n\36\f\36\16\36\u00e2\13\36\3\36"+
		"\3\36\3\37\3\37\3\37\5\37\u00e9\n\37\3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3#\3#\5#\u00f9\n#\3#\3#\3$\3$\7$\u00ff\n$\f$\16$\u0102\13$\3%\3%"+
		"\3&\3&\3\'\3\'\2\2(\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37=\2?\2A\2C\2E G\2I\2K\2M\2\3\2\n\4\2C\\c|\6\2\62;"+
		"C\\aac|\4\2GGgg\7\2\f\f\17\17$$^^\u202a\u202b\n\2$$\61\61^^ddhhppttvv"+
		"\5\2\62;CHch\5\2\f\f\17\17\u202a\u202b\6\2\13\13\r\16\"\"\u00a2\u00a2"+
		"\2\u0113\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2E\3\2\2\2\3O\3\2\2\2\5T\3\2\2\2\7V\3\2\2\2\tX\3\2\2\2"+
		"\13c\3\2\2\2\ri\3\2\2\2\17s\3\2\2\2\21z\3\2\2\2\23\u0080\3\2\2\2\25\u0082"+
		"\3\2\2\2\27\u0084\3\2\2\2\31\u0089\3\2\2\2\33\u008b\3\2\2\2\35\u008d\3"+
		"\2\2\2\37\u008f\3\2\2\2!\u0091\3\2\2\2#\u0093\3\2\2\2%\u0095\3\2\2\2\'"+
		"\u00a5\3\2\2\2)\u00a7\3\2\2\2+\u00a9\3\2\2\2-\u00af\3\2\2\2/\u00b4\3\2"+
		"\2\2\61\u00c2\3\2\2\2\63\u00cc\3\2\2\2\65\u00ce\3\2\2\2\67\u00d0\3\2\2"+
		"\29\u00d9\3\2\2\2;\u00db\3\2\2\2=\u00e5\3\2\2\2?\u00ea\3\2\2\2A\u00f0"+
		"\3\2\2\2C\u00f2\3\2\2\2E\u00f8\3\2\2\2G\u00fc\3\2\2\2I\u0103\3\2\2\2K"+
		"\u0105\3\2\2\2M\u0107\3\2\2\2OP\7v\2\2PQ\7{\2\2QR\7r\2\2RS\7g\2\2S\4\3"+
		"\2\2\2TU\7}\2\2U\6\3\2\2\2VW\7\177\2\2W\b\3\2\2\2XY\7k\2\2YZ\7o\2\2Z["+
		"\7r\2\2[\\\7n\2\2\\]\7g\2\2]^\7o\2\2^_\7g\2\2_`\7p\2\2`a\7v\2\2ab\7u\2"+
		"\2b\n\3\2\2\2cd\7k\2\2de\7p\2\2ef\7r\2\2fg\7w\2\2gh\7v\2\2h\f\3\2\2\2"+
		"ij\7k\2\2jk\7p\2\2kl\7v\2\2lm\7g\2\2mn\7t\2\2no\7h\2\2op\7c\2\2pq\7e\2"+
		"\2qr\7g\2\2r\16\3\2\2\2st\7u\2\2tu\7e\2\2uv\7c\2\2vw\7n\2\2wx\7c\2\2x"+
		"y\7t\2\2y\20\3\2\2\2z{\7w\2\2{|\7p\2\2|}\7k\2\2}~\7q\2\2~\177\7p\2\2\177"+
		"\22\3\2\2\2\u0080\u0081\7?\2\2\u0081\24\3\2\2\2\u0082\u0083\7~\2\2\u0083"+
		"\26\3\2\2\2\u0084\u0085\7g\2\2\u0085\u0086\7p\2\2\u0086\u0087\7w\2\2\u0087"+
		"\u0088\7o\2\2\u0088\30\3\2\2\2\u0089\u008a\7<\2\2\u008a\32\3\2\2\2\u008b"+
		"\u008c\7*\2\2\u008c\34\3\2\2\2\u008d\u008e\7+\2\2\u008e\36\3\2\2\2\u008f"+
		"\u0090\7]\2\2\u0090 \3\2\2\2\u0091\u0092\7_\2\2\u0092\"\3\2\2\2\u0093"+
		"\u0094\7#\2\2\u0094$\3\2\2\2\u0095\u0099\t\2\2\2\u0096\u0098\t\3\2\2\u0097"+
		"\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a&\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7v\2\2\u009d\u009e"+
		"\7t\2\2\u009e\u009f\7w\2\2\u009f\u00a6\7g\2\2\u00a0\u00a1\7h\2\2\u00a1"+
		"\u00a2\7c\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a6\7g\2\2"+
		"\u00a5\u009c\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a6(\3\2\2\2\u00a7\u00a8\5"+
		"+\26\2\u00a8*\3\2\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7w\2\2\u00ab\u00ac"+
		"\7n\2\2\u00ac\u00ad\7n\2\2\u00ad,\3\2\2\2\u00ae\u00b0\5\61\31\2\u00af"+
		"\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\63"+
		"\32\2\u00b2.\3\2\2\2\u00b3\u00b5\5\61\31\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00bd\5\63\32\2\u00b7\u00b9\7\60\2"+
		"\2\u00b8\u00ba\59\35\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00c1\5\67\34\2\u00c0\u00bf\3"+
		"\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\60\3\2\2\2\u00c2\u00c3\7/\2\2\u00c3\62"+
		"\3\2\2\2\u00c4\u00cd\7\62\2\2\u00c5\u00cd\5\65\33\2\u00c6\u00c8\5\65\33"+
		"\2\u00c7\u00c9\59\35\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cc"+
		"\u00c5\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cd\64\3\2\2\2\u00ce\u00cf\4\63;"+
		"\2\u00cf\66\3\2\2\2\u00d0\u00d2\t\4\2\2\u00d1\u00d3\5\61\31\2\u00d2\u00d1"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d6\59\35\2\u00d5"+
		"\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d88\3\2\2\2\u00d9\u00da\4\62;\2\u00da:\3\2\2\2\u00db\u00e0\5A"+
		"!\2\u00dc\u00df\n\5\2\2\u00dd\u00df\5=\37\2\u00de\u00dc\3\2\2\2\u00de"+
		"\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2"+
		"\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\5A!\2\u00e4<"+
		"\3\2\2\2\u00e5\u00e8\7^\2\2\u00e6\u00e9\t\6\2\2\u00e7\u00e9\5? \2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9>\3\2\2\2\u00ea\u00eb\7w\2\2\u00eb"+
		"\u00ec\5C\"\2\u00ec\u00ed\5C\"\2\u00ed\u00ee\5C\"\2\u00ee\u00ef\5C\"\2"+
		"\u00ef@\3\2\2\2\u00f0\u00f1\7$\2\2\u00f1B\3\2\2\2\u00f2\u00f3\t\7\2\2"+
		"\u00f3D\3\2\2\2\u00f4\u00f9\5K&\2\u00f5\u00f9\5M\'\2\u00f6\u00f9\5I%\2"+
		"\u00f7\u00f9\5G$\2\u00f8\u00f4\3\2\2\2\u00f8\u00f5\3\2\2\2\u00f8\u00f6"+
		"\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b#\2\2\u00fb"+
		"F\3\2\2\2\u00fc\u0100\7%\2\2\u00fd\u00ff\n\b\2\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101H\3\2\2\2"+
		"\u0102\u0100\3\2\2\2\u0103\u0104\t\b\2\2\u0104J\3\2\2\2\u0105\u0106\t"+
		"\t\2\2\u0106L\3\2\2\2\u0107\u0108\7.\2\2\u0108N\3\2\2\2\23\2\u0099\u00a5"+
		"\u00af\u00b4\u00bb\u00bd\u00c0\u00ca\u00cc\u00d2\u00d7\u00de\u00e0\u00e8"+
		"\u00f8\u0100\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}