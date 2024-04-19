package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.User;
import com.example.Rowdyback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> foundUserOptional = userRepository.findByUsername(user.getUsername());

        if (!foundUserOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Error: No user found with username: " + user.getUsername());
        }

        User foundUser = foundUserOptional.get();

        // Assuming you have a method to check passwords that returns a boolean
        if (!checkPassword(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("Error: Incorrect password");
        }

        return ResponseEntity.ok().body("User logged in successfully");
    }

    private boolean checkPassword(String providedPassword, String storedPassword) {
        // This should be replaced with a proper password check, possibly using BCrypt or another secure method
        return providedPassword.equals(storedPassword);
    }
}
