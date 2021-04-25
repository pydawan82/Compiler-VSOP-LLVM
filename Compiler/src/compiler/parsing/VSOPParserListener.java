package compiler.parsing;
// Generated from e:\Data\David\git\Compiler-VSOP-LLVM\Compiler\grammars\VSOPParser.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VSOPParser}.
 */
public interface VSOPParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VSOPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(VSOPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(VSOPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#clazz}.
	 * @param ctx the parse tree
	 */
	void enterClazz(VSOPParser.ClazzContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#clazz}.
	 * @param ctx the parse tree
	 */
	void exitClazz(VSOPParser.ClazzContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(VSOPParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(VSOPParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(VSOPParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(VSOPParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(VSOPParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(VSOPParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(VSOPParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(VSOPParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#formals}.
	 * @param ctx the parse tree
	 */
	void enterFormals(VSOPParser.FormalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#formals}.
	 * @param ctx the parse tree
	 */
	void exitFormals(VSOPParser.FormalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(VSOPParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(VSOPParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(VSOPParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(VSOPParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ass}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterAss(VSOPParser.AssContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ass}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitAss(VSOPParser.AssContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterNew(VSOPParser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitNew(VSOPParser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minus}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterMinus(VSOPParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minus}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitMinus(VSOPParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isnull}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterIsnull(VSOPParser.IsnullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isnull}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitIsnull(VSOPParser.IsnullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bl}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterBl(VSOPParser.BlContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bl}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitBl(VSOPParser.BlContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterWhile(VSOPParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitWhile(VSOPParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterCall(VSOPParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitCall(VSOPParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unit}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterUnit(VSOPParser.UnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unit}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitUnit(VSOPParser.UnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterNot(VSOPParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitNot(VSOPParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lit}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterLit(VSOPParser.LitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lit}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitLit(VSOPParser.LitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code self}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterSelf(VSOPParser.SelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code self}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitSelf(VSOPParser.SelfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfcall}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterSelfcall(VSOPParser.SelfcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfcall}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitSelfcall(VSOPParser.SelfcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code oi}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterOi(VSOPParser.OiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code oi}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitOi(VSOPParser.OiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterLet(VSOPParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitLet(VSOPParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braceExpr}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterBraceExpr(VSOPParser.BraceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braceExpr}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitBraceExpr(VSOPParser.BraceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterIf(VSOPParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitIf(VSOPParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binop}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void enterBinop(VSOPParser.BinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binop}
	 * labeled alternative in {@link VSOPParser#object}.
	 * @param ctx the parse tree
	 */
	void exitBinop(VSOPParser.BinopContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(VSOPParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(VSOPParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(VSOPParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(VSOPParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link VSOPParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(VSOPParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link VSOPParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(VSOPParser.BooleanLiteralContext ctx);
}