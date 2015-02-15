package pl.wcislo.neo4j_sbql.plugins.test;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;
import org.neo4j.test.server.HTTP;

public class Test2 {
	@Rule
	public Neo4jRule neo4j = new Neo4jRule()
	        .withFixture( "CREATE (admin:Admin)" );

	@Test
	public void shouldWorkWithServer() throws Exception
	{
	    // Given
	    URI serverURI = neo4j.httpURI();

	    // When I access the server
	    HTTP.Response response = HTTP.GET( serverURI.toString() );

	    // Then it should reply
	    assertEquals(200, response.status());
	}
}
