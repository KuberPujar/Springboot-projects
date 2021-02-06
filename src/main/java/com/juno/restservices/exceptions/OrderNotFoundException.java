package com.juno.restservices.exceptions;

public class OrderNotFoundException extends Exception {
	private final static Long serialVersioId = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
