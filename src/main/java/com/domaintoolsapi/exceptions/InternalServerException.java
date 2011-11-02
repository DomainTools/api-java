package com.domaintoolsapi.exceptions;

public class InternalServerException extends DomainToolsException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(String message){
		super(message);
	}

	@Override
	public int getCode() {
		return 500;
	}
}
