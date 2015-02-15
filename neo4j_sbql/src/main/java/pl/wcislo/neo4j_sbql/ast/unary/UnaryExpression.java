package pl.wcislo.neo4j_sbql.ast.unary;

import pl.wcislo.neo4j_sbql.ast.Expression;

public abstract class UnaryExpression extends Expression {

	private Expression innerExpr;
	
	public UnaryExpression(Expression innerExpr) {
		super();
		this.innerExpr = innerExpr;
	}

	public Expression getInnerExpr() {
		return innerExpr;
	}
}
