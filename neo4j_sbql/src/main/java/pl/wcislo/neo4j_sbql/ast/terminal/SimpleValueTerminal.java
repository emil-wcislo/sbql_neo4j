package pl.wcislo.neo4j_sbql.ast.terminal;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.visitor.ASTVisitor;

public abstract class SimpleValueTerminal<T> extends Expression {
	private T value;
	
	public SimpleValueTerminal(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
