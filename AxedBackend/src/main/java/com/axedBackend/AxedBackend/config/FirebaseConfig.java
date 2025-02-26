package com.axedBackend.AxedBackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    private FirebaseApp firebaseApp;
    private Firestore firestoreInstance;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ClassPathResource("firebase-service-account.json").getInputStream());

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        } else {
            firebaseApp = FirebaseApp.getInstance();
        }
        return firebaseApp;
    }

    @Bean
    @DependsOn("firebaseApp")
    public Firestore firestore() throws IOException {
        if (firestoreInstance == null) {
            firestoreInstance = FirestoreClient.getFirestore();
        }
        return firestoreInstance;
    }

    @PreDestroy
    public void cleanup() {
        if (firebaseApp != null) {
            firebaseApp.delete();
        }
    }
}