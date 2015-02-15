package pl.wcislo.neo4j_sbql.ast.binary;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class DotExpression extends BinaryExpression {

	public DotExpression(Expression leftExpr, Expression rightExpr) {
		super(leftExpr, rightExpr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitDotExpression(this);
	}

}
