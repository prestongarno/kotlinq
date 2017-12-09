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
		"LineTerminator", "Whitespace", "Comma"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00c5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\7\13T\n\13\f\13\16\13W\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\5\fb\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\5\17l\n\17\3\17\3"+
		"\17\3\20\5\20q\n\20\3\20\3\20\3\20\6\20v\n\20\r\20\16\20w\5\20z\n\20\3"+
		"\20\5\20}\n\20\3\21\3\21\3\22\3\22\3\22\3\22\6\22\u0085\n\22\r\22\16\22"+
		"\u0086\5\22\u0089\n\22\3\23\3\23\3\24\3\24\5\24\u008f\n\24\3\24\6\24\u0092"+
		"\n\24\r\24\16\24\u0093\3\25\3\25\3\26\3\26\3\26\7\26\u009b\n\26\f\26\16"+
		"\26\u009e\13\26\3\26\3\26\3\27\3\27\3\27\5\27\u00a5\n\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u00b5\n\33"+
		"\3\33\3\33\3\34\3\34\7\34\u00bb\n\34\f\34\16\34\u00be\13\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\2\61\2\63"+
		"\2\65\30\67\29\2;\2=\2\3\2\n\4\2C\\c|\6\2\62;C\\aac|\4\2GGgg\7\2\f\f\17"+
		"\17$$^^\u202a\u202b\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\f\f\17\17"+
		"\u202a\u202b\6\2\13\13\r\16\"\"\u00a2\u00a2\2\u00cf\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2\65\3\2\2\2\3?\3\2\2\2\5A\3\2\2\2\7C\3"+
		"\2\2\2\tE\3\2\2\2\13G\3\2\2\2\rI\3\2\2\2\17K\3\2\2\2\21M\3\2\2\2\23O\3"+
		"\2\2\2\25Q\3\2\2\2\27a\3\2\2\2\31c\3\2\2\2\33e\3\2\2\2\35k\3\2\2\2\37"+
		"p\3\2\2\2!~\3\2\2\2#\u0088\3\2\2\2%\u008a\3\2\2\2\'\u008c\3\2\2\2)\u0095"+
		"\3\2\2\2+\u0097\3\2\2\2-\u00a1\3\2\2\2/\u00a6\3\2\2\2\61\u00ac\3\2\2\2"+
		"\63\u00ae\3\2\2\2\65\u00b4\3\2\2\2\67\u00b8\3\2\2\29\u00bf\3\2\2\2;\u00c1"+
		"\3\2\2\2=\u00c3\3\2\2\2?@\7<\2\2@\4\3\2\2\2AB\7*\2\2B\6\3\2\2\2CD\7+\2"+
		"\2D\b\3\2\2\2EF\7]\2\2F\n\3\2\2\2GH\7_\2\2H\f\3\2\2\2IJ\7#\2\2J\16\3\2"+
		"\2\2KL\7?\2\2L\20\3\2\2\2MN\7}\2\2N\22\3\2\2\2OP\7\177\2\2P\24\3\2\2\2"+
		"QU\t\2\2\2RT\t\3\2\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\26\3\2\2"+
		"\2WU\3\2\2\2XY\7v\2\2YZ\7t\2\2Z[\7w\2\2[b\7g\2\2\\]\7h\2\2]^\7c\2\2^_"+
		"\7n\2\2_`\7u\2\2`b\7g\2\2aX\3\2\2\2a\\\3\2\2\2b\30\3\2\2\2cd\5\33\16\2"+
		"d\32\3\2\2\2ef\7p\2\2fg\7w\2\2gh\7n\2\2hi\7n\2\2i\34\3\2\2\2jl\5!\21\2"+
		"kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\5#\22\2n\36\3\2\2\2oq\5!\21\2po\3\2\2"+
		"\2pq\3\2\2\2qr\3\2\2\2ry\5#\22\2su\7\60\2\2tv\5)\25\2ut\3\2\2\2vw\3\2"+
		"\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2ys\3\2\2\2yz\3\2\2\2z|\3\2\2\2{}\5\'"+
		"\24\2|{\3\2\2\2|}\3\2\2\2} \3\2\2\2~\177\7/\2\2\177\"\3\2\2\2\u0080\u0089"+
		"\7\62\2\2\u0081\u0089\5%\23\2\u0082\u0084\5%\23\2\u0083\u0085\5)\25\2"+
		"\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2\2\2\u0088"+
		"\u0082\3\2\2\2\u0089$\3\2\2\2\u008a\u008b\4\63;\2\u008b&\3\2\2\2\u008c"+
		"\u008e\t\4\2\2\u008d\u008f\5!\21\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0091\3\2\2\2\u0090\u0092\5)\25\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094(\3\2\2\2"+
		"\u0095\u0096\4\62;\2\u0096*\3\2\2\2\u0097\u009c\5\61\31\2\u0098\u009b"+
		"\n\5\2\2\u0099\u009b\5-\27\2\u009a\u0098\3\2\2\2\u009a\u0099\3\2\2\2\u009b"+
		"\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2"+
		"\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\5\61\31\2\u00a0,\3\2\2\2\u00a1\u00a4"+
		"\7^\2\2\u00a2\u00a5\t\6\2\2\u00a3\u00a5\5/\30\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a3\3\2\2\2\u00a5.\3\2\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\5\63\32\2"+
		"\u00a8\u00a9\5\63\32\2\u00a9\u00aa\5\63\32\2\u00aa\u00ab\5\63\32\2\u00ab"+
		"\60\3\2\2\2\u00ac\u00ad\7$\2\2\u00ad\62\3\2\2\2\u00ae\u00af\t\7\2\2\u00af"+
		"\64\3\2\2\2\u00b0\u00b5\5;\36\2\u00b1\u00b5\5=\37\2\u00b2\u00b5\59\35"+
		"\2\u00b3\u00b5\5\67\34\2\u00b4\u00b0\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\33"+
		"\2\2\u00b7\66\3\2\2\2\u00b8\u00bc\7%\2\2\u00b9\u00bb\n\b\2\2\u00ba\u00b9"+
		"\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"8\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\t\b\2\2\u00c0:\3\2\2\2\u00c1"+
		"\u00c2\t\t\2\2\u00c2<\3\2\2\2\u00c3\u00c4\7.\2\2\u00c4>\3\2\2\2\23\2U"+
		"akpwy|\u0086\u0088\u008e\u0093\u009a\u009c\u00a4\u00b4\u00bc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}