package test;
import java.io.IOException;

import junit.framework.TestCase;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.DomainToolsException;


public class ResponseJSONTest extends TestCase {

	/**
	 * Test if the JSON response is in a valid JSON format
	 */
	public void testResponseJSON(){
		DomainTools domainTools = new DomainTools("username", "key");
		ObjectMapper m = new ObjectMapper();
		String json = "";
		try {
			json = domainTools.use("whois").on("domaintools.com").toJSON().execute().getJSON();
			m.readTree(json);
		} catch (JsonProcessingException e) {
			fail();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DomainToolsException e) {
			e.printStackTrace();
		}
	}
}
