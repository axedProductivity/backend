package com.axedBackend.AxedBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class Default {

    @GetMapping("/hello")
    public HashMap<String, String> hello() {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Hello, World!");
        return response;
    }
}
