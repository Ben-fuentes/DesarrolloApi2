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
    private Integer stock;
    private boolean enOferta = false;
    private Integer precioAntes = null;

    // CAMBIO IMPORTANTE:
    // Borramos @Enumerated y el public enum.
    // Ahora es simplemente un String.
    private String categoria; 
}
