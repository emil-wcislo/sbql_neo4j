package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class IntegerTerminal extends SimpleValueTerminal<Integer> {

	public IntegerTerminal(Integer value) {
		super(value);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitIntegerTerminal(this);
	}

}
