package com.domaintoolsapi;

import org.codehaus.jackson.JsonNode;


/**
 * This class contains all types of responses we can get from a DomainTools API 
 * @author Julien SOSIN
 */

public class DTResponse {
	
	private String JSON_response;
	private String HTMresponse;
	private String XMresponse;

	public DTResponse() {}

	protected String getJSON_response() {
		return JSON_response;
	}

	protected void setJSON_response(String JSON_response) {
		this.JSON_response = JSON_response;
	}

	protected String getHTMresponse() {
		return HTMresponse;
	}

	protected void setHTMresponse(String HTMresponse) {
		this.HTMresponse = HTMresponse;
	}

	protected String getXMresponse() {
		return XMresponse;
	}

	protected void setXMresponse(String XMresponse) {
		this.XMresponse = XMresponse;
	}
	
	protected JsonNode getObject_response(){
		return DTNodesService.getDomainToolsNode(getJSON_response());
	}
}