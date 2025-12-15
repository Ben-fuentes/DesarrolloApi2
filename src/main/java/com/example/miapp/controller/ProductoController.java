package com.example.miapp.controller;

import com.example.miapp.model.Producto;
import com.example.miapp.repository.ProductoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository prodRepository;

    public ProductoController(ProductoRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    @GetMapping
    public List<Producto> getAllProductos() {
        return prodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return prodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto nuevoProducto) {
        return ResponseEntity.ok(prodRepository.save(nuevoProducto));
    }

   @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto prodDetails) {
        return prodRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(prodDetails.getNombre());
                    existente.setPrecio(prodDetails.getPrecio());
                    existente.setDescripcion(prodDetails.getDescripcion());
                    existente.setCategoria(prodDetails.getCategoria());
                    
                    existente.setImg(prodDetails.getImg()); 
                    existente.setStock(prodDetails.getStock());
                    existente.setEnOferta(prodDetails.isEnOferta());
                    existente.setPrecioAntes(prodDetails.getPrecioAntes());
                    
                    return ResponseEntity.ok(prodRepository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!prodRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prodRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
