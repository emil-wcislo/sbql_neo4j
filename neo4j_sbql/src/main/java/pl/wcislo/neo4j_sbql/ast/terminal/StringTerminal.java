package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class StringTerminal extends SimpleValueTerminal<String> {

	public StringTerminal(String value) {
		super(value);
	}
	
	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitStringTerminal(this);
	}
	
}
