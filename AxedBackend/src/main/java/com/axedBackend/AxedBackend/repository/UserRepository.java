package com.axedBackend.AxedBackend.repository;

import com.axedBackend.AxedBackend.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {
    private final Firestore db;

    public UserRepository(Firestore db) {
        this.db = db;
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("users").document(user.getId());
        ApiFuture<WriteResult> result = docRef.set(user);
        return result.get().getUpdateTime().toString();
    }

    public User getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        User user = document.toObject(User.class);
        return user;
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("users").document(user.getId());
        ApiFuture<WriteResult> result = docRef.set(user);
        return result.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        db.collection("users").document(id).delete();
        return "User deleted successfully";
    }
}
