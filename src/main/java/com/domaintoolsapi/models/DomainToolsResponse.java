package com.domaintoolsapi.models;

import org.codehaus.jackson.JsonNode;

import com.domaintoolsapi.services.DomainToolsNodesService;


public class DomainToolsResponse {
	
	private String a_JSON_response;
	private String a_HTML_response;
	private String a_XML_response;

	public DomainToolsResponse() {}

	public String getA_JSON_response() {
		return a_JSON_response;
	}

	public void setA_JSON_response(String a_JSON_response) {
		this.a_JSON_response = a_JSON_response;
	}

	public String getA_HTML_response() {
		return a_HTML_response;
	}

	public void setA_HTML_response(String a_HTML_response) {
		this.a_HTML_response = a_HTML_response;
	}

	public String getA_XML_response() {
		return a_XML_response;
	}

	public void setA_XML_response(String a_XML_response) {
		this.a_XML_response = a_XML_response;
	}
	
	public JsonNode getA_Object_response(){
		return DomainToolsNodesService.getDomainToolsNode(getA_JSON_response());
	}
}