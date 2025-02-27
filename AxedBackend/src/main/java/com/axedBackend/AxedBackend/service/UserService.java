package com.axedBackend.AxedBackend.service;

import com.axedBackend.AxedBackend.models.User;
import com.axedBackend.AxedBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        return userRepository.createUser(user);
    }

    public User getUser(String id) throws ExecutionException, InterruptedException {
        return userRepository.getUser(id);
    }
}
