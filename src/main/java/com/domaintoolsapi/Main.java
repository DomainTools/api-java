/**
 * Main
 *
 * @author Julien SOSIN
 *
 */
package com.domaintoolsapi;

import java.util.HashMap;


/**
 * Sample class to use DomainAPI java library.
 * 
 * @author Julien SOSIN
 */
public class Main {
	
	public static void main(String[] args) {
		DomainTools domainTools = new DomainTools(args[0], args[1]);
		try {
			System.out.println(domainTools.use("domain-suggestions").where("query=domain%20tools").signed(true).toXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		DTRequest domainToolsRequest = domainTools.use("registrant-alert").on("domaintools.com").where("query=domaintool&days_back=4").signed(false);
//		System.out.println(domainToolsRequest.toXML());
//		String res = dtRequest.on("domaintools.com1").toXML();
//		System.out.println(res);

//		JsonNode response1 = domainToolsRequest.toObject();
//		System.out.println("1 : "+response1);

	}
}