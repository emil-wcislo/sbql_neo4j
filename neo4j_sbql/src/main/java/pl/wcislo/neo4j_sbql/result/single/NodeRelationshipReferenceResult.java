package pl.wcislo.neo4j_sbql.result.single;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import pl.wcislo.neo4j_sbql.envs.ENVSBinder;
import pl.wcislo.neo4j_sbql.envs.ENVSFrame;
import pl.wcislo.neo4j_sbql.result.single.factory.PropertyBinderFactory;

public class NodeRelationshipReferenceResult extends SingleResult {

	private Long relId;
	private Node startNode;
	
	public NodeRelationshipReferenceResult(Long relId, Node startNode) {
		this.relId = relId;
		this.startNode = startNode;
	}

	
	@Override
	public ENVSFrame nested(GraphDatabaseService db) {
		ENVSFrame f = new ENVSFrame();
		//create binder to another node (other than node which was origin of this relationship)
		Relationship rel = db.getRelationshipById(relId);
		Node otherNode = rel.getOtherNode(startNode);
		NodeResult nr = new NodeResult(otherNode);
		Iterable<Label> labels = otherNode.getLabels();
		for(Label l : labels) {
			String name = l.name();
			ENVSBinder b = new ENVSBinder(name, nr);
			f.getBinders().add(b);
		}
		//create binders based on relationship properties
		PropertyBinderFactory bf = new PropertyBinderFactory();
		List<ENVSBinder> propBinders = bf.createResult(rel);
		f.getBinders().addAll(propBinders);
		return f;
	}
}
