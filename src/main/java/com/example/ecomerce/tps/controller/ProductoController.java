package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Producto;
import com.example.ecomerce.tps.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
        try{
            productoService.crearProducto(producto);
            return ResponseEntity.ok("Producto registrado exitosamente!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
      //  return ResponseEntity.ok(productoService.crearProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return productoActualizado != null ? ResponseEntity.ok("Producto actualizado correctamente") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
