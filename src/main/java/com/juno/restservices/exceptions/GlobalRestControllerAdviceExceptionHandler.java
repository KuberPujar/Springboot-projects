package com.juno.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public CustomeErrorDetails userNotFoundException(UserNameNotFoundException ex) {
		return new CustomeErrorDetails(new Date(), "From @RestControllerAdvice - Not found", ex.getMessage());
	}
}
