package test;
import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import junit.framework.TestCase;


public class JsonParserTest extends TestCase {

	public void testJsonParser(){
			String s = "{\"a\":{\"b\" : \"value\"}}";
			ObjectMapper m = new ObjectMapper();
			JsonNode rootNode = null;
			try {
				rootNode = (JsonNode) m.readTree(s);
			} catch (JsonProcessingException e) {
				fail();
				e.printStackTrace();
			} catch (IOException e) {
				fail();
				e.printStackTrace();
			}
			assertEquals("value", rootNode.get("a").get("b").getTextValue().toString());
	}
}
