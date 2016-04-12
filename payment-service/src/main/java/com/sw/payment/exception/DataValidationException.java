package com.sw.payment.exception;


public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 7210533956864048027L;

	public DataValidationException(String message) {
		super(message);
	}
	
	public DataValidationException(String message, Throwable th) {
		super(message, th);
	}
}
