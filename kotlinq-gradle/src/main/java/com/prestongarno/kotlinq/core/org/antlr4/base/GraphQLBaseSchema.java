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
		BLOCK_DEF=1, SCALAR_DEF=2, UNION_DEF=3, EQ=4, LCURLY=5, NAME=6, WS=7, 
		OR=8, RCURLY=9, OTHER=10;
	public static final int
		CODE_0=1, CODE_N=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "CODE_0", "CODE_N"
	};

	public static final String[] ruleNames = {
		"BLOCK_DEF", "SCALAR_DEF", "UNION_DEF", "EQ", "LCURLY", "NAME", "WS", 
		"OR", "CODE_0_LCURLY", "RCURLY", "CODE_0_OTHER", "CODE_N_LCURLY", "CODE_N_RCURLY", 
		"OTHER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'='", "'{'", null, null, "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BLOCK_DEF", "SCALAR_DEF", "UNION_DEF", "EQ", "LCURLY", "NAME", 
		"WS", "OR", "RCURLY", "OTHER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\u0091\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\5\2:\n\2\3\2\3\2\5\2>\n\2\5\2@\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3J\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\6\4Z\n\4\r\4\16\4[\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\7\7f\n\7\f\7\16\7"+
		"i\13\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\6\f}\n\f\r\f\16\f~\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\6\17\u008e\n\17\r\17\16\17\u008f\2\2\20\5\3\7\4\t"+
		"\5\13\6\r\7\17\b\21\t\23\n\25\2\27\13\31\2\33\2\35\2\37\f\5\2\3\4\6\4"+
		"\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\4\2}}\177\177\2\u0098\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\3\27\3\2\2\2\3\31\3\2\2\2\4\33\3\2"+
		"\2\2\4\35\3\2\2\2\4\37\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tM\3\2\2\2\13]\3"+
		"\2\2\2\r_\3\2\2\2\17c\3\2\2\2\21j\3\2\2\2\23n\3\2\2\2\25r\3\2\2\2\27w"+
		"\3\2\2\2\31|\3\2\2\2\33\u0082\3\2\2\2\35\u0087\3\2\2\2\37\u008d\3\2\2"+
		"\2!\"\7v\2\2\"#\7{\2\2#$\7r\2\2$@\7g\2\2%&\7k\2\2&\'\7p\2\2\'(\7v\2\2"+
		"()\7g\2\2)*\7t\2\2*+\7h\2\2+,\7c\2\2,-\7e\2\2-@\7g\2\2./\7g\2\2/\60\7"+
		"p\2\2\60\61\7w\2\2\61@\7o\2\2\62\63\7k\2\2\63\64\7p\2\2\64\65\7r\2\2\65"+
		"\66\7w\2\2\66\67\7v\2\2\679\3\2\2\28:\5\21\b\298\3\2\2\29:\3\2\2\2:;\3"+
		"\2\2\2;=\5\17\7\2<>\5\21\b\2=<\3\2\2\2=>\3\2\2\2>@\3\2\2\2?!\3\2\2\2?"+
		"%\3\2\2\2?.\3\2\2\2?\62\3\2\2\2@\6\3\2\2\2AB\7u\2\2BC\7e\2\2CD\7c\2\2"+
		"DE\7n\2\2EF\7c\2\2FG\7t\2\2GI\3\2\2\2HJ\5\21\b\2IH\3\2\2\2IJ\3\2\2\2J"+
		"K\3\2\2\2KL\5\17\7\2L\b\3\2\2\2MN\7w\2\2NO\7p\2\2OP\7k\2\2PQ\7q\2\2QR"+
		"\7p\2\2RS\3\2\2\2ST\5\17\7\2TU\5\13\5\2UY\5\17\7\2VW\5\23\t\2WX\5\17\7"+
		"\2XZ\3\2\2\2YV\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\n\3\2\2\2]^\7"+
		"?\2\2^\f\3\2\2\2_`\7}\2\2`a\3\2\2\2ab\b\6\2\2b\16\3\2\2\2cg\t\2\2\2df"+
		"\t\3\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\20\3\2\2\2ig\3\2\2\2"+
		"jk\t\4\2\2kl\3\2\2\2lm\b\b\3\2m\22\3\2\2\2no\7~\2\2op\3\2\2\2pq\b\t\3"+
		"\2q\24\3\2\2\2rs\7}\2\2st\3\2\2\2tu\b\n\4\2uv\b\n\5\2v\26\3\2\2\2wx\7"+
		"\177\2\2xy\3\2\2\2yz\b\13\6\2z\30\3\2\2\2{}\n\5\2\2|{\3\2\2\2}~\3\2\2"+
		"\2~|\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\b\f\4\2\u0081"+
		"\32\3\2\2\2\u0082\u0083\7}\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\r\4\2"+
		"\u0085\u0086\b\r\5\2\u0086\34\3\2\2\2\u0087\u0088\7\177\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u008a\b\16\4\2\u008a\u008b\b\16\6\2\u008b\36\3\2\2\2\u008c"+
		"\u008e\n\5\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090 \3\2\2\2\r\2\3\49=?I[g~\u008f\7\7\3\2\b"+
		"\2\2\t\f\2\7\4\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}