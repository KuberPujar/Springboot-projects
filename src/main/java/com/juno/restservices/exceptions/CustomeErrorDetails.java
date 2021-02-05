package com.juno.restservices.exceptions;

import java.util.Date;

public class CustomeErrorDetails {
private Date timeStamp;
private String message;
private String errorDetails;
public CustomeErrorDetails(Date timeStamp, String message, String errorDetails) {
	super();
	this.timeStamp = timeStamp;
	this.message = message;
	this.errorDetails = errorDetails;
}
public Date getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getErrorDetails() {
	return errorDetails;
}
public void setErrorDetails(String errorDetails) {
	this.errorDetails = errorDetails;
}


}
