package com.domaintoolsapi;

import java.util.Map;

import org.codehaus.jackson.JsonNode;


/**
 * This class contains all types of responses we can get from a DomainTools API 
 * @author Julien SOSIN
 */

public class DTResponse {
	
	private String format;
	private String domain;
	private String product;
	private String parameters;
	private Map<String, String> parameters_map;
	
	private String responseJSON;
	private String responseHTML;
	private String responseXML;
	private JsonNode responseObject;

	public DTResponse(String format, String domain, String product, String parameters, Map<String, String> parameters_map) {
		this.format = format;
		this.domain = domain;
		this.product = product;
		this.parameters = parameters;
		this.parameters_map = parameters_map;
		this.responseJSON = "";
		this.responseHTML = "";
		this.responseXML = "";
		this.responseObject = null;
	}

	protected String getResponseJSON() {
		return responseJSON;
	}

	protected void setResponseJSON(String responseJSON) {
		this.responseJSON = responseJSON;
	}

	protected String getResponseHTML() {
		return responseHTML;
	}

	protected void setResponseHTML(String responseHTML) {
		this.responseHTML = responseHTML;
	}

	protected String getResponseXML() {
		return responseXML;
	}

	protected void setResponseXML(String responseXML) {
		this.responseXML = responseXML;
	}
	
	public void setResponseObject(JsonNode responseObject) {
		this.responseObject = responseObject;
	}

	protected JsonNode getResponseObject(){
		return this.responseObject;
	}

	
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @return the parameters_map
	 */
	public Map<String, String> getParameters_map() {
		return parameters_map;
	}

	/**
	 * 
	 * @return
	 */
	public boolean equals(DTRequest dtRequest) {
		boolean res = true;
		if(!this.getDomain().equals(dtRequest.getDomain())) res = false;
		if(!this.getFormat().equals(dtRequest.getFormat())) res = false;
		if(!this.getParameters().equals(dtRequest.getParameters())) res = false;
		if(!this.getParameters_map().equals(dtRequest.getParameters_map())) res = false;
		return res;
	}	
}