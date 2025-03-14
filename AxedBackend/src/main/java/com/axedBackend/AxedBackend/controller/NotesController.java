package com.axedBackend.AxedBackend.controller;

import com.axedBackend.AxedBackend.models.Notes;
import com.axedBackend.AxedBackend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
    private final NotesService notesService;
    private static final Logger logger = LoggerFactory.getLogger(NotesController.class);

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createNote(@RequestBody Notes note) {
        try {
            logger.info("Creating note: {}", note.getTitle());
            Map<String, Object> response = notesService.createNote(note);
            logger.info("Note created successfully with ID: {}", response.get("noteId"));
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            logger.error("Error creating note: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getNotesByUserId(@PathVariable String userId) {
        try {
            logger.info("Getting notes for user: {}", userId);
            Map<String, Object> response = notesService.getNotesByUserId(userId);
            logger.info("Retrieved {} notes for user {}",
                    ((Map<String, Object>) response.get("notes")).get("count"), userId);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            logger.error("Error getting notes for user {}: {}", userId, e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getNoteById(@PathVariable String id) {
        try {
            logger.info("Getting note by ID: {}", id);
            Map<String, Object> response = notesService.getNoteById(id);
            logger.info("Note retrieved successfully: {}", id);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            logger.error("Error getting note by ID {}: {}", id, e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateNote(@PathVariable String id, @RequestBody Notes note) {
        try {
            Map<String, Object> response = notesService.updateNote(id, note);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNote(@PathVariable String id) {
        try {
            Map<String, Object> response = notesService.deleteNote(id);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/{id}/favorite")
    public ResponseEntity<Map<String, Object>> toggleFavorite(@PathVariable String id) {
        try {
            Map<String, Object> response = notesService.toggleFavorite(id);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
