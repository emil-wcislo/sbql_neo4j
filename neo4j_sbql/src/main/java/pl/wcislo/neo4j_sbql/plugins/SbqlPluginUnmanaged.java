package pl.wcislo.neo4j_sbql.plugins;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.server.rest.repr.BadInputException;
import org.neo4j.server.rest.repr.InputFormat;
import org.neo4j.server.rest.repr.OutputFormat;

import com.sun.jersey.api.client.ClientResponse.Status;

import pl.wcislo.neo4j_sbql.ast.Expression;
import pl.wcislo.neo4j_sbql.output.JsonResultGenerator;
import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.parser.SbqlParser;
import pl.wcislo.neo4j_sbql.visitor.Interpreter;

@Path("/sbql")
public class SbqlPluginUnmanaged {
    private static final String QUERY_KEY = "query";
	
	private GraphDatabaseService graphDb;
	private InputFormat input;
	private OutputFormat output;
//	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public SbqlPluginUnmanaged(
			@Context GraphDatabaseService graphDb, 
			@Context InputFormat input,
            @Context OutputFormat output) {
		this.graphDb = graphDb;
		this.input = input;
		this.output = output;
	}

	
	@POST
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	@Path("{query}")
	public Response myEndpoint(
			String body
//			@PathParam("query") String query
			) throws Exception {
		
		Map<String,Object> command = input.readMap( body );
        if( !command.containsKey(QUERY_KEY) ) {
            return output.badRequest(new BadInputException( "You have to provide the 'query' parameter." ));
        }
        String query = (String) command.get( QUERY_KEY );
//		query = query.replace("%20", " ");
		SbqlParser parser = new SbqlParser(query);
		parser.parse();
		Expression treeRoot = parser.RESULT;
		Transaction tx = graphDb.beginTx();
		Interpreter i = new Interpreter(graphDb);
		treeRoot.accept(i);
		AbstractQueryResult result = i.getResult();
		JsonResultGenerator gen = new JsonResultGenerator();
		String jsonRes = gen.writeResult(result);
		tx.close();
		return Response
				.status( Status.OK )
				.entity(jsonRes.getBytes( Charset.forName("UTF-8") ) )
				.build();
	}
	
}
