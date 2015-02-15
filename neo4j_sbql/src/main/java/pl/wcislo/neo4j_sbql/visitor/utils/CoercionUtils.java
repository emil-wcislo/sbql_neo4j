package pl.wcislo.neo4j_sbql.visitor.utils;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.collection.BagResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class CoercionUtils {

	public CoercionUtils() {
		
	}
	
	public static BagResult coercionToBag(AbstractQueryResult res) {
		if(res instanceof BagResult) {
			return (BagResult) res;
		} else if(res instanceof SingleResult) {
			SingleResult sr = (SingleResult) res;
			BagResult bag = new BagResult();
			bag.getElements().add(sr);
			return bag;
		}
		return new BagResult();
	}
	
	public static SingleResult coercionToSingleResult(AbstractQueryResult res) {
		if(res instanceof SingleResult) {
			return (SingleResult) res;
		} else if(res instanceof BagResult) {
			BagResult bag = (BagResult) res;
			if(bag.getElements().size() > 1) {
				throw new IllegalArgumentException("coercionToSingleResult: bag size > 1 "+res);
			}
			return bag.getElements().get(0);
		}
		return null;
	}

}
