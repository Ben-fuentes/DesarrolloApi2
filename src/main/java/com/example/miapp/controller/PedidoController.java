package com.example.miapp.controller;

import com.example.miapp.model.Pedido;
import com.example.miapp.model.Pedido.EstadoPedido;
import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.PedidoRepository;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/crear/{userId}")
    public ResponseEntity<Pedido> crearPedido(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUser(user);
        pedido.setEstado(EstadoPedido.PENDIENTE);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    @PostMapping("/{pedidoId}/agregar/{productoId}")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable Long pedidoId,
            @PathVariable Long productoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        pedido.getProductos().add(producto);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<List<Producto>> verProductos(@PathVariable Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        return ResponseEntity.ok(pedido.getProductos());
    }

    @DeleteMapping("/{pedidoId}/vaciar")
    public ResponseEntity<Void> vaciarPedido(@PathVariable Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        pedido.getProductos().clear();
        pedidoRepository.save(pedido);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pedidoId}/estado/{estado}")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long pedidoId,
            @PathVariable String estado) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        EstadoPedido estadoEnum;
        try {
            estadoEnum = EstadoPedido.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado inválido");
        }

        pedido.setEstado(estadoEnum);
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }
}