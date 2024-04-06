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

# Como usar:
Após executar o programa, ele estará esperando por uma entrada. Você deve digitar a entrada no terminal. A entrada deve estar de acordo com a gramática definida no arquivo Gramatica.g4.
Você pode digitar várias atribuições e apertar Enter para digitar a próxima. Para finalizar o input, no Windows, você pode usar CTRL + Z e depois pressionar Enter, já no Linux e no MacOS, você pode usar CTRL + D para enviar um sinal e indicar o fim da entrada.
O programa irá analisar a entrada de acordo com a gramática, gerar a árvore de análise e imprimir a árvore e o valor da expressão final.

# Exemplos:

a = 2 + 3 <br>
b = a * 5 <br>
c = (b - a) + 10 <br>
^Z <br>
Árvore de parsing: 
(program (statement (assignment (variable a) = (expression (term (factor 2)) + (term (factor 3))))) (statement (assignment (variable b) = (expression (term (factor (variable a)) * (factor 
5))))) (statement (assignment (variable c) = (expression (term (factor ( (expression (term (factor (variable b))) - (term (factor (variable a)))) ))) + (term (factor 10)))))) <br>
Valor da expressão final: 30

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

a = 10 <br>
b = 2 <br>
c = (a * b) + 3 <br>
d = (c / 10) * b <br>
^Z <br>
Árvore de parsing: 
(program (statement (assignment (variable a) = (expression (term (factor 10))))) (statement (assignment (variable b) = (expression (term (factor 2))))) (statement (assignment (variable c) 
= (expression (term (factor ( (expression (term (factor (variable a)) * (factor (variable b)))) ))) + (term (factor 3))))) (statement (assignment (variable d) = (expression (term (factor ( (expression (term (factor (variable c)) / (factor 10))) )) * (factor (variable b))))))) <br>
Valor da expressão final: 4

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

a = 3    
b = a + c <br>
^Z <br>
Erro: Variável c não foi definida.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

a @ b <br>
^Z <br>
Erro: Sintaxe inválida na linha: 1, posição 2: token recognition error at: '@'
