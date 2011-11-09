package test;

import java.io.IOException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;


public class JSON2XMLConverterTest extends XMLTestCase {

	public void testJSON2XML(){
		XMLUnit.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		XMLUnit.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");


		String original_xml = "<whoisapi>"+
				"<response>"+
				"<ip_addresses>"+
				"<ip_address>66.249.17.251</ip_address>"+
				"<domain_count>52</domain_count>"+
				"<domain_names>DMAINTOOLS.COM</domain_names>"+
				"<domain_names>DOAMINTOOLS.COM</domain_names>"+
				"</ip_addresses>"+
				"</response>"+
				"</whoisapi>";
		String generated_xml = 
				"<response>" +
						"<ip_addresses>" +
						"<ip_address>66.249.17.251</ip_address>" +
						"<domain_names>DMAINTOOLS.COM</domain_names>" +
						"<domain_names>DOAMINTOOLS.COM</domain_names>" +
						"<domain_count>52</domain_count>" +
						"</ip_addresses>" +
						"</response>";

		try {
			Diff diff = new Diff(original_xml, generated_xml);
			diff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
		} catch (SAXException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

	}
}
