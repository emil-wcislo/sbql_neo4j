package pl.wcislo.neo4j_sbql.result;

import org.junit.Test;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.result.parser.SbqlParser;
import pl.wcislo.neo4j_sbql.visitor.PrettyVisitor;
import static org.junit.Assert.assertEquals;

public class TestParser {

	public TestParser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new TestParser();
	}
	
	@Test
	public void testWhere() throws Exception {
//		String q = "emp where 1 + 2 > 3";
//		String q = "Movie where title = \"RescueDawn\"";
//		String s = "\"asdf\"";
//		String substring = s.substring(1, s.length()-1);
//		System.out.println(substring);
		String q = "Movie where 1 = \"RescueDawn\"";
		SbqlParser p = new SbqlParser(q);
		p.parse();
		Expression e = p.RESULT;
		PrettyVisitor pv = new PrettyVisitor();
		e.accept(pv);
		assertEquals(q, pv.toString());
//		System.out.println(e);
	}
}
