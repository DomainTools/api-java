package test;
import junit.framework.TestCase;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.BadRequestException;
import com.domaintoolsapi.exceptions.DomainToolsException;

public class ParametersTest extends TestCase {

	public void testAuthentication(){
		DomainTools domainTools = new DomainTools("username", "key");
			try {
				System.out.println(domainTools.use("domain-search").where("bad parameters").toJSON());
				fail(); //unreachable because an exception should be launch
			} catch (DomainToolsException e) {
				if(!(e instanceof BadRequestException)) fail();
			}
	}
}
