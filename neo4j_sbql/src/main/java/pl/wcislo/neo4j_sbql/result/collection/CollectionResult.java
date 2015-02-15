package pl.wcislo.neo4j_sbql.result.collection;

import java.util.Collection;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public abstract class CollectionResult extends AbstractQueryResult {

	public CollectionResult() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract Collection<SingleResult> getElements();

}
