package com.gj.exception;

public class ChatException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public ChatException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public ChatException() {
		super();
	}
}
