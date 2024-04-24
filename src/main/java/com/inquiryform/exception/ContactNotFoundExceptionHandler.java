package com.inquiryform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactNotFoundExceptionHandler {
	
	 @ExceptionHandler(value = {ContactNotFoundException.class})
	    public ResponseEntity<Object> handleContactNotFoundException
	            (ContactNotFoundException ContactNotFoundException)
	    {
	        InquiryException inquiryException = new InquiryException(
	                ContactNotFoundException.getMessage(),
	                ContactNotFoundException.getCause(),
	                HttpStatus.NOT_FOUND
	        );

	        return new ResponseEntity<>(inquiryException, HttpStatus.NOT_FOUND);
	    }

}
