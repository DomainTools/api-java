package com.domaintoolsapi;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

import com.domaintoolsapi.DTConstants;

/**
 * Contains all DT request's data
 * @author Julien SOISN
 */
public class DTRequest {
	/**
	 * The DomainTool's object (for login)
	 */
	private DomainTools domainTools;
	/**
	 * The DomainTool's product (like Whois, Reverse IP, ...)
	 */
	private String product;
	/**
	 * The domain's request (domain names like "DomainTools.com")
	 */
	private String domain;
	/**
	 * Format of the request
	 */
	private String format;

	/*
	 * Parameters
	 */
	private String parameters;
	private Map<String, String> parameters_map;

	/**
	 * Create a new request
	 * @param domainTools
	 * @param product
	 */
	public DTRequest(DomainTools domainTools, String product) {
		this.domainTools = domainTools;
		this.product = product;
		this.parameters = "";
		this.parameters_map = new HashMap<String,String>();
		this.domain = "";
		this.format = "";
	}

	/**
	 * Define the domain's request<br/>
	 * Example : domaintools.com
	 * @param domain
	 * @return this request
	 */
	public DTRequest on(String domain){
		this.domain = domain;
		return this;
	}
	
	/**
	 * Set if we use hashed message authentication or not.<br/>
	 * Hashed message provides a straightforward but secure method of protecting your API key.
	 * @param signed true if we use hashed message authentication
	 * @return this request
	 */
	public DTRequest signed(boolean signed){
		this.domainTools.setsigned(signed);
		return this;
	}
	
	/**
	 * Execute the request
	 * @return DomainTools's response
	 */
	public DTResponse execute(){
		return DTService.execute(this);
	}
	
	/**
	 * Add parameters to the request
	 * @param options parameters/values
	 * @return this request
	 */
	public DTRequest where(HashMap<String, String> parameters) {
		this.parameters_map = parameters;
		return this;
	}

	/**
	 * Add parameters to the request
	 * @param parameters Example : "limit=10"
	 * @return this request
	 */
	public DTRequest where(String parameters) {
		this.parameters = parameters;
		return this;
	}

	/**
	 * Re-execute the request
	 * @return the request's response
	 */
	public DTResponse refresh(){
		return execute();
	}

	protected DomainTools getdomainTools() {
		return domainTools;
	}

	protected void setdomainTools(DomainTools domainTools) {
		this.domainTools = domainTools;
	}

	protected String getproduct() {
		return product;
	}

	protected String getdomain() {
		return domain;
	}

	protected String getformat() {
		return format;
	}

	protected void setformat(String format) {
		this.format = format;
	}

	protected String getparameters() {
		return parameters;
	}

	protected Map<String, String> getparameters_map() {
		return parameters_map;
	}
	
	/**
	 * Return the request response in XML format
	 * @return the XML's String
	 */
	public String toXML() {
		setformat(DTConstants.XML);
		return  DTService.execute(this).getXMresponse();
	}

	/**
	 * Return the request response in JSON format
	 * @return the JSON's String
	 */
	public String toJSON() {
		setformat(DTConstants.JSON);
		return  DTService.execute(this).getJSON_response();
	}
	
	/**
	 * Return the request response in HTML format
	 * @return the HTML's String
	 */
	public String toHTML() {
		setformat(DTConstants.HTML);
		return  DTService.execute(this).getHTMresponse();
	}
	
	/**
	 * Return the request's response in JsonNode format
	 * As a general design rule, most accessors ("getters") are included in this base class,
	 * to allow for traversing structure without type casts
	 * @return a JsonNode
	 */
	public JsonNode toObject() {
		setformat(DTConstants.JSON);
		return  DTService.execute(this).getObject_response();
	}
}