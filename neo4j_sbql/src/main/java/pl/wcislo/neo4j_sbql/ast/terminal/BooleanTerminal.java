package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class BooleanTerminal extends SimpleValueTerminal<Boolean> {

	public BooleanTerminal(Boolean value) {
		super(value);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitBooleanTerminal(this);
	}

}
