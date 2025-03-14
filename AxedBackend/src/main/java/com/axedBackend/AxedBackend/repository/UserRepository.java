package com.axedBackend.AxedBackend.repository;

import com.axedBackend.AxedBackend.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {
    private final Firestore db;

    public UserRepository(Firestore db) {
        this.db = db;
    }

    public boolean userExistsByEmail(String email) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = db.collection("users");
        Query query = usersCollection.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        return !querySnapshot.get().getDocuments().isEmpty();
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("users").document();
        ApiFuture<WriteResult> result = docRef.set(user);
        return result.get().getUpdateTime().toString();
    }

    public User getUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(User.class);
    }

    public List<User> getUserEmail(String email) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = db.collection("users");
        Query query = usersCollection.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<User> users = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            users.add(document.toObject(User.class));
        }
        return users;
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        db.collection("users").document(id).delete();
        return "User deleted successfully";
    }

}
