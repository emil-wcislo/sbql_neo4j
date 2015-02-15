package pl.wcislo.neo4j_sbql.visitor.utils;

import pl.wcislo.neo4j_sbql.result.single.SimpleValueResult;
import pl.wcislo.neo4j_sbql.result.single.SingleResult;

public class SimpleValueUtils {

	public SimpleValueUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Object coercionToSimpleValue(SingleResult sr) {
		if(sr instanceof SimpleValueResult) {
			SimpleValueResult simpleV = (SimpleValueResult) sr;
			Object value = simpleV.getValue();
			return value;
		} else {
			throw new IllegalArgumentException("Attempt to coerction to simple value, but got: "+sr);
		}
	}
	
	public static int compareResults(SingleResult leftRes, SingleResult rightRes) {
		Object leftObj = SimpleValueUtils.coercionToSimpleValue(leftRes);
		Object rightObj = SimpleValueUtils.coercionToSimpleValue(rightRes);
		if(leftObj instanceof Number && rightObj instanceof Number) {
			Number leftNum = (Number) leftObj;
			Number rightNum = (Number) rightObj;
			Double leftD = leftNum.doubleValue();
			Double rightD = rightNum.doubleValue();
			int res = leftD.compareTo(rightD);
			return res;
		} else if(leftObj instanceof String && rightObj instanceof String) {
			String leftS = (String) leftObj;
			String rightS = (String) rightObj;
			int res = leftS.compareTo(rightS);
			return res;
		} else if(leftObj.getClass().equals(rightObj.getClass()) && leftObj instanceof Comparable && rightObj instanceof Comparable) {
			Comparable leftC = (Comparable) leftObj;
			Comparable rightC = (Comparable) rightObj;
			int res = leftC.compareTo(rightC);
			return res;
		} else {
			throw new IllegalArgumentException("Attempt to compare objects - left: "+leftObj+", right: "+rightObj+"but got they are incomparable");
		}
	}
}
