
lexer grammar GraphQLBaseSchema;

UNION_DEF: 'union' -> type(TYPE_LIT), pushMode(UNION);
SCALAR_DEF: 'scalar' -> type(TYPE_LIT), pushMode(DEF_NAME_CTX);
TYPE_DEF: 'type' -> type(TYPE_LIT), pushMode(TYPE);
INTERFACE_DEF: 'interface' -> type(TYPE_LIT), pushMode(DEF_NAME_CTX);
ENUM_DEF: 'enum' -> type(TYPE_LIT), pushMode(DEF_NAME_CTX);
INPUT_DEF: 'input' -> type(TYPE_LIT), pushMode(DEF_NAME_CTX);

LCURLY: '{' -> pushMode(CODE_0);

WS:  [ \t\r\n] -> skip;
NAME: [a-zA-Z][a-zA-Z0-9]*;
TYPE_DEC: [a-zA-Z][a-zA-Z0-9]*;

COMMENT_ENTER: '#' -> skip, pushMode(COMMENT);
COLON   : ':';
WORD    : ('a'..'z' | 'A'..'Z')+;
BLOCK: (.)+?;
NEWLINE : ('\r'? '\n' | '\r') -> skip;
TYPE_LIT: [a-z];

mode CODE_0;

CODE_0_LCURLY: '{' -> type(OTHER), pushMode(TYPE_SCOPE);
RCURLY: '}' -> popMode;     // Close for LCURLY
CODE_0_OTHER: ~[{}]+ -> type(OTHER);

mode TYPE_SCOPE;

CODE_N_LCURLY: '{' -> type(OTHER), pushMode(CODE_0);
CODE_N_RCURLY: '}' -> type(RCURLY), popMode, pushMode(DEFAULT_MODE);
OTHER: ~[{}]+;

mode UNION;

UNION_NAME: NAME -> type(TYPE_DEC);
UNION_WS:  [ \t\r\n] -> skip;
EQ: '=' -> skip, popMode, pushMode(UNION_CONTEXT);

mode UNION_CONTEXT;

WS_UNION:  [ \t\r\n] -> skip;
OR: WS*?'|'WS*? -> skip;
BODY: NAME (OR NAME)+ -> type(BLOCK), popMode;

mode DEF_NAME_CTX;

WS_SCALAR: WS -> skip;
SCALAR_NAME: NAME -> type(TYPE_DEC), popMode;

mode TYPE;

WS_TYPE: WS -> skip;
IMPL_: 'implements' -> skip, pushMode(TYPE_IMPL_CONTEXT);
TYPE_NAME: NAME -> type(TYPE_DEC);
EXIT_TYPE: LCURLY -> type(LCURLY), popMode, pushMode(TYPE_SCOPE);

mode TYPE_IMPL_CONTEXT;

WS_TYPE_CTX: WS -> skip;
COMMA: ',' -> skip;
SUPERTYPE_NAME: NAME -> type(NAME);
EXIT_IMPL: LCURLY -> type(LCURLY), popMode, pushMode(TYPE_SCOPE);


mode COMMENT;

BREAK: [\r\n] -> skip, popMode;
BRACKET_INCLUDE: '{' -> type(STR);
BRACKET_INCLUDE_CLOSE: '}' -> type(STR);
STR: ~[\r\n]+ -> skip;
