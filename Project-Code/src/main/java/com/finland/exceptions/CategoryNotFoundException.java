package com.finland.exceptions;

public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * @return errorMessage of String Type
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage type set into errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CategoryNotFoundException() {
		super();
	}

	/**
	 * @param errorMessage
	 * @description
	 */
	public CategoryNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}