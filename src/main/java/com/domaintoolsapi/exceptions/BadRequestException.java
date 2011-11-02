package com.domaintoolsapi.exceptions;

public class BadRequestException extends DomainToolsException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message){
		super(message);
	}

	@Override
	public int getCode() {
		return 400;
	}
}
