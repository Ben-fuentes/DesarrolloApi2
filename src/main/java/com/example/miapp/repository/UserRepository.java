package com.example.miapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.miapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
