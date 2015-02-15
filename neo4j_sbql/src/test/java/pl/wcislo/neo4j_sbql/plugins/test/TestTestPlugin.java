package pl.wcislo.neo4j_sbql.plugins.test;

/**
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.test.server.HTTP;

public class TestTestPlugin {
	// @Rule public Mute mute = Mute.muteAll();

	// START SNIPPET: testExtension
	// @Path("/myEndpoint")
	// public static class MyUnmanagedExtension {
	// @GET
	// public Response myEndpoint() {
	// return Response.ok().build();
	// }
	// }

	@Path("")
	public static class UnmanagedNodeFinder {
		private GraphDatabaseService graphDb;
		private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

		public UnmanagedNodeFinder(@Context GraphDatabaseService graphDb) {
			this.graphDb = graphDb;
		}

		@GET
		// @Produces(MediaType.TEXT_PLAIN)
		@Path("{nodeId}")
		public Response myEndpoint(@PathParam("nodeId") Long id)
				throws IOException {
			Transaction tx = graphDb.beginTx();
			Node nodeById = graphDb.getNodeById(id);
			tx.close();
			ObjectNode root = JsonNodeFactory.instance.objectNode();
			ArrayNode resultRows = JsonNodeFactory.instance.arrayNode();
			resultRows.add(nodeById.toString());
			// root.set
			root.put("result", resultRows);
			// return Response.ok().build();
			return Response.status(200)
					.entity(OBJECT_MAPPER.writeValueAsString(root))
					// .entity("asdf")
					.type(MediaType.TEXT_PLAIN).build();
		}
	}

	@Test
	public void testMyExtension() throws Exception {
		// Given
		try (ServerControls server = TestServerBuilders
				.newInProcessBuilder()
				.withConfig("org.neo4j.server.database.location",
						"d:/Users/emilw_000/Documents/Neo4j/default.graphdb")
				// .withExtension("/asdf", MyUnmanagedExtension.class)
				.withExtension("/finder", UnmanagedNodeFinder.class)
				.newServer()) {
			// When
			String s = server.httpURI().resolve("finder/2").toString();

			System.out.println(s);
			Map<String, String> headers = new java.util.HashMap<String, String>();
			headers.put("Accept", "text/*");

			HTTP.Response response = HTTP.withHeaders(headers).GET(s);
			System.out.println(response.rawContent());

			// Then
			assertEquals(200, response.status());
		}
	}
	// END SNIPPET: testExtension
}