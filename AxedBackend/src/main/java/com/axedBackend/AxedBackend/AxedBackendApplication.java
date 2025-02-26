package com.axedBackend.AxedBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class AxedBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxedBackendApplication.class, args);
	}

	@RestController
	@RequestMapping("/api")
	public class HelloWorldController {
		private final Firestore firestore;
		private final FirebaseApp firebaseApp;

		@Autowired
		public HelloWorldController(Firestore firestore, FirebaseApp firebaseApp) {
			this.firestore = firestore;
			this.firebaseApp = firebaseApp;
		}

		@GetMapping("/hello")
		public String sayHello() {
			return "Hello World";
		}

		@GetMapping("/test-firebase")
		public String testFirebase() {
			try {
				// Simple test to verify Firestore is working
				ApiFuture<DocumentSnapshot> future = firestore.collection("test").document("test").get();
				future.get(); // Wait for response
				System.out.println(firebaseApp);
				return "Firebase connection successful! App name: " + firebaseApp.getName();
			} catch (InterruptedException | ExecutionException e) {
				return "Firebase operation failed: " + e.getMessage();
			}
		}
	}
}
