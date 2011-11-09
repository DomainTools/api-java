package test;

import java.io.IOException;

import junit.framework.TestCase;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class XML2JSONConvertTest extends TestCase{

	/**
	 * Test if the XML converted in JSON matches with the JSON returned by DomainTools
	 */
	public void testXML2JSON(){
		String generatedJson = "{\"response\":{\"ip_addresses\":{\"ip_address\":\"66.249.17.251\",\"domain_names\":[\"DMAINTOOLS.COM\",\"DOAMINTOOLS.COM\"],\"domain_count\":52}}}";
		String originalJson = "{\"response\":{\"ip_addresses\":{\"ip_address\":\"66.249.17.251\",\"domain_count\":52,\"domain_names\":[\"DMAINTOOLS.COM\",\"DOAMINTOOLS.COM\"]}}}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode generatedJsonNode = null;
		JsonNode originalJsonNode = null;
		try {
			generatedJsonNode = (JsonNode) objectMapper.readTree(generatedJson);
			originalJsonNode = (JsonNode) objectMapper.readTree(originalJson);
			if(!generatedJsonNode.equals(originalJsonNode)) fail();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
