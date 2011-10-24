package com.domaintoolsapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * This class allows to execute the DomainTools's requests
 * @author Julien SOSIN
 */
public class DTService {

	/**
	 * HttpURLConnection object used to do HTTP connection.
	 */
	private static HttpURLConnection httpConnection;
	/**
	 * Default line separator
	 */
	private static String lineSeparator ;
	private static URL url;

	protected static DTResponse execute(DTRequest domainToolsRequest){
		//If no format specified, set Json
		if(domainToolsRequest.getformat().isEmpty()) domainToolsRequest.setformat(DTConstants.JSON);
		getLineSeparator();		
		url = DTURLService.buildURL(domainToolsRequest);
		return doRequest(domainToolsRequest);
	}

	private static DTResponse doRequest(DTRequest domainToolsRequest){
		DTResponse domainToolsResponse = new DTResponse();
		StringBuilder stringBuilder_response = new StringBuilder();
		try{
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setDoOutput(true);

			//Read the response
			InputStream  response = httpConnection.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(response));
			String sLine;
			while ((sLine = bufReader.readLine()) != null){
				stringBuilder_response.append(sLine);
				stringBuilder_response.append(lineSeparator);
			}
			if(domainToolsRequest.getformat().equals(DTConstants.XML))
				domainToolsResponse.setResponseXML(stringBuilder_response.toString());
			else if(domainToolsRequest.getformat().equals(DTConstants.HTML))
				domainToolsResponse.setResponseHTML(stringBuilder_response.toString());
			else if(domainToolsRequest.getformat().equals(DTConstants.OBJECT))
				domainToolsResponse.setResponseObject(DTNodesService.getDomainToolsNode(stringBuilder_response.toString()));
			else domainToolsResponse.setResponseJSON(stringBuilder_response.toString());
			//We set the response in the request to not reuse it later
			domainToolsRequest.setDomainToolsResponse(domainToolsResponse);

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//May be thrown if the product doesn't exist
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpConnection.disconnect();
		}
		return domainToolsResponse;
	}

	private static void getLineSeparator() {
		try{
			lineSeparator = System.getProperty("line.separator");
		}
		catch (Exception e){
			lineSeparator = "\n";
		}
	}
}
