package com.domaintoolsapi;

import org.json.JSONException;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


public class DTConverterService {

	private static XMLSerializer xmlSerializer;
	private static DTConverterService dtConverterService;
	
	public static DTConverterService getDTConverterService() {
		if (dtConverterService == null) {
			dtConverterService = new DTConverterService();
			xmlSerializer = new XMLSerializer();
			xmlSerializer.setTypeHintsEnabled(false);
			xmlSerializer.setSkipNamespaces(true);
		}
		return dtConverterService;
	}
	
	public static String JSon2XML(String string_json){
		String res = "";
		org.json.JSONObject o;
		try {
			o = new org.json.JSONObject(string_json);
			res = org.json.XML.toString(o);
			//We have to add this to match with the xml response
			res = "<?xml version=\"1.0\"?>\n<whoisapi>"+res+"\n</whoisapi>";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return res;
	}
	
	public static String XML2JSon(String string_xml){		
		return xmlSerializer.read(string_xml).toString();  
	}
}
