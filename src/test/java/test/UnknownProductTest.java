package test;
import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.BadRequestException;
import com.domaintoolsapi.exceptions.DomainToolsException;

import junit.framework.TestCase;


public class UnknownProductTest extends TestCase {

	public void testUnknownProduct(){
		DomainTools domainTools = new DomainTools("username", "key");
			try {
				domainTools.use("unknown product").on("domaintools.com").toJSON();
				fail(); //unreachable because an exception should be launch
			} catch (DomainToolsException e) {
				if(!(e instanceof BadRequestException)) fail();
			}
	}
}
