package pl.wcislo.neo4j_sbql.plugins.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.test.server.HTTP;
import org.neo4j.test.server.HTTP.RawPayload;

import pl.wcislo.neo4j_sbql.plugins.SbqlPluginUnmanaged;

public class TestSbqlPluginUnmanaged {

	public TestSbqlPluginUnmanaged() {
	}
	
	private ServerControls prepareServer() {
		ServerControls server = TestServerBuilders
				.newInProcessBuilder()
				.withConfig("org.neo4j.server.database.location",
						"d:/Users/emilw_000/Documents/Neo4j/default.graphdb")
				.withExtension("/test", SbqlPluginUnmanaged.class)
				.newServer();
		return server;
	}
	
//	@Test
	public void testName() throws Exception {
		// Given
		try (ServerControls server = prepareServer()) {
			// When
			String s = server.httpURI().resolve("test/sbql/Movie").toString();

			System.out.println(s);
			Map<String, String> headers = new java.util.HashMap<String, String>();
			headers.put("Accept", "text/*");

			HTTP.Response response = HTTP.withHeaders(headers).GET(s);
			System.out.println("response: "+response.rawContent());

			// Then
			assertEquals(200, response.status());
		}
	}
	
	@Test
	public void testWhere() throws Exception {
		// Given
		try (ServerControls server = prepareServer()) {
			// When
			
			String query = "Movie where title = \"RescueDawn\"";
//			query = query.replace(" ", "%20");
			String queryEnc = java.net.URLEncoder.encode(query, "UTF-8");
//			String s = server.httpURI().resolve("test/sbql/"+queryEnc).toString();
			String s = server.httpURI().resolve("test/sbql/").toString();

			System.out.println(s);
			Map<String, String> headers = new java.util.HashMap<String, String>();
			headers.put("Accept", "text/*");
//			RawPayload rp = RawPayload.rawPayload("query="+query);
			Map<String, String> body = new HashMap<>();
			body.put("query", query);
//			String body = "query="+query;
			HTTP.Response response = HTTP.withHeaders(headers).POST(s, body);
			System.out.println("response: "+response.rawContent());

			// Then
			assertEquals(200, response.status());
		}
	}

}
