package com.raspberry.awards.controllers.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handleConstraintException(Exception e) {
		return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Error: Value must be unique."), HttpStatus.BAD_REQUEST);
	}
	
}
