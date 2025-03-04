package com.axedBackend.AxedBackend.service;

import com.axedBackend.AxedBackend.models.User;
import com.axedBackend.AxedBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> createUser(User user) throws ExecutionException, InterruptedException {
        Map<String, Object> response = new HashMap<>();

        // Check if user email already exists
        if (userRepository.userExistsByEmail(user.getEmail())) {
            response.put("status", 409); // Conflict
            response.put("message", "User already exists");
            return response;
        }

        String updateTime = userRepository.createUser(user);
        response.put("status", 201); // Created
        response.put("message", "User created successfully");
        response.put("updateTime", updateTime);
        return response;
    }
}
