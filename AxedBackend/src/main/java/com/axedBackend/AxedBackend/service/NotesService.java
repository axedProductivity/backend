package com.axedBackend.AxedBackend.service;

import com.axedBackend.AxedBackend.models.Notes;
import com.axedBackend.AxedBackend.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

@Service
public class NotesService {
    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Map<String, Object> createNote(Notes note) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> noteId = notesRepository.createNote(note);
        response.put("status", 201);
        response.put("message", "Note created successfully");
        response.put("noteId", noteId.get("id"));
        return response;
    }

    public Map<String, Object> getNotesByUserId(String userId) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> notes = notesRepository.getNotesByUserId(userId);
        System.out.println(notes);
        if (notes == null) {
            notes = new HashMap<>();
            notes.put("notes", new ArrayList<>());
            notes.put("count", 0);
        }
        response.put("status", 200);
        response.put("message", "Notes retrieved successfully");
        response.put("notes", notes);
        return response;
    }

    public Map<String, Object> updateNote(String id, Notes note) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> updatedNote = notesRepository.updateNote(id, note);
        response.put("status", 200);
        response.put("message", "Note updated successfully");
        response.put("note", updatedNote);
        return response;
    }

    public Map<String, Object> deleteNote(String id) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> deletedNote = notesRepository.deleteNote(id);
        response.put("status", 200);
        response.put("message", "Note deleted successfully");
        return response;
    }

    public Map<String, Object> toggleFavorite(String id) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> updatedNote = notesRepository.toggleFavorite(id);
        response.put("status", 200);
        response.put("message", "Note updated successfully");
        response.put("note", updatedNote);
        return response;
    }

    public Map<String, Object> getNoteById(String id) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> note = notesRepository.getNoteById(id);
        response.put("status", 200);
        response.put("message", "Note retrieved successfully");
        response.put("note", note);
        return response;
    }
}
