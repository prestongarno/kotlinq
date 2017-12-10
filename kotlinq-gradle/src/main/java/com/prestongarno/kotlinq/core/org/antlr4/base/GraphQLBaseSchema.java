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
		WS_UNION=15, OR=16, WS_SCALAR=17, WS_TYPE=18, IMPL_=19, WS_TYPE_CTX=20, 
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
		"WS_UNION", "OR", "BODY", "WS_SCALAR", "SCALAR_NAME", "WS_TYPE", "IMPL_", 
		"TYPE_NAME", "EXIT_TYPE", "WS_TYPE_CTX", "COMMA", "SUPERTYPE_NAME", "EXIT_IMPL", 
		"BREAK", "BRACKET_INCLUDE", "BRACKET_INCLUDE_CLOSE", "STR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'#'", "':'", null, null, null, null, null, 
		null, null, null, null, null, null, null, "'implements'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LCURLY", "WS", "NAME", "TYPE_DEC", "COMMENT_ENTER", "COLON", "WORD", 
		"BLOCK", "NEWLINE", "TYPE_LIT", "RCURLY", "OTHER", "UNION_WS", "EQ", "WS_UNION", 
		"OR", "WS_SCALAR", "WS_TYPE", "IMPL_", "WS_TYPE_CTX", "COMMA", "BREAK", 
		"STR"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u0161\b\1\b\1"+
		"\b\1\b\1\b\1\b\1\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4"+
		"\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17"+
		"\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26"+
		"\t\26\4\27\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35"+
		"\t\35\4\36\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&"+
		"\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\7\n\u00a3\n\n\f\n\16\n\u00a6\13\n\3\13\3\13\7\13"+
		"\u00aa\n\13\f\13\16\13\u00ad\13\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\6"+
		"\16\u00b7\n\16\r\16\16\16\u00b8\3\17\6\17\u00bc\n\17\r\17\16\17\u00bd"+
		"\3\20\5\20\u00c1\n\20\3\20\3\20\5\20\u00c5\n\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\6\24\u00d5\n\24\r\24"+
		"\16\24\u00d6\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\6\27\u00e7\n\27\r\27\16\27\u00e8\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34"+
		"\7\34\u00fe\n\34\f\34\16\34\u0101\13\34\3\34\3\34\7\34\u0105\n\34\f\34"+
		"\16\34\u0108\13\34\3\34\3\34\3\35\3\35\3\35\3\35\6\35\u0110\n\35\r\35"+
		"\16\35\u0111\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3+\6+\u015c\n+\r+"+
		"\16+\u015d\3+\3+\5\u00bd\u00ff\u0106\2,\13\2\r\2\17\2\21\2\23\2\25\2\27"+
		"\3\31\4\33\5\35\6\37\7!\b#\t%\n\'\13)\f+\2-\r/\2\61\2\63\2\65\16\67\2"+
		"9\17;\20=\21?\22A\2C\23E\2G\24I\25K\2M\2O\26Q\27S\2U\2W\30Y\2[\2]\31\13"+
		"\2\3\4\5\6\7\b\t\n\b\5\2\13\f\17\17\"\"\4\2C\\c|\5\2\62;C\\c|\3\2c|\4"+
		"\2}}\177\177\4\2\f\f\17\17\2\u0164\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\3-\3\2\2\2\3/\3\2\2\2\4\61\3\2\2\2"+
		"\4\63\3\2\2\2\4\65\3\2\2\2\5\67\3\2\2\2\59\3\2\2\2\5;\3\2\2\2\6=\3\2\2"+
		"\2\6?\3\2\2\2\6A\3\2\2\2\7C\3\2\2\2\7E\3\2\2\2\bG\3\2\2\2\bI\3\2\2\2\b"+
		"K\3\2\2\2\bM\3\2\2\2\tO\3\2\2\2\tQ\3\2\2\2\tS\3\2\2\2\tU\3\2\2\2\nW\3"+
		"\2\2\2\nY\3\2\2\2\n[\3\2\2\2\n]\3\2\2\2\13_\3\2\2\2\rh\3\2\2\2\17r\3\2"+
		"\2\2\21z\3\2\2\2\23\u0087\3\2\2\2\25\u008f\3\2\2\2\27\u0098\3\2\2\2\31"+
		"\u009c\3\2\2\2\33\u00a0\3\2\2\2\35\u00a7\3\2\2\2\37\u00ae\3\2\2\2!\u00b3"+
		"\3\2\2\2#\u00b6\3\2\2\2%\u00bb\3\2\2\2\'\u00c4\3\2\2\2)\u00c8\3\2\2\2"+
		"+\u00ca\3\2\2\2-\u00cf\3\2\2\2/\u00d4\3\2\2\2\61\u00da\3\2\2\2\63\u00df"+
		"\3\2\2\2\65\u00e6\3\2\2\2\67\u00ea\3\2\2\29\u00ee\3\2\2\2;\u00f2\3\2\2"+
		"\2=\u00f8\3\2\2\2?\u00ff\3\2\2\2A\u010b\3\2\2\2C\u0116\3\2\2\2E\u011a"+
		"\3\2\2\2G\u011f\3\2\2\2I\u0123\3\2\2\2K\u0131\3\2\2\2M\u0135\3\2\2\2O"+
		"\u013b\3\2\2\2Q\u013f\3\2\2\2S\u0143\3\2\2\2U\u0147\3\2\2\2W\u014d\3\2"+
		"\2\2Y\u0152\3\2\2\2[\u0156\3\2\2\2]\u015b\3\2\2\2_`\7w\2\2`a\7p\2\2ab"+
		"\7k\2\2bc\7q\2\2cd\7p\2\2de\3\2\2\2ef\b\2\2\2fg\b\2\3\2g\f\3\2\2\2hi\7"+
		"u\2\2ij\7e\2\2jk\7c\2\2kl\7n\2\2lm\7c\2\2mn\7t\2\2no\3\2\2\2op\b\3\2\2"+
		"pq\b\3\4\2q\16\3\2\2\2rs\7v\2\2st\7{\2\2tu\7r\2\2uv\7g\2\2vw\3\2\2\2w"+
		"x\b\4\2\2xy\b\4\5\2y\20\3\2\2\2z{\7k\2\2{|\7p\2\2|}\7v\2\2}~\7g\2\2~\177"+
		"\7t\2\2\177\u0080\7h\2\2\u0080\u0081\7c\2\2\u0081\u0082\7e\2\2\u0082\u0083"+
		"\7g\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\5\2\2\u0085\u0086\b\5\4\2\u0086"+
		"\22\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7p\2\2\u0089\u008a\7w\2\2\u008a"+
		"\u008b\7o\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\6\2\2\u008d\u008e\b\6"+
		"\4\2\u008e\24\3\2\2\2\u008f\u0090\7k\2\2\u0090\u0091\7p\2\2\u0091\u0092"+
		"\7r\2\2\u0092\u0093\7w\2\2\u0093\u0094\7v\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0096\b\7\2\2\u0096\u0097\b\7\4\2\u0097\26\3\2\2\2\u0098\u0099\7}\2\2"+
		"\u0099\u009a\3\2\2\2\u009a\u009b\b\b\6\2\u009b\30\3\2\2\2\u009c\u009d"+
		"\t\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\t\7\2\u009f\32\3\2\2\2\u00a0"+
		"\u00a4\t\3\2\2\u00a1\u00a3\t\4\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2"+
		"\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\34\3\2\2\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00ab\t\3\2\2\u00a8\u00aa\t\4\2\2\u00a9\u00a8\3\2\2\2\u00aa"+
		"\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\36\3\2\2"+
		"\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7%\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1"+
		"\b\f\7\2\u00b1\u00b2\b\f\b\2\u00b2 \3\2\2\2\u00b3\u00b4\7<\2\2\u00b4\""+
		"\3\2\2\2\u00b5\u00b7\t\3\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9$\3\2\2\2\u00ba\u00bc\13\2\2\2"+
		"\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00bd\u00bb"+
		"\3\2\2\2\u00be&\3\2\2\2\u00bf\u00c1\7\17\2\2\u00c0\u00bf\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c5\7\f\2\2\u00c3\u00c5\7\17"+
		"\2\2\u00c4\u00c0\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\b\20\7\2\u00c7(\3\2\2\2\u00c8\u00c9\t\5\2\2\u00c9*\3\2\2\2\u00ca"+
		"\u00cb\7}\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\22\t\2\u00cd\u00ce\b\22"+
		"\n\2\u00ce,\3\2\2\2\u00cf\u00d0\7\177\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2"+
		"\b\23\13\2\u00d2.\3\2\2\2\u00d3\u00d5\n\6\2\2\u00d4\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d9\b\24\t\2\u00d9\60\3\2\2\2\u00da\u00db\7}\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\u00dd\b\25\t\2\u00dd\u00de\b\25\6\2\u00de\62\3\2\2\2\u00df"+
		"\u00e0\7\177\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\b\26\f\2\u00e2\u00e3"+
		"\b\26\13\2\u00e3\u00e4\b\26\r\2\u00e4\64\3\2\2\2\u00e5\u00e7\n\6\2\2\u00e6"+
		"\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2"+
		"\2\2\u00e9\66\3\2\2\2\u00ea\u00eb\5\33\n\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed"+
		"\b\30\16\2\u00ed8\3\2\2\2\u00ee\u00ef\t\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f1\b\31\7\2\u00f1:\3\2\2\2\u00f2\u00f3\7?\2\2\u00f3\u00f4\3\2\2\2"+
		"\u00f4\u00f5\b\32\7\2\u00f5\u00f6\b\32\13\2\u00f6\u00f7\b\32\17\2\u00f7"+
		"<\3\2\2\2\u00f8\u00f9\t\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b\33\7\2"+
		"\u00fb>\3\2\2\2\u00fc\u00fe\5\31\t\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3"+
		"\2\2\2\u00ff\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0102\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0106\7~\2\2\u0103\u0105\5\31\t\2\u0104\u0103\3\2"+
		"\2\2\u0105\u0108\3\2\2\2\u0106\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u0109\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010a\b\34\7\2\u010a@\3\2\2\2"+
		"\u010b\u010f\5\33\n\2\u010c\u010d\5?\34\2\u010d\u010e\5\33\n\2\u010e\u0110"+
		"\3\2\2\2\u010f\u010c\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\b\35\20\2\u0114\u0115\b"+
		"\35\13\2\u0115B\3\2\2\2\u0116\u0117\5\31\t\2\u0117\u0118\3\2\2\2\u0118"+
		"\u0119\b\36\7\2\u0119D\3\2\2\2\u011a\u011b\5\33\n\2\u011b\u011c\3\2\2"+
		"\2\u011c\u011d\b\37\16\2\u011d\u011e\b\37\13\2\u011eF\3\2\2\2\u011f\u0120"+
		"\5\31\t\2\u0120\u0121\3\2\2\2\u0121\u0122\b \7\2\u0122H\3\2\2\2\u0123"+
		"\u0124\7k\2\2\u0124\u0125\7o\2\2\u0125\u0126\7r\2\2\u0126\u0127\7n\2\2"+
		"\u0127\u0128\7g\2\2\u0128\u0129\7o\2\2\u0129\u012a\7g\2\2\u012a\u012b"+
		"\7p\2\2\u012b\u012c\7v\2\2\u012c\u012d\7u\2\2\u012d\u012e\3\2\2\2\u012e"+
		"\u012f\b!\7\2\u012f\u0130\b!\21\2\u0130J\3\2\2\2\u0131\u0132\5\33\n\2"+
		"\u0132\u0133\3\2\2\2\u0133\u0134\b\"\16\2\u0134L\3\2\2\2\u0135\u0136\5"+
		"\27\b\2\u0136\u0137\3\2\2\2\u0137\u0138\b#\22\2\u0138\u0139\b#\13\2\u0139"+
		"\u013a\b#\n\2\u013aN\3\2\2\2\u013b\u013c\5\31\t\2\u013c\u013d\3\2\2\2"+
		"\u013d\u013e\b$\7\2\u013eP\3\2\2\2\u013f\u0140\7.\2\2\u0140\u0141\3\2"+
		"\2\2\u0141\u0142\b%\7\2\u0142R\3\2\2\2\u0143\u0144\5\33\n\2\u0144\u0145"+
		"\3\2\2\2\u0145\u0146\b&\23\2\u0146T\3\2\2\2\u0147\u0148\5\27\b\2\u0148"+
		"\u0149\3\2\2\2\u0149\u014a\b\'\22\2\u014a\u014b\b\'\13\2\u014b\u014c\b"+
		"\'\n\2\u014cV\3\2\2\2\u014d\u014e\t\7\2\2\u014e\u014f\3\2\2\2\u014f\u0150"+
		"\b(\7\2\u0150\u0151\b(\13\2\u0151X\3\2\2\2\u0152\u0153\7}\2\2\u0153\u0154"+
		"\3\2\2\2\u0154\u0155\b)\24\2\u0155Z\3\2\2\2\u0156\u0157\7\177\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u0159\b*\24\2\u0159\\\3\2\2\2\u015a\u015c\n\7\2\2"+
		"\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\b+\7\2\u0160^\3\2\2\2\27\2\3"+
		"\4\5\6\7\b\t\n\u00a4\u00ab\u00b8\u00bd\u00c0\u00c4\u00d6\u00e8\u00ff\u0106"+
		"\u0111\u015d\25\t\f\2\7\5\2\7\7\2\7\b\2\7\3\2\b\2\2\7\n\2\t\16\2\7\4\2"+
		"\6\2\2\t\r\2\7\2\2\t\6\2\7\6\2\t\n\2\7\t\2\t\3\2\t\5\2\t\31\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}