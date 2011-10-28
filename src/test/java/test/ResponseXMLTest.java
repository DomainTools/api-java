package test;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.domaintoolsapi.*;
import com.domaintoolsapi.exceptions.DomainToolsException;


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
