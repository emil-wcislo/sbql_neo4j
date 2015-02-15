package pl.wcislo.neo4j_sbql.result.single;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import pl.wcislo.neo4j_sbql.envs.ENVSBinder;
import pl.wcislo.neo4j_sbql.envs.ENVSFrame;
import pl.wcislo.neo4j_sbql.result.single.factory.PropertyBinderFactory;

public class NodeResult extends SingleResult {
	private Node node;
	
	public NodeResult(Node node) {
		this.node = node;
	}
	
	public Node getNode() {
		return node;
	}
	
	@Override
	public ENVSFrame nested(GraphDatabaseService db) {
		ENVSFrame res = new ENVSFrame();
		//create binders based on properties
		PropertyBinderFactory bf = new PropertyBinderFactory();
		List<ENVSBinder> propertyBinders = bf.createResult(node);
		res.getBinders().addAll(propertyBinders);
		//create binders based on relationships
		Iterable<Relationship> relationships = node.getRelationships();
		for(Relationship r : relationships) {
			long rId = r.getId();
			NodeRelationshipReferenceResult relRef = new NodeRelationshipReferenceResult(rId, node);
			ENVSBinder relBinder = new ENVSBinder(r.getType().name(), relRef);
			res.getBinders().add(relBinder);
		}
		return res;
	}

}
