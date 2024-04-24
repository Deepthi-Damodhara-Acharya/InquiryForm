package com.inquiryform.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConfig {
	
	@PostConstruct
	public void configureFirebseConnection() throws IOException
	{
		File file=ResourceUtils.getFile("classpath:config/springboot-firebase-demo-da98f-firebase-adminsdk-sgwh1-5fa5755cda.json");
		FileInputStream serviceAccount =
				new FileInputStream(file);

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

				FirebaseApp.initializeApp(options);
	}

}
