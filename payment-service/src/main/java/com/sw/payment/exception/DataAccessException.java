package com.sw.payment.exception;

public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 6374721679518990830L;

	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, Throwable th) {
		super(message, th);
	}

}
