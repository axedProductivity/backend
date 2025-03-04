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

}
