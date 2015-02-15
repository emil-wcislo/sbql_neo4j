package pl.wcislo.neo4j_sbql.ast.binary;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class LessThanExpression extends BinaryExpression {

	public LessThanExpression(Expression leftExpr, Expression rightExpr) {
		super(leftExpr, rightExpr);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitLessThanExpression(this);
	}

}
