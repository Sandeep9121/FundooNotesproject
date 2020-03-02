package com.bridzelabz.fundoonotes.custom_exceptions;

public class MailNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailNotFoundException() {
		super();
	
	}

	public MailNotFoundException(String message) {
		super(message);
	
	}

}
