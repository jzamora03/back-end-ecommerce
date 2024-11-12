package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Producto;
import com.example.ecomerce.tps.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> crearProducto(@RequestBody Producto producto) {
        try {
            productoService.crearProducto(producto);

            // Crear un mapa con el mensaje de respuesta
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto registrado exitosamente!");

            return ResponseEntity.ok(response);  // Devolver como JSON
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

//    @PostMapping("/crearProducto")
//    public ResponseEntity<String> crearProducto(@RequestBody Producto producto) {
//        try{
//            productoService.crearProducto(producto);
//            return ResponseEntity.ok("Producto registrado exitosamente!");
//        }catch (IllegalArgumentException e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//      //  return ResponseEntity.ok(productoService.crearProducto(producto));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            // Devolver JSON en lugar de texto simple
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto actualizado correctamente");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
