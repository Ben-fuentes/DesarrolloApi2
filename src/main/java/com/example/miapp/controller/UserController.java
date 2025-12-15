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
        System.out.println("INTENTO DE CREAR USUARIO:");
        System.out.println("Datos recibidos: " + user.getNombreUser() + " - " + user.getRol());

    
        user.setId(null);

        try {
            User nuevoUsuario = userRepository.save(user);
            System.out.println("USUARIO CREADO CON ÉXITO. ID: " + nuevoUsuario.getId());
            return nuevoUsuario;
        } catch (Exception e) {
            System.out.println("ERROR FATAL AL GUARDAR EN BD: " + e.getMessage());
            throw e; 
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setNombreUser(userDetails.getNombreUser());
        user.setContrasenia(userDetails.getContrasenia());
        user.setRol(userDetails.getRol());
        
        user.setNombreCompleto(userDetails.getNombreCompleto()); 
        user.setMetodoPago(userDetails.getMetodoPago());        

        return userRepository.save(user);
    }

    // BORRAR
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
