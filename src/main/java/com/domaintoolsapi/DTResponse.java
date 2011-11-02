package com.domaintoolsapi;

import java.util.Map;

import org.codehaus.jackson.JsonNode;


/**
 * This class contains all types of responses we can get from a DomainTools API 
 * @author Julien SOSIN
 */

public class DTResponse {
	
	private final String format;
	private final String domain;
	private final String product;
	private final String parameters;
	private final Map<String, String> parametersMap;
	
	private String responseJSON;
	private String responseHTML;
	private String responseXML;
	private JsonNode responseObject;

	public DTResponse(String format, String domain, String product, String parameters, Map<String, String> parameters_map) {
		this.format = format;
		this.domain = domain;
		this.product = product;
		this.parameters = parameters;
		this.parametersMap = parameters_map;
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
	public Map<String, String> getParametersMap() {
		return parametersMap;
	}	
}