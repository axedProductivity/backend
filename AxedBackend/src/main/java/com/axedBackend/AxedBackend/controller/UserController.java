package com.axedBackend.AxedBackend.controller;

import com.axedBackend.AxedBackend.models.User;
import com.axedBackend.AxedBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        try {
            Map<String, Object> response = userService.createUser(user);
            return ResponseEntity.status((Integer) response.get("status")).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // Example protected endpoint
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        String uid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("uid", uid);
        response.put("message", "Authenticated user");
        return ResponseEntity.ok(response);
    }
}