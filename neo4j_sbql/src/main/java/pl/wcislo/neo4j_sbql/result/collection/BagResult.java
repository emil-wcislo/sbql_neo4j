package pl.wcislo.neo4j_sbql.result.collection;

import java.util.ArrayList;
import java.util.List;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class BagResult extends CollectionResult {

	private List<SingleResult> elements = new ArrayList<>();
	
	public BagResult() {
	}

	@Override
	public List<SingleResult> getElements() {
		return elements;
	}

}
