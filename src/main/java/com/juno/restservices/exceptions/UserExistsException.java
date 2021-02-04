package com.juno.restservices.exceptions;

public class UserExistsException extends Exception {
	private static final long serialVesrionID = 1L;

	public UserExistsException(String message) {
		super(message);
	}

}
