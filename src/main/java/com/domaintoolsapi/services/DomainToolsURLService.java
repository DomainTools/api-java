package com.domaintoolsapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import com.domaintoolsapi.constants.DomainToolsConstants;
import com.domaintoolsapi.models.DomainToolsRequest;

public class DomainToolsURLService {

	private static String a_string_url;
	private static URL a_url;

	/**
	 * Build and return an URL
	 * Add URI, Parameters, Signature and Format
	 * @param p_domainToolsRequest
	 * @return the URL's request
	 */	
	public static URL buildURL(DomainToolsRequest p_domainToolsRequest){
		String l_uri = getURI(p_domainToolsRequest);
		a_string_url = DomainToolsConstants.SCHEME+DomainToolsConstants.HOST+DomainToolsConstants.PATH+"/"+l_uri;
		//If the user want to use the signed authentication
		if(p_domainToolsRequest.getA_domainTools().isA_signed()) addSignature(p_domainToolsRequest, l_uri);
		else addUserNameAndKey(p_domainToolsRequest);
		addParameters(p_domainToolsRequest);
		addResponseFormat(p_domainToolsRequest);
		try {
			a_url = new URL(a_string_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return a_url;
	}

	/**
	 * All products haven't the same URL's structure
	 * So we have to adapt the URL to the request
	 * @param p_domainToolsRequest
	 * @return the URI (like "/yourdomain.com/whois")
	 */	
	public static String getURI(DomainToolsRequest p_domainToolsRequest){
		String l_uri;
		if(p_domainToolsRequest.getA_product().equals("whois") || p_domainToolsRequest.getA_product().equals("hosting-history") ||
				p_domainToolsRequest.getA_product().equals("reverse-ip") || p_domainToolsRequest.getA_product().equals("name-server-domains"))
			l_uri = p_domainToolsRequest.getA_domain()+"/"+p_domainToolsRequest.getA_product();
		else l_uri = p_domainToolsRequest.getA_product()+"/"+p_domainToolsRequest.getA_domain();
		return l_uri;
	}

	private static void addParameters(DomainToolsRequest p_domainToolsRequest){
		boolean isFirstParameter = true;
		//String parameters
		if(!p_domainToolsRequest.getA_parameters().isEmpty())
			a_string_url = a_string_url.concat("&"+p_domainToolsRequest.getA_parameters());
		//Map parameters
		if(p_domainToolsRequest.getA_parameters_map().size() > 0){
			//If parameters is already in the url
			Set<String> keys = p_domainToolsRequest.getA_parameters_map().keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				if(!isFirstParameter) a_string_url = a_string_url.concat("&");
				isFirstParameter = false;
				String key = it.next();
				String value = (String) p_domainToolsRequest.getA_parameters_map().get(key);
				a_string_url = a_string_url.concat(key+"="+value);
			}
		}
	}
	
	private static void addUserNameAndKey(DomainToolsRequest p_domainToolsRequest) {
		a_string_url = a_string_url.concat("?api_username="+p_domainToolsRequest.getA_domainTools().getA_api_username()+"&api_key="+p_domainToolsRequest.getA_domainTools().getA_api_key());
	}

	/**
	 * Add hashed message authentication code to the URL's request
	 * @param p_domainToolsRequest
	 * @param p_uri
	 */
	private static void addSignature(DomainToolsRequest p_domainToolsRequest, String p_uri){
		try {
			DTSigner signer = new DTSigner(p_domainToolsRequest.getA_domainTools().getA_api_username(), 
					p_domainToolsRequest.getA_domainTools().getA_api_key());
			String timestamp = signer.timestamp();
			String signature = signer.sign(timestamp, p_uri);
			a_string_url = a_string_url.concat("?api_username="+p_domainToolsRequest.getA_domainTools().getA_api_username()+
					"&signature="+signature+"&timestamp="+timestamp);
		} catch(Exception e) {
			System.out.println("Error trying to sign query");
		}
	}

	/**
	 * Add response's format to the URL's request
	 * @param p_domainToolsRequest
	 */
	private static void addResponseFormat(DomainToolsRequest p_domainToolsRequest){
		if(p_domainToolsRequest.getA_format().equals(DomainToolsConstants.XML)) 
			a_string_url=a_string_url.concat("&"+DomainToolsConstants.FORMAT_XML);
		if(p_domainToolsRequest.getA_format().equals(DomainToolsConstants.HTML)) 
			a_string_url=a_string_url.concat("&"+DomainToolsConstants.FORMAT_HTML);
	}
}