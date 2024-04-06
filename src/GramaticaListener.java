// Generated from ./src/Gramatica.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramaticaParser}.
 */
public interface GramaticaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(GramaticaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(GramaticaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GramaticaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GramaticaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(GramaticaParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(GramaticaParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GramaticaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GramaticaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GramaticaParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GramaticaParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(GramaticaParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(GramaticaParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(GramaticaParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(GramaticaParser.VariableContext ctx);
}