# Parser-GLC
Parser desenvolvido para disciplina de Teoria da Computação.

Aluno: Gabriel Silva Duarte 
Matrícula: 202220171 
Disciplina: Teoria da Computação - GCC108

# Descrição:
É utilizada a biblioteca ANTLR para gerar um lexer e um parser a partir de uma gramática definida.

O projeto consiste em uma aplicação Java que lê a entrada padrão, cria um lexer e um parser, adiciona um listener de erro personalizado, começa a análise na regra do programa, cria um walker de árvore de análise genérico, cria uma nova instância do EvalListener, percorre a árvore criada durante a análise, aciona callbacks, imprime a árvore de análise e imprime o valor da expressão.

O projeto também contém uma classe interna CustomErrorListener para personalizar a saída de erro e sair do programa após o primeiro erro e uma classe interna EvalListener para avaliar a árvore de parsing e guardar os valores das variáveis e expressões em um HashMap.

# Requisitos:
Java 8 ou superior
ANTLR 4.13.1 (No repositório já possui o .jar para o pleno funcionamento do programa)
