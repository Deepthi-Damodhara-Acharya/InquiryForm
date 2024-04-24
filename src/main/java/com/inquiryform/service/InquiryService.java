package com.inquiryform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.inquiryform.entity.InquiryForm;
import com.inquiryform.exception.ContactNotFoundException;

@Service
public class InquiryService {

	public void addContactDetails(InquiryForm inquiry) {
		try {
		Firestore fireStore=FirestoreClient.getFirestore();
		String documentId=inquiry.getDocumentId();
		DocumentReference docRef=fireStore.collection("InquiryForm").document(documentId);
		ApiFuture<WriteResult> apiFuture= docRef.set(inquiry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public List<InquiryForm> getContacts(){
		try {
		Firestore fireStore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> apiFuture = fireStore.collection("InquiryForm").get();
		List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
		List<InquiryForm> contactList= list.stream().map((doc) -> doc.toObject(InquiryForm.class))
				.collect(Collectors.toList());
		return contactList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public Optional<InquiryForm> getContactById(String documentId) {
		Firestore fireStore = FirestoreClient.getFirestore();
		DocumentReference docRef = fireStore.collection("InquiryForm").document(documentId);
        try {
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            System.out.println(document);
            if (document.exists()) {
                InquiryForm inquiry = document.toObject(InquiryForm.class);
                return Optional.ofNullable(inquiry);
            } else {
                throw new ContactNotFoundException("Requested document Id is not present");
                }
        } 
        catch (InterruptedException | ExecutionException e) { 
            e.printStackTrace(); 
            return Optional.empty();
        }
	}
//	public ContactUsForm updateContact(ContactUsForm contactForm) {
//		Firestore fireStore = FirestoreClient.getFirestore();
//		DocumentReference docRef= fireStore.collection("ContactForm").document(contactForm.getId());
////		ApiFuture<WriteResult> apiFuture=docRef.set(contactForm);
////		return contactForm;
//		if(docRef.getId()!=null) {
//			ApiFuture<WriteResult> apiFuture=docRef.set(contactForm);
//			return contactForm;
//		}
//		else
//		{
//			return null;
//		}
//	}
//
//
//	public void deleteContact(String id) {
//		Firestore fireStore = FirestoreClient.getFirestore();
//		DocumentReference docRef= fireStore.collection("ContactForm").document(id);
//		ApiFuture<WriteResult> apiFuture=docRef.delete();
//	}
//
}

