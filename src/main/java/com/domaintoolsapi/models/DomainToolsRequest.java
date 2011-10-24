package com.domaintoolsapi.models;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

import com.domaintoolsapi.DomainTools;
import com.domaintoolsapi.constants.DomainToolsConstants;
import com.domaintoolsapi.services.DomainToolsService;

/**
 * Contains all DT request's data
 * @author Julien SOISN
 */
public class DomainToolsRequest {
	/**
	 * The DomainTool's object (for login)
	 */
	private DomainTools a_domainTools;
	/**
	 * The DomainTool's product (like Whois, Reverse IP, ...)
	 */
	private String a_product;
	/**
	 * Object representation of the URL
	 */
	private URL a_url;
	/**
	 * String representation of the URL
	 */
	private String a_string_url;
	/**
	 * The domain's request (domain names like "DomainTools.com")
	 */
	private String a_domain;
	/**
	 * Format of the request
	 */
	private String a_format;

	/*
	 * Parameters
	 */
	private String a_parameters;
	private Map<String, String> a_parameters_map;

	public DomainToolsRequest(DomainTools p_domainTools, String p_product) {
		this.a_domainTools = p_domainTools;
		this.a_product = p_product;
		this.a_parameters = "";
		this.a_parameters_map = new HashMap<String,String>();
		this.a_domain = "";
		this.a_format = "";
	}

	/**
	 * Define the domain's request
	 * @param p_domain
	 * @return this request
	 */
	public DomainToolsRequest on(String p_domain){
		this.a_domain = p_domain;
		return this;
	}
	
	/**
	 * Set if we use hashed message authentication or not
	 * @param this request
	 */
	public DomainToolsRequest signed(boolean p_signed){
		this.a_domainTools.setA_signed(p_signed);
		return this;
	}
	
	/**
	 * Execute the request
	 * @return DomainTools's response
	 */
	public DomainToolsResponse execute(){
		return DomainToolsService.execute(this);
	}
	
	/**
	 * Add parameters to the request
	 * @param options parameters/values
	 * @return this request
	 */
	public DomainToolsRequest where(HashMap<String, String> parameters) {
		this.a_parameters_map = parameters;
		return this;
	}

	/**
	 * Add parameters to the request
	 * @param parameters 
	 */
	public DomainToolsRequest where(String parameters) {
		this.a_parameters = parameters;
		return this;
	}

	public DomainToolsResponse refresh(){
		return execute();
	}

	public DomainTools getA_domainTools() {
		return a_domainTools;
	}

	public void setA_domainTools(DomainTools a_domainTools) {
		this.a_domainTools = a_domainTools;
	}

	public String getA_product() {
		return a_product;
	}

	public void setA_product(String a_product) {
		this.a_product = a_product;
	}

	public URL getA_url() {
		return a_url;
	}

	public void setA_url(URL a_url) {
		this.a_url = a_url;
	}

	public String getA_domain() {
		return a_domain;
	}

	public String getA_format() {
		return a_format;
	}

	public void setA_format(String a_format) {
		this.a_format = a_format;
	}

	public String getA_parameters() {
		return a_parameters;
	}

	public Map<String, String> getA_parameters_map() {
		return a_parameters_map;
	}

	public String toXML() {
		setA_format(DomainToolsConstants.XML);
		return  DomainToolsService.execute(this).getA_XML_response();
	}

	public String toJSON() {
		setA_format(DomainToolsConstants.JSON);
		return  DomainToolsService.execute(this).getA_JSON_response();
	}
	
	public String toHTML() {
		setA_format(DomainToolsConstants.HTML);
		return  DomainToolsService.execute(this).getA_HTML_response();
	}
	
	/**
	 * Return the request's response in Java Object Format
	 * As a general design rule, most accessors ("getters") are included in this base class,
	 * to allow for traversing structure without type casts
	 * @return
	 */
	public JsonNode toObject() {
		setA_format(DomainToolsConstants.JSON);
		return  DomainToolsService.execute(this).getA_Object_response();
	}
}