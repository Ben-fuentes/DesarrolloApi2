package com.example.miapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.miapp.model.User;
import com.example.miapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User bookDetails) {
        User book = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setNombreUser(bookDetails.getNombreUser());
        book.setContrasenia(bookDetails.getContrasenia());
        book.setRol(bookDetails.getRol());

        return userRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}