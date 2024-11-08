package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Orden;
import com.example.ecomerce.tps.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping("/crearOrden")
    public ResponseEntity<Orden> crearOrden(@RequestBody OrdenService ordenRequest) {
        try {
            Orden nuevaOrden = ordenService.crearOrden(ordenRequest.getUsuarioId(), ordenRequest.getDetalles());
            return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodasLasOrdenes() {
        List<Orden> ordenes = ordenService.obtenerTodasLasOrdenes();
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long id) {
        Orden orden = ordenService.obtenerOrdenPorId(id);
        if (orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
