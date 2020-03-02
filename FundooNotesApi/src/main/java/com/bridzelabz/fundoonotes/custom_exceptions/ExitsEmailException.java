package com.bridzelabz.fundoonotes.custom_exceptions;

public class ExitsEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExitsEmailException(String message) {
		super(message);
	}
}
