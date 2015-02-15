package pl.wcislo.neo4j_sbql.visitor;

import pl.wcislo.neo4j_sbql.ast.binary.DivideExpression;
import pl.wcislo.neo4j_sbql.ast.binary.DotExpression;
import pl.wcislo.neo4j_sbql.ast.binary.EqualsExpression;
import pl.wcislo.neo4j_sbql.ast.binary.GreaterThanExpression;
import pl.wcislo.neo4j_sbql.ast.binary.LessThanExpression;
import pl.wcislo.neo4j_sbql.ast.binary.MinusExpression;
import pl.wcislo.neo4j_sbql.ast.binary.MultiplyExpression;
import pl.wcislo.neo4j_sbql.ast.binary.PlusExpression;
import pl.wcislo.neo4j_sbql.ast.binary.WhereExpression;
import pl.wcislo.neo4j_sbql.ast.terminal.BooleanTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.DoubleTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.IntegerTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.NameTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.StringTerminal;

public interface ASTVisitor {
	
	//aux name expressions
	
	
	//binary expressions
	void visitDivideExpression(DivideExpression expr);
	void visitDotExpression(DotExpression expr);
	void visitEqualsExpression(EqualsExpression expr);
	void visitGreaterThanExpression(GreaterThanExpression expr);
	void visitLessThanExpression(LessThanExpression expr);
	void visitMinusExpression(MinusExpression expr);
	void visitMultiplyExpression(MultiplyExpression expr);
	void visitPlusExpression(PlusExpression expr);
	void visitWhereExpression(WhereExpression expr);
	
	//terminals
	void visitBooleanTerminal(BooleanTerminal expr);
	void visitDoubleTerminal(DoubleTerminal expr);
	void visitIntegerTerminal(IntegerTerminal expr);
	void visitNameTerminal(NameTerminal expr);
	void visitStringTerminal(StringTerminal expr);
	
	//unary expressions
}
