package com.inquiryform.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InquiryException {
	
	private  String message;
    private  Throwable throwable;
    private  HttpStatus httpStatus;
    
	public InquiryException(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}
    
    

}