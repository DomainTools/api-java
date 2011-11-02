package com.domaintoolsapi.exceptions;

public class NotAuthorizedException extends DomainToolsException {

	private static final long serialVersionUID = 1L;

	public NotAuthorizedException(String message){
		super(message);
	}

	@Override
	public int getCode() {
		return 401;
	}
}
