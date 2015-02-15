package pl.wcislo.neo4j_sbql.envs;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.tooling.GlobalGraphOperations;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.single.NodeResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class EnvsGraphDatabaseNameResolver extends ENVSFrame {

	private GraphDatabaseService db;
	public EnvsGraphDatabaseNameResolver(GraphDatabaseService db) {
		this.db = db;
	}
	
	@Override
	public List<SingleResult> bind(final String name) {
		Label l = new Label() {
			@Override
			public String name() {
				return name;
			}
		};
		ResourceIterable<Node> allNodesWithLabel = GlobalGraphOperations.at(db).getAllNodesWithLabel(l);
		List<SingleResult> res = new ArrayList<SingleResult>();
		for(Node n : allNodesWithLabel) {
			NodeResult ns = new NodeResult(n);
//			ENVSBinder b = new ENVSBinder(name, ns);
			res.add(ns);
		}
		return res;
	}

}
