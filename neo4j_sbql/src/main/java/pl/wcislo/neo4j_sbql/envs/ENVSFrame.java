package pl.wcislo.neo4j_sbql.envs;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_0.commands.AbstractQuery;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.collection.BagResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class ENVSFrame {

	private List<ENVSBinder> binders = new ArrayList<ENVSBinder>();
	
	public ENVSFrame() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ENVSBinder> getBinders() {
		return binders;
	}
	
	public List<SingleResult> bind(String name) {
		List<SingleResult> res = new ArrayList<SingleResult>();
		for(ENVSBinder b : binders) {
			if(b.getName().equals(name)) {
				AbstractQueryResult val = b.getValue();
				if(val instanceof SingleResult) {
					res.add((SingleResult) val);
				} else if(val instanceof BagResult) {
					BagResult bag = (BagResult) val;
					res.addAll(bag.getElements());
				}
			}
		}
		return res;
	}

}
