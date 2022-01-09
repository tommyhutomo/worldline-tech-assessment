package com.worldline.interview.utils;

import java.util.List;

public class DefaultApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private List<String> errors;
	
	public DefaultApiException(String message, List<String> errors) {
		super();
		this.message = message;
		this.errors = errors;
	}


	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
}
