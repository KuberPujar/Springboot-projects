package com.juno.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomeGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomeErrorDetails erroDtls=new CustomeErrorDetails(new Date(), "From methodInvalidException", ex.getMessage());
		return new ResponseEntity<>(erroDtls,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomeErrorDetails erroDtls=new CustomeErrorDetails(new Date(), "From HttpRequestMethodNotSupportedException -Method not allowed", ex.getMessage());
		return new ResponseEntity<>(erroDtls,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest web)
	{
		CustomeErrorDetails erroDtls=new CustomeErrorDetails(new Date(), ex.getMessage(),web.getDescription(false));
		return new ResponseEntity<>(erroDtls,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)
	{
		CustomeErrorDetails erroDtls=new CustomeErrorDetails(new Date(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(erroDtls,HttpStatus.BAD_REQUEST);
	}
}
