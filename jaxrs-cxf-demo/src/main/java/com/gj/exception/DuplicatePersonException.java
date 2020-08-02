package com.gj.exception;

public class DuplicatePersonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public DuplicatePersonException() {
		super();
	}

	public DuplicatePersonException(String m) {

		super(m);
		message = m;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
