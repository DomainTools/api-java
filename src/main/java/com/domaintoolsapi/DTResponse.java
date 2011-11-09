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

	public String getJSON() {
		//If we have already a XML response, we convert it in JSON
		if(this.responseJSON.isEmpty()){
			if(!this.responseXML.isEmpty()){
				this.responseJSON = DTConverterService.getDTConverterService().XML2JSon(this.responseXML);
			}
		}
		return responseJSON;
	}

	protected void setJSON(String responseJSON) {
		this.responseJSON = responseJSON;
	}

	public String getHTML() {
		return responseHTML;
	}

	protected void setHTML(String responseHTML) {
		this.responseHTML = responseHTML;
	}

	public String getXML() {
		//If we have already a JSON response, we convert it in XML
		if(this.responseXML.isEmpty()){
			if(!this.responseJSON.isEmpty()){
				this.responseXML = DTConverterService.getDTConverterService().JSon2XML(this.responseJSON);
			}
		}
		return responseXML;
	}

	protected void setXML(String responseXML) {
		this.responseXML = responseXML;
	}

	protected void setObject(JsonNode responseObject) {
		this.responseObject = responseObject;
	}

	public JsonNode getObject(){
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
	/**
	 * Return the DomainTools in asked format.
	 * We cannot return an object as a String, so we return the JSON format
	 * @return the response in asked format
	 */
	public String getSource() {
		if(getFormat().equals(DTConstants.XML)){
			return getXML();
		}
		else if(getFormat().equals(DTConstants.HTML)){
			getHTML();
		}
		else if(getFormat().equals(DTConstants.OBJECT)){
			getJSON();
		}
		else{
			getJSON();
		}
		return null;
	}	
	
	/**
	 * getSource's alias
	 */
	@Override
	public String toString(){
		return getSource();
	}
}