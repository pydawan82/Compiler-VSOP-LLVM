grammar VSOP;

WS
	: '\t'
	| '\r'
	| '\n'
	| '\f';

ID: [A-Za-z][A-Za-z0-9]*;

TYPE
	: 'int'
	| 'float'
	;
	
OP
	: '+'
	| '-'
	;
	
INT: [0-9]+;

FLOAT
	: [0-9]*.[0-9]+
	| [0-9]+.[0-9]*
	;

STRING: '"'[\x00-\x7F]*'"';

litteral
	: INT
	| FLOAT
	| STRING
	;
	
expression
	: ID
	| litteral
	| fcall
	| expression WS* OP WS* expression
	;
	
declaration: (TYPE|ID) WS+ ID;

assignement: (declaration|ID) WS* '=' WS* expression;

parameter
	: WS+ (ID|litteral) WS+
	| parameter ',' parameter
	;

fcall: ID WS* '('parameter')';

