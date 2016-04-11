package com.sw.payment.exception;


public class FirstDataException extends RuntimeException {

	private static final long serialVersionUID = 7210533956864048027L;

	public FirstDataException(String message) {
		super(message);
	}
	public FirstDataException(Throwable th) {
		super(th);
	}
	
	public FirstDataException(String message, Throwable th) {
		super(message, th);
	}
}
