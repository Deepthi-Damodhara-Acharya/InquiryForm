package com.inquiryform.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inquiryform.entity.InquiryForm;
import com.inquiryform.service.InquiryService;

@RestController
//@RequestMapping("/contactus")
@CrossOrigin(origins = "http://localhost:3000")
public class InquiryController {

	@Autowired
	InquiryService inquiryService;

	@PostMapping("/addcontact")
	public ResponseEntity<String> postContact(@RequestBody InquiryForm contactForm) {
		inquiryService.addContactDetails(contactForm);
		return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully");
	}

	@GetMapping("/getContacts")
	public ResponseEntity<?> getContact() throws InterruptedException, ExecutionException {
		List<InquiryForm> list = inquiryService.getContacts();

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Contacts Found");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}

	@GetMapping("/getContact/{id}")
	public ResponseEntity<?> getContactById(@PathVariable String id) {

		Optional<InquiryForm> inquiry = inquiryService.getContactById(id);
		if (inquiry.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(inquiry);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered documentId is not present");
		}
	}

	@GetMapping
	public ResponseEntity<String> home() {
		String message="You are on homepage";
		HttpStatus status = HttpStatus.OK;
		return ResponseEntity.status(HttpStatus.OK).body(message+ "\n" +status);
	}
	
	@GetMapping("/*")
	public ResponseEntity<String> error() {
		String message="404 Not Found - The requested resource was not found on this server.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(message);
	}

//	@PutMapping("/updateContact")
//	public ResponseEntity<String> updateContact(@RequestBody Inquiry contactForm)
//	{
//		contactFormService.updateContact(contactForm);
//		return ResponseEntity.status(HttpStatus.OK).body("Contact updated successfully");
//	}
//	
//	@DeleteMapping("/deleteContact")
//	public void deleteContact(@RequestParam String id)
//	{
//		contactFormService.deleteContact(id);
//	}

}
