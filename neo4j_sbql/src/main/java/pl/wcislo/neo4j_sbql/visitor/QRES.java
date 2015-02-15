package pl.wcislo.neo4j_sbql.visitor;

import java.util.Stack;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;

public class QRES {

	private Stack<AbstractQueryResult> innerStack = new Stack<AbstractQueryResult>();
	
	public AbstractQueryResult push(AbstractQueryResult item) {
		return innerStack.push(item);
	}

	public AbstractQueryResult pop() {
		return innerStack.pop();
	}

	public boolean isEmpty() {
		return innerStack.isEmpty();
	}
	

}
