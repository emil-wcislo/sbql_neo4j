package pl.wcislo.neo4j_sbql.envs;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;

public class ENVSBinder {
	private String name;
	private AbstractQueryResult value;
	
	public ENVSBinder(String name, AbstractQueryResult value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public AbstractQueryResult getValue() {
		return value;
	}

}
