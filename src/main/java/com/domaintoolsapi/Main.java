/**
 * Main
 *
 * @author Julien SOSIN
 *
 */
package com.domaintoolsapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;

import com.domaintoolsapi.exceptions.DomainToolsException;


/**
 * Sample class to use DomainAPI java library.
 * 
 * @author Julien SOSIN
 */
public class Main {
	
	public static void main(String[] args) {
		DomainTools domainTools = new DomainTools(args[0], args[1]);
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("query", "domain%20tools");
			JsonNode s = domainTools.use("reverse-ip").on("nameintel.com").where("limit=10").toObject();
			Iterator<JsonNode> it = s.get("response").get("ip_addresses").get("domain_names").getElements();
			while(it.hasNext()){
				System.out.println(it.next());
			}
			
		} catch (DomainToolsException e) {
			e.printStackTrace();
		}

	}
}