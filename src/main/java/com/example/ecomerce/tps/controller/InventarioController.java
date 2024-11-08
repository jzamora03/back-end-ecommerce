package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Inventario;
import com.example.ecomerce.tps.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Crear un nuevo inventario
    @PostMapping("/crearInventario")
    public ResponseEntity<Inventario> crearInventario(@RequestBody InventarioRequest inventarioRequest) {
        Inventario inventario = new Inventario();
        inventario.setCantidadEnStock(inventarioRequest.getCantidadEnStock());

        Inventario inventarioCreado = inventarioService.crearInventario(inventario, inventarioRequest.getProductoId());
        return ResponseEntity.ok(inventarioCreado);
    }

    // Consultar todos los inventarios
    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodosLosInventarios() {
        List<Inventario> inventarios = inventarioService.obtenerTodosLosInventarios();
        return ResponseEntity.ok(inventarios);
    }

    // Consultar un inventario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventarioPorId(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerInventarioPorId(id);
        return ResponseEntity.ok(inventario);
    }

    // Clase auxiliar para la solicitud de creación de inventario
    static class InventarioRequest {
        private Long productoId;
        private int cantidadEnStock;

        // Getters y Setters
        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public int getCantidadEnStock() {
            return cantidadEnStock;
        }

        public void setCantidadEnStock(int cantidadEnStock) {
            this.cantidadEnStock = cantidadEnStock;
        }
    }
}
