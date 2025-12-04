package com.example.miapp.controller;

import com.example.miapp.model.EstadoPedido;
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
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UserRepository userRepository;
    private final ProductoRepository productoRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            UserRepository userRepository,
                            ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
        this.productoRepository = productoRepository;
    }

    // Crear pedido
    @PostMapping("/crear/{userId}")
    public ResponseEntity<Pedido> crearPedido(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUser(user);
        pedido.setEstado(EstadoPedido.PENDIENTE);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    // Agregar producto al pedido
    @PostMapping("/{pedidoId}/agregar/{productoId}")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable Long pedidoId,
            @PathVariable Long productoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        pedido.getProductos().add(producto);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    // Ver productos del pedido
    @GetMapping("/{pedidoId}")
    public ResponseEntity<List<Producto>> verProductos(@PathVariable Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return ResponseEntity.ok(pedido.getProductos());
    }

    // Vaciar el pedido
    @DeleteMapping("/{pedidoId}/vaciar")
    public ResponseEntity<Void> vaciarPedido(@PathVariable Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.getProductos().clear();
        pedidoRepository.save(pedido);

        return ResponseEntity.noContent().build();
    }

    // Cambiar estado del pedido
    @PutMapping("/{pedidoId}/estado/{estado}")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long pedidoId,
            @PathVariable EstadoPedido estado) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(estado);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }
}