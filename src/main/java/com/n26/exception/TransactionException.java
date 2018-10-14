package com.n26.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RuntimeException {
	
	private static final long serialVersionUID = -6568286497092479591L;
	
	private HttpStatus status;
	public TransactionException()
	{
		super();
	}
	
	public TransactionException(HttpStatus status)
	{
		this.status = status;
	}
	
	public HttpStatus getStatus()
	{
		return status;
	}
}
