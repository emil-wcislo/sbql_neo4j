package pl.wcislo.neo4j_sbql.ast.binary;

import pl.wcislo.neo4j_sbql.ast.Expression;

public abstract class BinaryExpression extends Expression {
	private Expression leftExpr;
	private Expression rightExpr;
	
	public BinaryExpression(Expression leftExpr, Expression rightExpr) {
		super();
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}
	
	public Expression getLeftExpr() {
		return leftExpr;
	}
	public Expression getRightExpr() {
		return rightExpr;
	}

}
