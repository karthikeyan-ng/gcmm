package com.techstack.gcmm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This ExceptionResponse prepares whenever system behaves wrongly and the same
 * will be send back to the caller.
 * 
 * @author Karthikeyan N
 *
 */
@Getter
public class ExceptionResponse {

	private final HttpStatus status;
	private final String message;

	public ExceptionResponse(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
	}

	public static ExceptionResponse of(final String message, HttpStatus status) {
		return new ExceptionResponse(message, status);
	}

}