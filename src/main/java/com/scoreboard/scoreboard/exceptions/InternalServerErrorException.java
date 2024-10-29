package com.scoreboard.scoreboard.exceptions;

public class InternalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = -2147321067248471756L;

	public InternalServerErrorException(String message) {
		super(message);
	}
}
