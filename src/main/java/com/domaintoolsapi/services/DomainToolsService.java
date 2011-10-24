package com.domaintoolsapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.domaintoolsapi.constants.DomainToolsConstants;
import com.domaintoolsapi.models.DomainToolsRequest;
import com.domaintoolsapi.models.DomainToolsResponse;

public class DomainToolsService {

	/**
	 * HttpURLConnection object used to do HTTP connection.
	 */
	private static HttpURLConnection a_httpConnection;
	/**
	 * Default line separator
	 */
	private static String a_line_separator ;
	private static URL a_url;

	public static DomainToolsResponse execute(DomainToolsRequest p_domainToolsRequest){
		//If no format specified, set Json
		if(p_domainToolsRequest.getA_format().isEmpty()) p_domainToolsRequest.setA_format(DomainToolsConstants.JSON);
		getLineSeparator();		
		a_url = DomainToolsURLService.buildURL(p_domainToolsRequest);	
		System.out.println("URL :\n"+a_url.toString());
		return doRequest(p_domainToolsRequest);
	}

	private static DomainToolsResponse doRequest(DomainToolsRequest p_domainToolsRequest){
		DomainToolsResponse l_domainToolsResponse = new DomainToolsResponse();
		StringBuilder l_stringBuilder_response = new StringBuilder();
		try{
			a_httpConnection = (HttpURLConnection) a_url.openConnection();
			a_httpConnection.setRequestMethod("GET");
			a_httpConnection.setDoOutput(true);

			//Read the response
			InputStream  l_response = a_httpConnection.getInputStream();
			BufferedReader l_bufReader = new BufferedReader(new InputStreamReader(l_response));
			String l_sLine;
			while ((l_sLine = l_bufReader.readLine()) != null){
				l_stringBuilder_response.append(l_sLine);
				l_stringBuilder_response.append(a_line_separator);
			}
			if(p_domainToolsRequest.getA_format().equals(DomainToolsConstants.XML)) l_domainToolsResponse.setA_XML_response(l_stringBuilder_response.toString());
			else if(p_domainToolsRequest.getA_format().equals(DomainToolsConstants.HTML)) l_domainToolsResponse.setA_HTML_response(l_stringBuilder_response.toString());
			else l_domainToolsResponse.setA_JSON_response(l_stringBuilder_response.toString());

			a_httpConnection.disconnect();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//May be thrown if the product doesn't exist
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return l_domainToolsResponse;
	}

	private static void getLineSeparator() {
		try{
			a_line_separator = System.getProperty("line.separator");
		}
		catch (Exception e){
			a_line_separator = "\n";
		}
	}
}
