package com.juno.restservices.exceptions;

public class UserNameNotFoundException extends Exception{
	private static final long serialVesrionID = 1L;
	public UserNameNotFoundException(String message) {
		super(message);
	}

}
