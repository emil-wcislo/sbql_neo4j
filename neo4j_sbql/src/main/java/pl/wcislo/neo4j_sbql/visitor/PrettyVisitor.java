package pl.wcislo.neo4j_sbql.visitor;

import pl.wcislo.neo4j_sbql.ast.auxname.AuxNameExpression;
import pl.wcislo.neo4j_sbql.ast.binary.BinaryExpression;
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
import pl.wcislo.neo4j_sbql.ast.terminal.SimpleValueTerminal;
import pl.wcislo.neo4j_sbql.ast.terminal.StringTerminal;

public class PrettyVisitor implements ASTVisitor {

	StringBuilder sb = new StringBuilder();
	
	public PrettyVisitor() {
	}
	
	

	@Override
	public void visitDivideExpression(DivideExpression expr) {
		printBinaryExpression(expr, "/");
	}

	@Override
	public void visitDotExpression(DotExpression expr) {
		printBinaryExpression(expr, ".");
	}

	@Override
	public void visitEqualsExpression(EqualsExpression expr) {
		printBinaryExpression(expr, "=");
	}

	@Override
	public void visitGreaterThanExpression(GreaterThanExpression expr) {
		printBinaryExpression(expr, ">");
	}

	@Override
	public void visitLessThanExpression(LessThanExpression expr) {
		printBinaryExpression(expr, "<");
	}

	@Override
	public void visitMinusExpression(MinusExpression expr) {
		printBinaryExpression(expr, "-");
	}

	@Override
	public void visitMultiplyExpression(MultiplyExpression expr) {
		printBinaryExpression(expr, "*");
	}

	@Override
	public void visitPlusExpression(PlusExpression expr) {
		printBinaryExpression(expr, "+");
	}

	@Override
	public void visitWhereExpression(WhereExpression expr) {
		printBinaryExpression(expr, "where");
	}

	@Override
	public void visitBooleanTerminal(BooleanTerminal expr) {
		printSimpleValueTerminal(expr);
	}

	@Override
	public void visitDoubleTerminal(DoubleTerminal expr) {
		printSimpleValueTerminal(expr);
	}

	@Override
	public void visitIntegerTerminal(IntegerTerminal expr) {
		printSimpleValueTerminal(expr);
	}

	@Override
	public void visitNameTerminal(NameTerminal expr) {
		print(expr.getName()+" ");
	}

	@Override
	public void visitStringTerminal(StringTerminal expr) {
		printSimpleValueTerminal(expr);
	}

	
	private void print(String s) {
		sb.append(s);
	}
	
	private void printBinaryExpression(BinaryExpression expr, String value) {
		expr.getLeftExpr().accept(this);
		print(value+" ");
		expr.getRightExpr().accept(this);
	}
	
	private void printUnaryExpression(pl.wcislo.neo4j_sbql.ast.unary.UnaryExpression expr, String value) {
		expr.getInnerExpr().accept(this);
		print(value+" ");
	}
	
	private void printAuxNameExpr(AuxNameExpression expr, String value) {
		print(value+" "+expr.getAuxName());
	}
	
	private void printSimpleValueTerminal(SimpleValueTerminal expr) {
		if(expr instanceof StringTerminal) {
			print("\""+expr.getValue().toString()+"\" ");
		} else {
			print(expr.getValue().toString()+" ");	
		}
		
	}
	
	@Override
	public String toString() {
		return sb.toString().trim();
	}
}
