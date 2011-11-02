package com.domaintoolsapi.exceptions;

public class UnkownException extends DomainToolsException {

	private static final long serialVersionUID = 1L;

	public UnkownException(String message){
		super(message);
	}

	@Override
	public int getCode() {
		return 0;
	}
}
