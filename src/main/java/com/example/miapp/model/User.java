package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nombreCompleto;

    private String nombreUser; 

    private String contrasenia;

    private String metodoPago = null;

    @Enumerated(EnumType.STRING)
    private Rol rol; 
    
    public enum Rol {
        ADMIN,
        CLIENTE,
        VENDEDOR,
        SOPORTE
    }
}

