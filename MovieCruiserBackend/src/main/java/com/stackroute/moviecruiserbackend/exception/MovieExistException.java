package com.stackroute.moviecruiserbackend.exception;

public class MovieExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieExistException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieExistException [message=" + message + "]";
	}

}
