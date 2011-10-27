import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.DomainToolsException;

import junit.framework.TestCase;


public class ResponseJSONTest extends TestCase {

	/**
	 * Test if the JSON response is in a valid JSON format
	 */
	public void testResponseJSON(){
		DomainTools domainTools = new DomainTools("username", "key");
		ObjectMapper m = new ObjectMapper();
		JsonNode rootNode = null;
		String json = "";
		try {
			json = domainTools.use("whois").on("domaintools.com").toJSON();
			rootNode = (JsonNode) m.readTree(json);
		} catch (JsonProcessingException e) {
			fail();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DomainToolsException e) {
			e.printStackTrace();
		}
	}
}
