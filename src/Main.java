// Aluno: Gabriel Silva Duarte
// Matrícula: 202220171
// Disciplina: Teoria da Computação
// Github: 

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

// classe principal que contém o método main.
// O método main lê a entrada padrão, cria um lexer e um parser, adiciona um listener de erro personalizado,
// começa a análise na regra do programa, cria um walker de árvore de análise genérico, cria uma nova instância do EvalListener,
// percorre a árvore criada durante a análise, aciona callbacks, imprime a árvore de análise e imprime o valor da expressão.
// A classe também contém uma classe interna CustomErrorListener para personalizar a saída de erro e sair do programa após o primeiro erro
// e uma classe interna EvalListener para avaliar a árvore de parsing e guardar os valores das variáveis e expressões em um HashMap.
public class Main {
    public static void main(String[] args) throws Exception {
        // cria um CharStream que lê da entrada padrão
        CharStream input = CharStreams.fromStream(System.in);
        // cria um lexer que se alimenta do CharStream de entrada
        GramaticaLexer lexer = new GramaticaLexer(input);
        // adiciona o listener de erro personalizado ao lexer
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomErrorListener());
        // cria um buffer de tokens retirados do lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // cria um parser que se alimenta do buffer de tokens
        GramaticaParser parser = new GramaticaParser(tokens);
        // adiciona o listener de erro personalizado ao parser
        parser.removeErrorListeners();
        parser.addErrorListener(new CustomErrorListener());
        // começa a análise na regra do programa
        ParseTree tree = parser.program();
        // cria um walker de árvore de análise genérico que pode acionar callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // cria uma nova instância do EvalListener
        EvalListener listener = new EvalListener();
         // percorre a árvore criada durante a análise, aciona callbacks
        walker.walk(listener, tree);
        // imprime a árvore de análise
        System.out.println("Árvore de parsing: ");
        System.out.println(tree.toStringTree(parser));
        // imprime o valor da expressão
        if (listener.getLastExpression() != null) {
            System.out.println("Valor da expressão final: " + listener.getValue(listener.getLastExpression()));
        }
    }


    // classe interna para personalizar a saída de erro e sair do programa após o primeiro erro
    public static class CustomErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            System.out.println("Error: Invalid syntax at line " + line + ", position " + charPositionInLine + ": " + msg);
            System.exit(1);
        }
    }


    // classe interna para avaliar a árvore de parsing e guardar os valores das variáveis e expressões em um HashMap
    public static class EvalListener extends GramaticaBaseListener {
        private Map<String, Integer> variables = new HashMap<>();
        private Map<ParseTree, Integer> values = new HashMap<>();
        private ParseTree lastExpression;

        // método chamado quando a regra de atribuição é encontrada
        @Override
        public void exitAssignment(GramaticaParser.AssignmentContext ctx) { // ctx é o contexto da regra de atribuição
            String id = ctx.variable().getText();
            int value = getValue(ctx.expression());
            variables.put(id, value);
            lastExpression = ctx.expression(); // guarda a última expressão avaliada
        }


        // método chamado quando a regra de expressão é encontrada
        @Override
        public void exitExpression(GramaticaParser.ExpressionContext ctx) {
            // avalia a expressão
            int left = getValue(ctx.term(0));
            // itera sobre os termos da expressão
            for (int i = 1; i < ctx.term().size(); i++) {
                int right = getValue(ctx.term(i));
                String op = ctx.getChild(i*2-1).getText();
                switch (op) {
                    case "+":
                        left += right;
                        break;
                    case "-":
                        left -= right;
                        break;
                    case "/":
                        left /= right;
                        break;
                    case "*":
                        left *= right;
                        break;
                }
            }
            values.put(ctx, left); // guarda o valor da expressão no HashMap
        }


        // método chamado quando a regra de termo é encontrada
        @Override
        public void exitTerm(GramaticaParser.TermContext ctx) {
            int value = getValue(ctx.factor(0));
            for (int i = 1; i < ctx.factor().size(); i++) {
                if (ctx.getChild(2*i-1).getText().equals("*")) {
                    value *= getValue(ctx.factor(i));
                } else {
                    value /= getValue(ctx.factor(i));
                }
            }
            values.put(ctx, value); // guarda o valor do termo no HashMap
        }


        // método chamado quando a regra de fator é encontrada
        @Override
        public void exitFactor(GramaticaParser.FactorContext ctx) {
            int value;
            // verifica se o fator é uma expressão, um número ou uma variável
            if (ctx.expression() != null) {
                value = getValue(ctx.expression());
            } else if (ctx.NUMBER() != null) {
                value = Integer.parseInt(ctx.NUMBER().getText());
            } else {
                String varName = ctx.variable().getText();
                // verifica se a variável foi definida
                if (variables.containsKey(varName)) {
                    value = variables.get(varName);
                } else {
                    System.out.println("Erro: Variável " + varName + " não foi definida."); // imprime um erro se a variável não foi definida
                    System.exit(1);
                    return;
                }
            }
            values.put(ctx, value); // guarda o valor do fator no HashMap
        }


        // método para obter o valor de uma expressão
        public int getValue(ParseTree node) {
            // verifica se o valor da expressão foi guardado no HashMap
            if (values.containsKey(node)) {
                return values.get(node);
            } else {
                throw new IllegalArgumentException("A seguinte expressão não foi encontrada: " + node.getText());
            }
        }

        // método para obter a última expressão avaliada
        public ParseTree getLastExpression() {
            return lastExpression;
        }
    }
}