package test;
import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.DomainToolsException;

import junit.framework.TestCase;


public class AuthenticationTest extends TestCase {

	public void testAuthentication(){
		DomainTools domainTools = new DomainTools("bad_username", "bad_key");
			try {
				domainTools.use("").on("google.com").toJSON();
				fail(); //unreachable because an exception should be launch
			} catch (DomainToolsException e) {}
	}
}
