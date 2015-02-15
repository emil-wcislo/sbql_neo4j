package pl.wcislo.neo4j_sbql.result;

import org.neo4j.graphdb.GraphDatabaseService;

import pl.wcislo.neo4j_sbql.envs.ENVSFrame;

public abstract class AbstractQueryResult {

	public AbstractQueryResult() {
	}
	
	public ENVSFrame nested(GraphDatabaseService db) {
		ENVSFrame res = new ENVSFrame();
		return res;
	}

}
