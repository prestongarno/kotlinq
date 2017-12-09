
lexer grammar GraphQLBaseSchema;

BLOCK_DEF: 'type'|'interface'|'enum'|'input' WS? NAME WS?;
SCALAR_DEF: 'scalar' WS? NAME;
UNION_DEF: 'union' NAME EQ NAME (OR NAME)+;

EQ: '=';
LCURLY: '{' -> pushMode(CODE_0);
NAME: [a-zA-Z][a-zA-Z0-9]*;
WS:    [ \t\r\n] -> skip;
OR: '|' -> skip;

mode CODE_0;

CODE_0_LCURLY: '{' -> type(OTHER), pushMode(CODE_N);
RCURLY: '}' -> popMode;     // Close for LCURLY
CODE_0_OTHER: ~[{}]+ -> type(OTHER);

mode CODE_N;

CODE_N_LCURLY: '{' -> type(OTHER), pushMode(CODE_N);
CODE_N_RCURLY: '}' -> type(OTHER), popMode;
OTHER: ~[{}]+;


