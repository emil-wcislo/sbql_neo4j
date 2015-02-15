package pl.wcislo.neo4j_sbql.ast.binary;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class EqualsExpression extends BinaryExpression {

	public EqualsExpression(Expression leftExpr, Expression rightExpr) {
		super(leftExpr, rightExpr);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitEqualsExpression(this);
	}

}
