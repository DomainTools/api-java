package com.domaintoolsapi;


/**
 * DomainTools API in Java
 * Executes HTTP requests on DomainTools API and provides
 * responses's datas in JSON, XML, HTML, or Java Object 
 * @author Julien SOSIN
 */
public class DomainTools {

	private String apiUsername;
	private String apiKey;
	private boolean signed;

	/**
	 * Construct a DomainTools API Object
	 * Most requests sent to the DomainTools API require authentication.
	 * You have to specify your API username and your API Key
	 * @param apiUsername
	 * @param apiKey
	 */
	public DomainTools(String apiUsername, String apiKey){
		this.apiUsername = apiUsername;
		this.apiKey = apiKey;
		this.signed = true;
	}
	
	/**
	 * Construct a DomainTools API Object
	 * You have to specify your API username and your API Key
	 * @param apiUsername
	 * @param apiKey
	 * @param signed if you want to use the hashed message authentication code or not
	 */
	public DomainTools(String apiUsername, String apiKey, boolean signed){
		this.apiUsername = apiUsername;
		this.apiKey = apiKey;
		this.signed = signed;
	}

	/**
	 * Use a product provides by DomainTools
	 * @param String of the name of the product
	 * Domain Profile : Basic registrant, server, and registration data for a domain name, plus preview data for other products<br />
	 * - "whois"  Whois records for domain names and IP addresses<br />
	 * - "whois/live" Live lookup on the registrar's Whois server. <br />
	 * - "whois/history" Historical Whois records<br />
	 * - "hosting-history" Provides the registrar, IP and name server history for a domain name<br />
	 * - "reverse-ip" List of domains that share the same network host. <br />
	 *  Parameters : <br />
	 *    - "limit" Limits the size of the domain list than can appear in a response. The limit is applied per-IP address, not for the entire request.<br />
	 * - "name-server-domains" List of domains that share the same primary name server. <br />
	 *  Parameters : <br />
	 *   - "limit" Limits the size of the domain list than can appear in a response.<br />
	 * - "reverse-whois" Provides a list of domain names with Whois records that match a specific query<br />
	 * 	Parameters :
	 *   - "terms" (Required) List of one or more terms to search for in the Whois record, separated with the pipe character ( | ).
	 *   - "exclude" Domain names with Whois records that match these terms will be excluded from the result set. Separate multiple terms with the pipe character ( | ). 
	 *   - "scope" Sets the scope of the report to include only current Whois records, or to include both current and historic records. Value must be current (the default) or historic. 
	 *   - "mode" 
	 *      - "quote" : only lists the size and retail price of the query
	 *      - "purchase" : includes the complete list of domain names that match the query 
	 * - "domain-suggestions" Generates available domain suggestions that are related to a query string
	 *  Parameters :
	 *   - "query" Query string — must be at least two characters long. Use spaces to separate multiple terms, but be sure to URL encode the values before passing them to the API. 
	 * - "domain-search" Searches active and deleted domain names that match a query string
	 *  Parameters :
	 *   - "query" (<65 letters, numbers) (Required) Query string — must be at least two characters long. Use spaces to separate multiple terms, but be sure to URL encode the values before passing them to the API. 
	 *   - "max_length" (2...65) Limit the maximum domain character count. Default: 25 
	 *   - "hyphens" (optional|only|none) Filters results with hyphens in the domain name. Default: optional
	 *   - "numbers" (optional|none) Filters results with number in the domain name.
	 *   - "status" (all|active|delete) Filters results based on their registration status. Default: all
	 *   - "page" (1...100) Sets the page of results to retrieve from the server. Each page is limited to 100results
	 * - "mark-alert" Search new domains registered today for specific keywords.
	 *  Parameters :
	 *   - "query" (Required) One or more terms separated by the pipe character ( | ). 
	 *   - "exclude" Domain names with these words will be excluded from the result set. Separate multiple terms with the pipe character ( | ). 
	 *   - "domain_status" Sets the scope of domain names to search. By default, the API will search both new domain names and domains which are now on-hold (pending delete). To narrow your search to only one of these status codes, set this parameter to either new or on-hold. 
	 * 	 - "days_back" Use this parameter in exceptional circumstances where you need to search domains registered up to six days prior to the current date. Set the value to an integer in the range of 1-6. 
	 * - "registrant-alert" : Receive notification whenever specific people register, renew or delete domain name names
	 *  Parameters :
	 *   - "query" (Required) One or more terms separated by the pipe character ( | ). 
	 *   - "exclude" Domain names with these words will be excluded from the result set. Separate multiple terms with the pipe character ( | ). 
	 *   - "domain_status" Sets the scope of domain names to search. By default, the API will search both new domain names and domains which are now on-hold (pending delete). To narrow your search to only one of these status codes, set this parameter to either new or on-hold. 
	 * 	 - "days_back" Use this parameter in exceptional circumstances where you need to search domains registered up to six days prior to the current date. Set the value to an integer in the range of 1-6. 
	 *   - "limit" Limit the number of matched domain names that are returned in your result set. 
	 * @return a DomainToolsRequest
	 */
	public DTRequest use(String product){
		DTRequest domainToolsRequest = new DTRequest(this,product);
		return domainToolsRequest;
	}

	protected String getapi_username() {
		return apiUsername;
	}

	public String getapi_key() {
		return apiKey;
	}

	public boolean issigned() {
		return signed;
	}

	public void setsigned(boolean signed) {
		this.signed = signed;
	}	
}