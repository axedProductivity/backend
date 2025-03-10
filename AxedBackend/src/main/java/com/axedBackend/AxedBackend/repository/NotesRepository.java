package com.axedBackend.AxedBackend.repository;

import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;
import com.google.cloud.firestore.Firestore;
import com.axedBackend.AxedBackend.models.Notes;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Repository
public class NotesRepository {
    private final Firestore db;

    public NotesRepository(Firestore db) {
        this.db = db;
    }

    public Map<String, Object> createNote(Notes note) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("notes").document();
        ApiFuture<WriteResult> result = docRef.set(note);
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", result.get().getUpdateTime().toString());
        response.put("message", "Note created successfully");
        return response;
    }

    public Map<String, Object> getNotesByUserId(String userId) throws ExecutionException, InterruptedException {
        CollectionReference notesCollection = db.collection("notes");
        List<QueryDocumentSnapshot> querySnapshot = notesCollection.whereEqualTo("userId", userId).get().get()
                .getDocuments();
        List<Notes> notes = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot) {
            notes.add(document.toObject(Notes.class));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("notes", notes);
        response.put("count", notes.size());
        return response;
    }

    public Map<String, Object> updateNote(String id, Notes note) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("notes").document(id);
        ApiFuture<WriteResult> result = docRef.set(note);
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", result.get().getUpdateTime().toString());
        response.put("message", "Note updated successfully");
        return response;
    }

    public Map<String, Object> deleteNote(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("notes").document(id);
        ApiFuture<WriteResult> result = docRef.delete();
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", result.get().getUpdateTime().toString());
        response.put("message", "Note deleted successfully");
        return response;
    }

    public Map<String, Object> toggleFavorite(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("notes").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Notes note = document.toObject(Notes.class);
        Map<String, Object> response = new HashMap<>();

        if (note != null) {
            note.setFavorite(!note.isFavorite());
            ApiFuture<WriteResult> result = docRef.set(note);
            response.put("timestamp", result.get().getUpdateTime().toString());
            response.put("message", "Favorite status toggled successfully");
            response.put("isFavorite", note.isFavorite());
        } else {
            response.put("message", "Note not found");
            response.put("error", true);
        }
        return response;
    }

}
