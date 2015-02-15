package pl.wcislo.neo4j_sbql.result.single.factory;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.PropertyContainer;

import pl.wcislo.neo4j_sbql.envs.ENVSBinder;
import pl.wcislo.neo4j_sbql.result.single.SimpleValueResult;

public class PropertyBinderFactory {

	public PropertyBinderFactory() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Creates binders to properties
	 * @param pc
	 * @return
	 */
	public List<ENVSBinder> createResult(PropertyContainer pc) {
		List<ENVSBinder> res = new ArrayList<>();
		Iterable<String> propertyKeys = pc.getPropertyKeys();
		for(String propKey : propertyKeys) {
			Object property = pc.getProperty(propKey);
			if(property == null) {
				continue;
			}
			SimpleValueResultFactory f = new SimpleValueResultFactory();
			if(property.getClass().isArray()) {
				List<SimpleValueResult> pArray = f.createSimpleResultArray(property);
				for(SimpleValueResult r : pArray) {
					ENVSBinder b = new ENVSBinder(propKey, r);
					res.add(b);
				}
			} else {
				SimpleValueResult p = f.createSimpleResult(property);
				ENVSBinder b = new ENVSBinder(propKey, p);
				res.add(b);
			}
		}
		return res;
	}
}
