package com.domaintoolsapi;

import org.codehaus.jackson.JsonNode;


/**
 * This class contains all types of responses we can get from a DomainTools API 
 * @author Julien SOSIN
 */

public class DTResponse {
	
	private String responseJSON;
	private String responseHTML;
	private String responseXML;
	private JsonNode responseObject;

	public DTResponse() {
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
}