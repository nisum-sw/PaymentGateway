package com.sw.payment.exception;

public class DataIntegrityViolationException extends DataAccessException {

	private static final long serialVersionUID = -8765615587539164024L;

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	
	public DataIntegrityViolationException(String message, Exception ex) {
		super(message, ex);
	}

}
