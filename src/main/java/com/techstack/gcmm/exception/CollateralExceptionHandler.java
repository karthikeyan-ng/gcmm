package com.techstack.gcmm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler prepares error response based on the various
 * exception handler class.
 * 
 * @author Karthikeyan N
 *
 */
@ControllerAdvice
public class CollateralExceptionHandler {

	@ExceptionHandler(CalculationException.class)
	public final ResponseEntity<ExceptionResponse> handleCalculationException(final CalculationException e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAnyException(final Exception e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ExceptionResponse> handleAnyRuntimeException(final RuntimeException e) {
		ExceptionResponse response = ExceptionResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
