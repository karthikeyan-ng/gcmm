package com.techstack.gcmm.exception;

/**
 * This exception will be thrown whenever system behaves differently during
 * calculations.
 * 
 * @author Karthikeyan N
 *
 */
public class CalculationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CalculationException(String message) {
		super(message);
	}

}
