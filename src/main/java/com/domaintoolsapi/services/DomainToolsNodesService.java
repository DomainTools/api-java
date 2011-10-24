package com.domaintoolsapi.services;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DomainToolsNodesService {

	public static JsonNode getDomainToolsNode(String p_JSON_response){
		ObjectMapper m = new ObjectMapper();
		JsonNode l_rootNode = null;
		try {
			l_rootNode = (JsonNode) m.readTree(p_JSON_response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return l_rootNode;
	}
}
