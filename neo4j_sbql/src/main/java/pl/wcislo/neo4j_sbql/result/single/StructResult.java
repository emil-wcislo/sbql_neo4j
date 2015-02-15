package pl.wcislo.neo4j_sbql.result.single;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;

import pl.wcislo.neo4j_sbql.envs.ENVSFrame;
import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;

public class StructResult extends SingleResult {

	private List<AbstractQueryResult> contents;
	
	public StructResult() {
	}
	
	@Override
	public ENVSFrame nested(GraphDatabaseService db) {
		ENVSFrame res = new ENVSFrame();
		for(AbstractQueryResult r : contents) {
			ENVSFrame rn = r.nested(db);
			res.getBinders().addAll(rn.getBinders());
		}
		return res;
	}
	
	public List<AbstractQueryResult> getContents() {
		return contents;
	}

	public int size() {
		return contents.size();
	}

	public Iterator<AbstractQueryResult> iterator() {
		return contents.iterator();
	}

	public boolean add(AbstractQueryResult e) {
		return contents.add(e);
	}

	public boolean remove(Object o) {
		return contents.remove(o);
	}

	public boolean addAll(Collection<? extends AbstractQueryResult> c) {
		return contents.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends AbstractQueryResult> c) {
		return contents.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return contents.removeAll(c);
	}

	public void add(int index, AbstractQueryResult element) {
		contents.add(index, element);
	}

	public AbstractQueryResult remove(int index) {
		return contents.remove(index);
	}

}
