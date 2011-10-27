import org.junit.Test;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.NotAuthorizedException;

import junit.framework.TestCase;


public class AuthenticationTest extends TestCase {

	public void testAutentication(){
		DomainTools domainTools = new DomainTools("bad_username", "bad_key");
			try {
				domainTools.use("").on("google.com").toJSON();
				fail(); //unreachable because an exception should be launch
			} catch (Exception e) {}
	}
}
