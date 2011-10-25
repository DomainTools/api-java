package com.domaintoolsapi.exceptions;

public class ServiceUnavailableException extends DomainToolsException {

	private static final long serialVersionUID = 1L;

	public ServiceUnavailableException(String message){
		super(message);
	}
}
