package pl.wcislo.neo4j_sbql.result.single.factory;

import java.util.ArrayList;
import java.util.List;

import pl.wcislo.neo4j_sbql.result.single.DoubleResult;
import pl.wcislo.neo4j_sbql.result.single.IntegerResult;
import pl.wcislo.neo4j_sbql.result.single.SimpleValueResult;
import pl.wcislo.neo4j_sbql.result.single.StringResult;

public class SimpleValueResultFactory {

	public SimpleValueResultFactory() {
	}
	
	public SimpleValueResult createSimpleResult(Object o) {
		if(o == null) {
			return null;
		}
		SimpleValueResult res;
		if(o instanceof Integer) {
			res = new IntegerResult((Integer) o);
		} else if(o instanceof Double) {
			res = new DoubleResult((Double) o);
		} else if(o instanceof String) {
			res = new StringResult((String) o);
		} else {
			res = null;
		}
		return res;
	}
	
	public List<SimpleValueResult> createSimpleResultArray(Object o) {
		List<SimpleValueResult> res = new ArrayList<SimpleValueResult>();
		if(o instanceof Integer[]) {
			Integer[] sArr = (Integer[]) o;
			for(Integer s : sArr) {
				res.add(new IntegerResult(s));
			}
		} else if(o instanceof Double[]) {
			Double[] sArr = (Double[]) o;
			for(Double s : sArr) {
				res.add(new DoubleResult(s));
			}
		} else if(o instanceof String[]) {
			String[] sArr = (String[]) o;
			for(String s : sArr) {
				res.add(new StringResult(s));
			}
		}
		return res;
	}

}
