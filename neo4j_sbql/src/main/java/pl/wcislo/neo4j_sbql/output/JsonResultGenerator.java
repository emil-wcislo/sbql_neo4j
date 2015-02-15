package pl.wcislo.neo4j_sbql.output;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

import pl.wcislo.neo4j_sbql.result.AbstractQueryResult;
import pl.wcislo.neo4j_sbql.result.collection.BagResult;
import pl.wcislo.neo4j_sbql.result.single.NodeResult;

public class JsonResultGenerator {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonResultGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public String writeResult(AbstractQueryResult res) {
		StringWriter w = new StringWriter();
		try {
			JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator(w);
			writeResultInternal(res, jg);
			jg.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String string = w.toString();
		return string;
	}
	
	private void writeResultInternal(AbstractQueryResult qr, JsonGenerator jg) throws JsonGenerationException, IOException {
		if(qr instanceof BagResult) {
			writeBagResult((BagResult) qr, jg);
		} else if(qr instanceof NodeResult) {
			writeNodeResult((NodeResult) qr, jg);
		}
	}
	
	private void writeNodeResult(NodeResult nr, JsonGenerator jg) throws JsonGenerationException, IOException {
		Node node = nr.getNode();
		jg.writeStartObject();
		Iterable<Label> labels = node.getLabels();
		jg.writeObjectField("id", node.getId());
		jg.writeFieldName("label");
		jg.writeStartArray();
		for(Label l : labels) {
			jg.writeString(l.name());
		}
		jg.writeEndArray();
		jg.writeEndObject();
	}
	
	private void writeBagResult(BagResult br, JsonGenerator jg) throws JsonGenerationException, IOException {
		jg.writeStartArray();
		for(AbstractQueryResult qr : br.getElements()) {
			writeResultInternal(qr, jg);
		}
		jg.writeEndArray();
	}
	
}
