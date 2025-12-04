package com.example.miapp.config;

import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductoRepository productoRepository, UserRepository userRepository) {
        return args -> {

            // ===================== USUARIOS =====================
            if (userRepository.count() == 0) {

                userRepository.save(new User(
                        null,
                        "Administrador General",    
                        "admin",                   
                        "admin123",                
                        null,                       
                        User.Rol.ADMIN              
                ));

                userRepository.save(new User(
                        null,
                        "Cliente de Prueba",
                        "cliente",
                        "cliente123",
                        null,
                        User.Rol.CLIENTE
                ));

                System.out.println("Usuarios cargados.");
            }

            // ===================== PRODUCTOS =====================
            if (productoRepository.count() == 0) {

                String img = "/img/white.png";

                productoRepository.save(new Producto(
                        null,
                        "Ramp Master Skate",
                        89990.0,
                        "Skate deck profesional 8.0 pulgadas",
                        img,
                        30,
                        false,
                        null,
                        Producto.Categoria.SKATE
                ));

                productoRepository.save(new Producto(
                        null,
                        "Street Cruiser Skate",
                        75500.0,
                        "Skate urbano resistente al desgaste",
                        img,
                        20,
                        false,
                        null,
                        Producto.Categoria.SKATE
                ));

                productoRepository.save(new Producto(
                        null,
                        "Turbo Roller",
                        120000.0,
                        "Patines con ruedas de PU",
                        img,
                        15,
                        true,
                        150000,
                        Producto.Categoria.ROLLER
                ));

                productoRepository.save(new Producto(
                        null,
                        "Speed Roller",
                        99000.0,
                        "Patín inline cómodo",
                        img,
                        10,
                        false,
                        null,
                        Producto.Categoria.ROLLER
                ));

                productoRepository.save(new Producto(
                        null,
                        "BMX Freestyle X",
                        45000.0,
                        "Bicicleta BMX para trucos",
                        img,
                        12,
                        false,
                        null,
                        Producto.Categoria.BMX
                ));

                productoRepository.save(new Producto(
                        null,
                        "BMX Street Pro",
                        52000.0,
                        "Cuadro reforzado para saltos",
                        img,
                        7,
                        true,
                        65000,
                        Producto.Categoria.BMX
                ));

                System.out.println("Productos cargados.");
            }

        };
    }
}