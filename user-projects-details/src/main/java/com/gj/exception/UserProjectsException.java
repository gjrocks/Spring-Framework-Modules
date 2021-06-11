package com.gj.exception;

public class UserProjectsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public UserProjectsException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public UserProjectsException() {
		super();
	}
}
