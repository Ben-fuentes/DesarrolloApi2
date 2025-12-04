package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nombre; 
    private Double precio; 
    private String descripcion;
    private String img;
    private Number stock;
    private boolean enOferta = false;
    private Number precioAntes = null;

    @Enumerated(EnumType.STRING)

    private Categoria categoria;  
    
    public enum Categoria {
        BMX,
        ROLLER,
        SKATE
    };
    
}

