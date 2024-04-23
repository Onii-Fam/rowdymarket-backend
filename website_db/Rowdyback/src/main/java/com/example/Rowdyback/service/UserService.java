package com.example.Rowdyback.service;

import com.example.Rowdyback.model.User;
import com.example.Rowdyback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update user details
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            // Update password if provided
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(updatedUser.getPassword());
            }
            // Save and return updated user
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found!");
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        if (userId == null || userId == 0) {
            return Optional.empty(); // Avoid querying for invalid IDs
        }
        return userRepository.findById(userId);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
