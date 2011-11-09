/**
 * Main
 *
 * @author Julien SOSIN
 *
 */
package com.domaintoolsapi;

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
			
			DTResponse res = domainTools.use("reverse-ip").on("nameintel.com").where("limit=2").toJSON().execute();
			
			System.out.println(res.getXML());
			
			DTResponse res2 = domainTools.use("reverse-ip").on("nameintel.com").where("limit=2").toXML().execute();
			
			System.out.println(res2.getXML());
			
		} catch (DomainToolsException e) {
			e.printStackTrace();
		}

	}
}