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
                        "Efectivo",
                        User.Rol.CLIENTE
                ));

                userRepository.save(new User(
                        null,
                        "Vendedor Tienda",
                        "vendedor",
                        "vendedor123",
                        null,
                        User.Rol.VENDEDOR
                ));

                userRepository.save(new User(
                        null,
                        "Soporte Técnico",
                        "soporte",
                        "soporte123",
                        null,
                        User.Rol.SOPORTE
                ));

                System.out.println("--- Usuarios de prueba cargados (Admin, Cliente, Vendedor, Soporte) ---");
            }

            if (productoRepository.count() == 0) {

                productoRepository.save(new Producto(
                        null,
                        "Ramp Master Skate",
                        89990.0,
                        "Skate deck profesional 8.0 pulgadas, madera de arce canadiense.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/skatedeckpro1.webp",
                        30,
                        false,
                        null,
                        Producto.Categoria.SKATE
                ));

                productoRepository.save(new Producto(
                        null,
                        "Street Cruiser Skate",
                        75500.0,
                        "Skate urbano resistente al desgaste, ideal para calle.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/skatedeck2.jpg",
                        20,
                        false,
                        null,
                        Producto.Categoria.SKATE
                ));

                productoRepository.save(new Producto(
                        null,
                        "Turbo Roller",
                        120000.0,
                        "Patines con ruedas de PU de alta velocidad y rodamientos ABEC-7.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/roller1.jpg",
                        15,
                        true,
                        150000,
                        Producto.Categoria.ROLLER
                ));

                productoRepository.save(new Producto(
                        null,
                        "Speed Roller",
                        99000.0,
                        "Patín inline cómodo y ajustable, bota ergonómica.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/roller3.webp",
                        10,
                        false,
                        null,
                        Producto.Categoria.ROLLER
                ));

                productoRepository.save(new Producto(
                        null,
                        "BMX Freestyle X",
                        245000.0, 
                        "Bicicleta BMX diseñada para trucos y rampas, rotor 360.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/bmx1.webp",
                        12,
                        false,
                        null,
                        Producto.Categoria.BMX
                ));

                productoRepository.save(new Producto(
                        null,
                        "BMX Street Pro",
                        252000.0, 
                        "Cuadro reforzado de acero cromoly, ideal para grindar.",
                        "https://raw.githubusercontent.com/alan-flores1/MovilWithBenyi2.0/0183b9638272c8a208e3d737fc5ae849d202bfd4/app/src/main/res/drawable/bmx2.webp",
                        7,
                        true, 
                        300000,
                        Producto.Categoria.BMX
                ));

                System.out.println("--- Productos con imágenes cargados ---");
            }
        };
    }
}
