package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // For demonstration, pretend to authenticate
        if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
