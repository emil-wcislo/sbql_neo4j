package pl.wcislo.neo4j_sbql.ast;

import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public abstract class Expression {
	public abstract void accept(ASTVisitor visitor);
}
