package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public class DoubleTerminal extends SimpleValueTerminal<Double> {

	public DoubleTerminal(Double value) {
		super(value);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitDoubleTerminal(this);
	}

}
