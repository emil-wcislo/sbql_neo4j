package pl.wcislo.neo4j_sbql.ast.binary;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class DivideExpression extends BinaryExpression {

	public DivideExpression(Expression leftExpr, Expression rightExpr) {
		super(leftExpr, rightExpr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitDivideExpression(this);
	}

}
