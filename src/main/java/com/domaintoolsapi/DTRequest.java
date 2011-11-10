package com.domaintoolsapi;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

import com.domaintoolsapi.exceptions.DomainToolsException;

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
	 * Parameters
	 */
	private String parameters;
	private Map<String, String> parametersMap;

	/**
	 * Responses
	 */
	private String responseJSON;
	private String responseHTML;
	private String responseXML;
	private JsonNode responseObject;


	/**
	 * Create a new request
	 * @param domainTools
	 * @param product
	 */
	public DTRequest(DomainTools domainTools, String product) {
		this.domainTools = domainTools;
		this.product = product;
		this.parameters = "";
		this.parametersMap = new HashMap<String,String>();
		this.domain = "";
		this.format = "";
		this.responseJSON = "";
		this.responseHTML = "";
		this.responseXML = "";
		this.responseObject = null;
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
	 * Define the product<br/>
	 * Example : reverse-ip
	 * @param product
	 * @return this request
	 */
	public DTRequest use(String product) {
		this.product = product;
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
	 * @throws Exception 
	 */
	public void execute() throws DomainToolsException{
		if(!isAlreadyExecuted()){
			DTService.execute(this);
		}
	}

	/**
	 * Test if the request has been already executed 
	 * @return true we have already the request's response
	 * @throws DomainToolsException 
	 */
	private boolean isAlreadyExecuted() throws DomainToolsException{
		boolean res = false;
		if(format.equals(DTConstants.JSON) && !responseJSON.isEmpty()
				|| format.equals(DTConstants.HTML) && !responseHTML.isEmpty()
				|| format.equals(DTConstants.XML) && !responseXML.isEmpty()
				|| format.equals(DTConstants.OBJECT) && responseObject != null){
			res=true;

		}
		return res;
	}
	
	/**
	 * Re-execute the request
	 * @return the request's response
	 * @throws Exception 
	 */
	public DTRequest reexecute() throws DomainToolsException{
		return DTService.execute(this);
	}

	/**
	 * Re-execute the request
	 * @return the request's response
	 * @throws Exception 
	 */
	public DTRequest refresh() throws DomainToolsException{
		return DTService.execute(this);
	}
	
	

	/**
	 * Add parameters to the request
	 * @param options parameters/values
	 * @return this request
	 */
	public DTRequest where(Map<String, String> parameters) {
		this.parametersMap = parameters;
		return this;
	}

	/**
	 * Add parameters to the request
	 * @param parameters Example : "limit=10"
	 * @return this request
	 */
	public DTRequest where(String parameters) {
		//If the request has already a String parameter, we concat
		if(this.parameters.isEmpty()){
			this.parameters = parameters;
		}
		else{
			this.parameters = this.parameters.concat("&"+parameters);
		}
		return this;
	}

	/**
	 * Return the request response in XML format
	 * @return the XML's String
	 * @throws Exception 
	 */
	public DTRequest toXML() throws DomainToolsException {
		setFormat(DTConstants.XML);
		return this;
	}

	/**
	 * Return the request response in JSON format
	 * @return the JSON's String
	 * @throws Exception 
	 */
	public DTRequest toJSON() throws DomainToolsException{
		setFormat(DTConstants.JSON);
		return this;
	}

	/**
	 * Return the request response in HTML format
	 * @return the HTML's String
	 * @throws Exception 
	 */
	public String toHTML() throws DomainToolsException {
		setFormat(DTConstants.HTML);
		execute();
		return responseHTML;
	}

	/**
	 * Return the request's response in JsonNode format
	 * As a general design rule, most accessors ("getters") are included in this base class,
	 * to allow for traversing structure without type casts
	 * @return a JsonNode
	 * @throws Exception 
	 */
	public DTRequest toObject() throws DomainToolsException {
		setFormat(DTConstants.OBJECT);
		return this;
	}

	/**
	 * Reset ALL parameters
	 * @return this request
	 */
	public DTRequest resetParameters(){
		this.parameters = "";
		this.parametersMap = new HashMap<String,String>();
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
		this.parameters = "";
		this.parametersMap = new HashMap<String,String>();
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

	protected Map<String, String> getParametersMap() {
		return parametersMap;
	}

	protected DTRequest getDomainToolsResponse() {
		return this;
	}

	public String getJSON() throws DomainToolsException{
		//If we have already a XML response, we convert it in JSON
		if(this.responseJSON.isEmpty()){
			if(!this.responseXML.isEmpty()){
				this.responseJSON = DTConverterService.getDTConverterService().XML2JSon(this.responseXML);
			}
			else{
				setFormat(DTConstants.JSON);
				execute();
			}
		}
		return responseJSON;
	}

	public String getXML() throws DomainToolsException{
		//If we have already a JSON response, we convert it in XML
		if(this.responseXML.isEmpty()){
			System.out.println(this.responseJSON);
			if(!this.responseJSON.isEmpty()){
				this.responseXML = DTConverterService.getDTConverterService().JSon2XML(this.responseJSON);
			}
			else{
				setFormat(DTConstants.XML);
				execute();
			}
		}
		return responseXML;
	}

	public String getHTML() throws DomainToolsException{
		setFormat(DTConstants.HTML);
		execute();
		return responseHTML;
	}

	public JsonNode getObject() throws DomainToolsException{
		setFormat(DTConstants.OBJECT);
		execute();
		return responseObject;
	}

	public void setResponseJSON(String responseJSON) {
		this.responseJSON = responseJSON;
	}

	public void setResponseHTML(String responseHTML) {
		this.responseHTML = responseHTML;
	}

	public void setResponseXML(String responseXML) {
		this.responseXML = responseXML;
	}

	public void setResponseObject(JsonNode responseObject) {
		this.responseObject = responseObject;
	}
}