package pl.wcislo.neo4j_sbql.ast.auxname;

import pl.wcislo.neo4j_sbql.ast.Expression;

public abstract class AuxNameExpression extends Expression {

	private Expression innerExpr;
	private String auxName;
	
	public AuxNameExpression(Expression innerExpr, String auxName) {
		super();
		this.innerExpr = innerExpr;
		this.auxName = auxName;
	}
	
	public Expression getInnerExpr() {
		return innerExpr;
	}
	public String getAuxName() {
		return auxName;
	}

}
