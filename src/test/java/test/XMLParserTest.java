package test;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import junit.framework.TestCase;


public class XMLParserTest extends TestCase {

	public void testXMLParser(){
		String s = "<a>value</a>";
		DocumentBuilder parser = null;
		Document document = null;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = parser.parse(new InputSource(new StringReader(s)));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		String res = document.getElementsByTagName("a").item(0).getTextContent();
		assertEquals("value", res);
	}
}
