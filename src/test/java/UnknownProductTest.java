import org.junit.Test;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.exceptions.BadRequestException;

import junit.framework.TestCase;


public class UnknownProductTest extends TestCase {

	@Test(expected=BadRequestException.class)
	public void testUnknownProduct(){
		try{
		DomainTools domainTools = new DomainTools("username", "key");
		domainTools.use("unknown product").on("domaintools.com").toJSON();
		fail("Un exception était attendue!");
		}catch(BadRequestException e){
			
		} catch (Exception e) {

		}
	}
}
