package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // TODO: custom queries can be added here as needed
}
