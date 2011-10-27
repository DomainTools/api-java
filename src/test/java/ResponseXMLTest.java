import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.DomainToolsException;

import junit.framework.TestCase;


public class ResponseXMLTest extends TestCase {

	/**
	 * Test if the XML response is in a valid XML format
	 */
	public void testResponseXML(){
		DomainTools domainTools = new DomainTools("username", "key");
		String xml = "";
		try {
			xml = domainTools.use("whois").on("domaintools.com").toXML();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			factory.setAttribute("http://xml.org/sax/features/validation", false);
			DocumentBuilder builder = factory.newDocumentBuilder();        
			builder.parse(new InputSource(new StringReader(xml)));
		}    
		catch (SAXException se){
			fail();
		} catch (DomainToolsException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
