package com.bridzelabz.fundoonotes.customexception;

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
