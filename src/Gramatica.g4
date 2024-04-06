grammar Gramatica;

// Gramática sem recursão à esquerda e fatorada, correspondente à gramática dada no Campus Virtual

program: statement+;

statement: assignment | expression;

assignment: variable '=' expression;

expression: term (('+' | '-') term)*;

term: factor (('*' | '/') factor)*;

factor: '(' expression ')' 
       | NUMBER 
       | variable;

variable: LETTER;

// A gramática dada não aceita números com ponto flutuante
NUMBER: DIGIT+;
LETTER: [a-zA-Z];
DIGIT: [0-9];

WS: [ \t\r\n]+ -> skip; // Ignora espaços em branco