package pl.wcislo.neo4j_sbql.envs;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.collection.BagResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class ENVS {

	private List<ENVSFrame> frames = new ArrayList<ENVSFrame>();
	private GraphDatabaseService db;
	
	public ENVS(GraphDatabaseService db) {
		init(db);
		this.db = db;
	}
	
	public BagResult bind(String name) {
		BagResult res = new BagResult();
		for(int i=frames.size() - 1; i>=0; i--) {
			ENVSFrame f = frames.get(i);
			List<SingleResult> r = f.bind(name);
			if(!r.isEmpty()) {
				res.getElements().addAll(r);
				return res;
			}
		}
		return res;
	}
	
	public ENVSFrame nested(AbstractQueryResult res) {
		ENVSFrame nested = res.nested(db);
		this.frames.add(nested);
		return nested;
	}
	
	public void init(GraphDatabaseService db) {
		EnvsGraphDatabaseNameResolver res = new EnvsGraphDatabaseNameResolver(db);
		frames.add(res);
	}
	
	public ENVSFrame pop() {
		int index = frames.size()-1;
		ENVSFrame res = frames.get(index);
		frames.remove(index);
		return res;
		
	}

}
