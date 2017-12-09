// Generated from /home/preston/IdeaProjects/kotlinq/kotlinq-gradle/src/main/resources/GraphQLBaseSchema.g4 by ANTLR 4.7
package com.prestongarno.kotlinq.core.org.antlr4.base;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GraphQLBaseSchema extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LCURLY=1, WS=2, NAME=3, TYPE_DEC=4, COMMENT_ENTER=5, COLON=6, WORD=7, 
		BLOCK=8, NEWLINE=9, TYPE_LIT=10, RCURLY=11, OTHER=12, UNION_WS=13, EQ=14, 
		WS_UNION=15, WS_SCALAR=16, WS_TYPE=17, IMPL_=18, EXIT_TYPE=19, WS_TYPE_CTX=20, 
		COMMA=21, BREAK=22, STR=23;
	public static final int
		CODE_0=1, TYPE_SCOPE=2, UNION=3, UNION_CONTEXT=4, DEF_NAME_CTX=5, TYPE=6, 
		TYPE_IMPL_CONTEXT=7, COMMENT=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "CODE_0", "TYPE_SCOPE", "UNION", "UNION_CONTEXT", "DEF_NAME_CTX", 
		"TYPE", "TYPE_IMPL_CONTEXT", "COMMENT"
	};

	public static final String[] ruleNames = {
		"UNION_DEF", "SCALAR_DEF", "TYPE_DEF", "INTERFACE_DEF", "ENUM_DEF", "INPUT_DEF", 
		"LCURLY", "WS", "NAME", "TYPE_DEC", "COMMENT_ENTER", "COLON", "WORD", 
		"BLOCK", "NEWLINE", "TYPE_LIT", "CODE_0_LCURLY", "RCURLY", "CODE_0_OTHER", 
		"CODE_N_LCURLY", "CODE_N_RCURLY", "OTHER", "UNION_NAME", "UNION_WS", "EQ", 
		"WS_UNION", "BODY", "WS_SCALAR", "SCALAR_NAME", "WS_TYPE", "IMPL_", "TYPE_NAME", 
		"EXIT_TYPE", "WS_TYPE_CTX", "COMMA", "SUPERTYPE_NAME", "EXIT_IMPL", "BREAK", 
		"BRACKET_INCLUDE", "BRACKET_INCLUDE_CLOSE", "STR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'#'", "':'", null, null, null, null, null, 
		null, null, null, null, null, null, "'implements'", null, null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LCURLY", "WS", "NAME", "TYPE_DEC", "COMMENT_ENTER", "COLON", "WORD", 
		"BLOCK", "NEWLINE", "TYPE_LIT", "RCURLY", "OTHER", "UNION_WS", "EQ", "WS_UNION", 
		"WS_SCALAR", "WS_TYPE", "IMPL_", "EXIT_TYPE", "WS_TYPE_CTX", "COMMA", 
		"BREAK", "STR"
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


	public GraphQLBaseSchema(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GraphQLBaseSchema.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u0151\b\1\b\1"+
		"\b\1\b\1\b\1\b\1\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4"+
		"\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17"+
		"\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26"+
		"\t\26\4\27\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35"+
		"\t\35\4\36\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&"+
		"\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\7\n\u00a3\n\n\f\n\16\n\u00a6\13\n\3\13\3\13\7\13"+
		"\u00aa\n\13\f\13\16\13\u00ad\13\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\6"+
		"\16\u00b7\n\16\r\16\16\16\u00b8\3\17\6\17\u00bc\n\17\r\17\16\17\u00bd"+
		"\3\20\5\20\u00c1\n\20\3\20\3\20\5\20\u00c5\n\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\6\24\u00d5\n\24\r\24"+
		"\16\24\u00d6\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\6\27\u00e7\n\27\r\27\16\27\u00e8\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\7\34\u0100\n\34\f\34\16\34\u0103\13\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'"+
		"\3(\3(\3(\3(\3)\3)\3)\3)\3*\6*\u014c\n*\r*\16*\u014d\3*\3*\3\u00bd\2+"+
		"\13\2\r\2\17\2\21\2\23\2\25\2\27\3\31\4\33\5\35\6\37\7!\b#\t%\n\'\13)"+
		"\f+\2-\r/\2\61\2\63\2\65\16\67\29\17;\20=\21?\2A\22C\2E\23G\24I\2K\25"+
		"M\26O\27Q\2S\2U\30W\2Y\2[\31\13\2\3\4\5\6\7\b\t\n\b\5\2\13\f\17\17\"\""+
		"\4\2C\\c|\5\2\62;C\\c|\3\2c|\4\2}}\177\177\4\2\f\f\17\17\2\u0152\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\3-\3"+
		"\2\2\2\3/\3\2\2\2\4\61\3\2\2\2\4\63\3\2\2\2\4\65\3\2\2\2\5\67\3\2\2\2"+
		"\59\3\2\2\2\5;\3\2\2\2\6=\3\2\2\2\6?\3\2\2\2\7A\3\2\2\2\7C\3\2\2\2\bE"+
		"\3\2\2\2\bG\3\2\2\2\bI\3\2\2\2\bK\3\2\2\2\tM\3\2\2\2\tO\3\2\2\2\tQ\3\2"+
		"\2\2\tS\3\2\2\2\nU\3\2\2\2\nW\3\2\2\2\nY\3\2\2\2\n[\3\2\2\2\13]\3\2\2"+
		"\2\rf\3\2\2\2\17p\3\2\2\2\21x\3\2\2\2\23\u0085\3\2\2\2\25\u008f\3\2\2"+
		"\2\27\u0098\3\2\2\2\31\u009c\3\2\2\2\33\u00a0\3\2\2\2\35\u00a7\3\2\2\2"+
		"\37\u00ae\3\2\2\2!\u00b3\3\2\2\2#\u00b6\3\2\2\2%\u00bb\3\2\2\2\'\u00c4"+
		"\3\2\2\2)\u00c8\3\2\2\2+\u00ca\3\2\2\2-\u00cf\3\2\2\2/\u00d4\3\2\2\2\61"+
		"\u00da\3\2\2\2\63\u00df\3\2\2\2\65\u00e6\3\2\2\2\67\u00ea\3\2\2\29\u00ee"+
		"\3\2\2\2;\u00f2\3\2\2\2=\u00f8\3\2\2\2?\u00fc\3\2\2\2A\u0107\3\2\2\2C"+
		"\u010b\3\2\2\2E\u0110\3\2\2\2G\u0114\3\2\2\2I\u0122\3\2\2\2K\u0126\3\2"+
		"\2\2M\u012b\3\2\2\2O\u012f\3\2\2\2Q\u0133\3\2\2\2S\u0137\3\2\2\2U\u013d"+
		"\3\2\2\2W\u0142\3\2\2\2Y\u0146\3\2\2\2[\u014b\3\2\2\2]^\7w\2\2^_\7p\2"+
		"\2_`\7k\2\2`a\7q\2\2ab\7p\2\2bc\3\2\2\2cd\b\2\2\2de\b\2\3\2e\f\3\2\2\2"+
		"fg\7u\2\2gh\7e\2\2hi\7c\2\2ij\7n\2\2jk\7c\2\2kl\7t\2\2lm\3\2\2\2mn\b\3"+
		"\2\2no\b\3\4\2o\16\3\2\2\2pq\7v\2\2qr\7{\2\2rs\7r\2\2st\7g\2\2tu\3\2\2"+
		"\2uv\b\4\2\2vw\b\4\5\2w\20\3\2\2\2xy\7k\2\2yz\7p\2\2z{\7v\2\2{|\7g\2\2"+
		"|}\7t\2\2}~\7h\2\2~\177\7c\2\2\177\u0080\7e\2\2\u0080\u0081\7g\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0083\b\5\2\2\u0083\u0084\b\5\4\2\u0084\22\3\2\2"+
		"\2\u0085\u0086\7g\2\2\u0086\u0087\7p\2\2\u0087\u0088\7w\2\2\u0088\u0089"+
		"\7o\2\2\u0089\u008a\3\2\2\2\u008a\u008b\5\33\n\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008d\b\6\2\2\u008d\u008e\b\6\4\2\u008e\24\3\2\2\2\u008f\u0090\7k\2\2"+
		"\u0090\u0091\7p\2\2\u0091\u0092\7r\2\2\u0092\u0093\7w\2\2\u0093\u0094"+
		"\7v\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\7\2\2\u0096\u0097\b\7\4\2\u0097"+
		"\26\3\2\2\2\u0098\u0099\7}\2\2\u0099\u009a\3\2\2\2\u009a\u009b\b\b\6\2"+
		"\u009b\30\3\2\2\2\u009c\u009d\t\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f"+
		"\b\t\7\2\u009f\32\3\2\2\2\u00a0\u00a4\t\3\2\2\u00a1\u00a3\t\4\2\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\34\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00ab\t\3\2\2\u00a8\u00aa"+
		"\t\4\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\36\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7%\2\2"+
		"\u00af\u00b0\3\2\2\2\u00b0\u00b1\b\f\7\2\u00b1\u00b2\b\f\b\2\u00b2 \3"+
		"\2\2\2\u00b3\u00b4\7<\2\2\u00b4\"\3\2\2\2\u00b5\u00b7\t\3\2\2\u00b6\u00b5"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"$\3\2\2\2\u00ba\u00bc\13\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2"+
		"\u00bd\u00be\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be&\3\2\2\2\u00bf\u00c1\7"+
		"\17\2\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c5\7\f\2\2\u00c3\u00c5\7\17\2\2\u00c4\u00c0\3\2\2\2\u00c4\u00c3\3"+
		"\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\20\7\2\u00c7(\3\2\2\2\u00c8\u00c9"+
		"\t\5\2\2\u00c9*\3\2\2\2\u00ca\u00cb\7}\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd"+
		"\b\22\t\2\u00cd\u00ce\b\22\n\2\u00ce,\3\2\2\2\u00cf\u00d0\7\177\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\b\23\13\2\u00d2.\3\2\2\2\u00d3\u00d5\n\6\2"+
		"\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\24\t\2\u00d9\60\3\2\2\2\u00da"+
		"\u00db\7}\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b\25\t\2\u00dd\u00de\b\25"+
		"\6\2\u00de\62\3\2\2\2\u00df\u00e0\7\177\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\b\26\t\2\u00e2\u00e3\b\26\13\2\u00e3\u00e4\b\26\f\2\u00e4\64\3"+
		"\2\2\2\u00e5\u00e7\n\6\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\66\3\2\2\2\u00ea\u00eb\5\33\n"+
		"\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\b\30\r\2\u00ed8\3\2\2\2\u00ee\u00ef"+
		"\t\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\b\31\7\2\u00f1:\3\2\2\2\u00f2"+
		"\u00f3\7?\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\b\32\7\2\u00f5\u00f6\b\32"+
		"\13\2\u00f6\u00f7\b\32\16\2\u00f7<\3\2\2\2\u00f8\u00f9\t\2\2\2\u00f9\u00fa"+
		"\3\2\2\2\u00fa\u00fb\b\33\7\2\u00fb>\3\2\2\2\u00fc\u0101\5\33\n\2\u00fd"+
		"\u00fe\7~\2\2\u00fe\u0100\5\33\n\2\u00ff\u00fd\3\2\2\2\u0100\u0103\3\2"+
		"\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0104\u0105\b\34\17\2\u0105\u0106\b\34\13\2\u0106@\3\2"+
		"\2\2\u0107\u0108\5\31\t\2\u0108\u0109\3\2\2\2\u0109\u010a\b\35\7\2\u010a"+
		"B\3\2\2\2\u010b\u010c\5\33\n\2\u010c\u010d\3\2\2\2\u010d\u010e\b\36\r"+
		"\2\u010e\u010f\b\36\13\2\u010fD\3\2\2\2\u0110\u0111\5\31\t\2\u0111\u0112"+
		"\3\2\2\2\u0112\u0113\b\37\7\2\u0113F\3\2\2\2\u0114\u0115\7k\2\2\u0115"+
		"\u0116\7o\2\2\u0116\u0117\7r\2\2\u0117\u0118\7n\2\2\u0118\u0119\7g\2\2"+
		"\u0119\u011a\7o\2\2\u011a\u011b\7g\2\2\u011b\u011c\7p\2\2\u011c\u011d"+
		"\7v\2\2\u011d\u011e\7u\2\2\u011e\u011f\3\2\2\2\u011f\u0120\b \7\2\u0120"+
		"\u0121\b \20\2\u0121H\3\2\2\2\u0122\u0123\5\33\n\2\u0123\u0124\3\2\2\2"+
		"\u0124\u0125\b!\r\2\u0125J\3\2\2\2\u0126\u0127\5\27\b\2\u0127\u0128\3"+
		"\2\2\2\u0128\u0129\b\"\13\2\u0129\u012a\b\"\n\2\u012aL\3\2\2\2\u012b\u012c"+
		"\5\31\t\2\u012c\u012d\3\2\2\2\u012d\u012e\b#\7\2\u012eN\3\2\2\2\u012f"+
		"\u0130\7.\2\2\u0130\u0131\3\2\2\2\u0131\u0132\b$\7\2\u0132P\3\2\2\2\u0133"+
		"\u0134\5\33\n\2\u0134\u0135\3\2\2\2\u0135\u0136\b%\21\2\u0136R\3\2\2\2"+
		"\u0137\u0138\5\27\b\2\u0138\u0139\3\2\2\2\u0139\u013a\b&\22\2\u013a\u013b"+
		"\b&\13\2\u013b\u013c\b&\n\2\u013cT\3\2\2\2\u013d\u013e\t\7\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u0140\b\'\7\2\u0140\u0141\b\'\13\2\u0141V\3\2\2\2\u0142"+
		"\u0143\7}\2\2\u0143\u0144\3\2\2\2\u0144\u0145\b(\23\2\u0145X\3\2\2\2\u0146"+
		"\u0147\7\177\2\2\u0147\u0148\3\2\2\2\u0148\u0149\b)\23\2\u0149Z\3\2\2"+
		"\2\u014a\u014c\n\7\2\2\u014b\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014b"+
		"\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\b*\7\2\u0150"+
		"\\\3\2\2\2\25\2\3\4\5\6\7\b\t\n\u00a4\u00ab\u00b8\u00bd\u00c0\u00c4\u00d6"+
		"\u00e8\u0101\u014d\24\t\f\2\7\5\2\7\7\2\7\b\2\7\3\2\b\2\2\7\n\2\t\16\2"+
		"\7\4\2\6\2\2\7\2\2\t\6\2\7\6\2\t\n\2\7\t\2\t\5\2\t\3\2\t\31\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}