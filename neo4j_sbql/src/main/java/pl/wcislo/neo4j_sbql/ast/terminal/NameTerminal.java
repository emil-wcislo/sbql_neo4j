package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class NameTerminal extends Expression {
	private String name;
	
	public NameTerminal(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNameTerminal(this);
	}
	
}
