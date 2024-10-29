package com.scoreboard.scoreboard.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6329802027184175018L;

	public NotFoundException(String message) {
		super(message);
	}
}
