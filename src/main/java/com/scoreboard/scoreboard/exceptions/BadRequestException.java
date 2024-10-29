package com.scoreboard.scoreboard.exceptions;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 5635501427684750962L;

	public BadRequestException(String message) {
		super(message);
	}
}
