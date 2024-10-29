package com.scoreboard.scoreboard.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@ExceptionHandler({ BadRequestException.class })
	protected ResponseEntity<Object> handleBadRequestException(final BadRequestException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("statusCode", 400);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler({ NotFoundException.class })
	protected ResponseEntity<Object> handleNotFoundException(final NotFoundException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("statusCode", 404);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler({ InternalServerErrorException.class })
	protected ResponseEntity<Object> handleInternalServerErrorException(final InternalServerErrorException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("statusCode", 500);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        
        response.put("message", errors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations()
				.forEach(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
