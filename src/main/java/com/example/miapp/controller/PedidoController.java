package com.example.miapp.controller;

import com.example.miapp.model.Pedido;
import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.PedidoRepository;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository PedidoRepository;
    private final UserRepository userRepository;
    private final ProductoRepository prodRepository;

    public PedidoController(PedidoRepository PedidoRepository,
                             UserRepository userRepository,
                             ProductoRepository prodRepository) {
        this.PedidoRepository = PedidoRepository;
        this.userRepository = userRepository;
        this.prodRepository = prodRepository;
    }

    @PostMapping("/crear/{userId}")
    public ResponseEntity<Pedido> crearPedido(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido Pedido = new Pedido();
        Pedido.setUser(user);

        return ResponseEntity.ok(PedidoRepository.save(Pedido));
    }

    @PostMapping("/{pedidoId}/agregar/{productoId}")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable Long PedidoId,
            @PathVariable Long productoId) {

        Pedido Pedido = PedidoRepository.findById(PedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = prodRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Pedido.getProductos().add(producto);
        //aaaaa
        return ResponseEntity.ok(PedidoRepository.save(Pedido));
    }

    @GetMapping("/{PedidoId}")
    public ResponseEntity<List<Producto>> verProductos(@PathVariable Long PedidoId) {

        Pedido Pedido = PedidoRepository.findById(PedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return ResponseEntity.ok(Pedido.getProductos());
    }

    @DeleteMapping("/{pedidoId}/vaciar")
    public ResponseEntity<Void> vaciarPedido(@PathVariable Long PedidoId) {

        Pedido Pedido = PedidoRepository.findById(PedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Pedido.getProductos().clear();
        PedidoRepository.save(Pedido);

        return ResponseEntity.noContent().build();
    }
}