package com.domaintoolsapi.exceptions;

public abstract class DomainToolsException extends Exception {

	private static final long serialVersionUID = 1L;

	public DomainToolsException(String message) {
		super(message);
	}

	public abstract int getCode();
	
	public String toString(){
		return "Code error : "+getCode()+" "+super.toString();
	}
}
