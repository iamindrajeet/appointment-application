package com.assessment.appointment.application.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * Global exception handler will handle all the necessary exceptions.
 * 
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = AppointmentNotFoundException.class)
	public ResponseEntity<Object> handleStudentNotFoundException(AppointmentNotFoundException ex) {

		String response = "The appointment details doesn't exist";
		logger.error(response + ex);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}