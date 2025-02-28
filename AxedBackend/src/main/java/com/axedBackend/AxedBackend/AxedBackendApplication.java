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
import org.springframework.http.ResponseEntity;
import com.axedBackend.AxedBackend.controller.UserController;

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
		public ResponseEntity<String> testFirebase() {
			try {
				ApiFuture<DocumentSnapshot> future = firestore.collection("test").document("test").get();
				DocumentSnapshot document = future.get();
				if (document.exists()) {
					return ResponseEntity.ok("Firebase connection successful! Data: " + document.getData());
				}
				return ResponseEntity.ok("Firebase connection successful! App name: " + firebaseApp.getName());
			} catch (InterruptedException | ExecutionException e) {
				return ResponseEntity.status(500).body("Firebase operation failed");
			}
		}
	}
}
