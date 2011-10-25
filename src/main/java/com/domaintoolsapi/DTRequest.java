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
	/**
	 * The DomainTool's response
	 */
	private DTResponse domainToolsResponse;

	/**
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
		this.domainToolsResponse = null;
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
	private DTResponse execute(){
		if(isAlreadyExecuted()){
			return getDomainToolsResponse();
		}
		else	return DTService.execute(this);
	}

	/**
	 * Test if the request has been already executed 
	 * @return true we have already the request's response
	 */
	private boolean isAlreadyExecuted(){
		boolean res = false;
		if(domainToolsResponse != null){
			if(domainToolsResponse.equals(this)){
				if(format.equals(DTConstants.JSON) && !domainToolsResponse.getResponseJSON().isEmpty()
						|| format.equals(DTConstants.HTML) && !domainToolsResponse.getResponseHTML().isEmpty()
						|| format.equals(DTConstants.XML) && !domainToolsResponse.getResponseXML().isEmpty()
						|| format.equals(DTConstants.OBJECT) && domainToolsResponse.getResponseObject() != null)
					res=true;
			}
		}
		return res;
	}

	/**
	 * Re-execute the request
	 * @return the request's response
	 */
	public DTResponse refresh(){
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
		//If the request has already a String parameter, we concat
		if(!this.parameters.isEmpty()) this.parameters = this.parameters.concat("&"+parameters);
		else this.parameters = parameters;
		return this;
	}

	/**
	 * Return the request response in XML format
	 * @return the XML's String
	 */
	public String toXML() {
		setFormat(DTConstants.XML);
		return  execute().getResponseXML();
	}

	/**
	 * Return the request response in JSON format
	 * @return the JSON's String
	 */
	public String toJSON() {
		setFormat(DTConstants.JSON);
		return  execute().getResponseJSON();
	}

	/**
	 * Return the request response in HTML format
	 * @return the HTML's String
	 */
	public String toHTML() {
		setFormat(DTConstants.HTML);
		return  execute().getResponseHTML();
	}

	/**
	 * Return the request's response in JsonNode format
	 * As a general design rule, most accessors ("getters") are included in this base class,
	 * to allow for traversing structure without type casts
	 * @return a JsonNode
	 */
	public JsonNode toObject() {
		setFormat(DTConstants.OBJECT);
		return  execute().getResponseObject();
	}
	
	/**
	 * Reset ALL parameters
	 * @return this request
	 */
	public DTRequest resetParameters(){
		this.parameters = "";
		this.parameters_map = new HashMap<String,String>();
		return this;
	}
	
	/**
	 * Reset ALL parameters, product, domain and format
	 * @return this request
	 */
	public DTRequest clear(){
		this.product = "";
		this.domain = "";
		this.format = "";
		this.domainToolsResponse = null;
		this.parameters = "";
		this.parameters_map = new HashMap<String,String>();
		return this;
	}

	protected DomainTools getDomainTools() {
		return domainTools;
	}

	protected void setDomainTools(DomainTools domainTools) {
		this.domainTools = domainTools;
	}

	protected String getProduct() {
		return product;
	}

	protected String getDomain() {
		return domain;
	}

	protected String getFormat() {
		return format;
	}

	protected void setFormat(String format) {
		this.format = format;
	}

	protected String getParameters() {
		return parameters;
	}

	protected Map<String, String> getParameters_map() {
		return parameters_map;
	}

	protected void setDomainToolsResponse(DTResponse domainToolsResponse) {
		this.domainToolsResponse = domainToolsResponse;
	}

	protected DTResponse getDomainToolsResponse() {
		return domainToolsResponse;
	}
}