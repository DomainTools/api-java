/**
 * Main
 *
 * @author Julien SOSIN
 *
 */
package com.domaintoolsapi;

import java.util.HashMap;

import org.codehaus.jackson.JsonNode;


/**
 * Sample class to use DomainAPI java library.
 * 
 * @author Julien SOSIN
 */
public class DTMain {
	
	public static void main(String[] args) {
		HashMap<String,String> parameters = new HashMap<String,String>();
		parameters.put("query", "domain%20tools");		
		
		DomainTools domainTools = new DomainTools("yourname", "");
		
		DTRequest dtRequest = domainTools.use("whois").signed(true);
//		domainToolsRequest.where("terms=DomainTools%20LLC|Seattle&mode=purchase");
		String res = dtRequest.on("domaintools.com1").toXML();

		System.out.println(res);

//		JsonNode response1 = domainToolsRequest.toObject();
//		System.out.println("1 : "+response1.get("response").get("domain_count"));
	}
}
