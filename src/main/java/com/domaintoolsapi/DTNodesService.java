package com.domaintoolsapi;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Convert a Json response in a Traversable object
 * @author Julien SOSIN
 *
 */
public class DTNodesService {

	protected static JsonNode getDomainToolsNode(String responseJSON){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = (JsonNode) objectMapper.readTree(responseJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootNode;
	}
}
