package com.inquiryform.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.google.firebase.database.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class InquiryForm {
	
	private static Long sequenceNumber=0L; 
	
	private String documentId;
	private String name;
	private String email;
	@NotNull
	private Long mobile;
	private String message;
	
	public InquiryForm() {
		this.documentId=generateDocumentId();
	}

	private String generateDocumentId()
	{
		String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String sequencePart=String.format("%05d", getNextSequenceNumber());;
        return "INQ" + datePart + sequencePart ;
	}
	
	private synchronized Long getNextSequenceNumber()
	{
		return sequenceNumber++;
	}
}

