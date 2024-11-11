package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Orden;
import com.example.ecomerce.tps.service.OrdenService;
import com.example.ecomerce.tps.service.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Orden>> obtenerOrdenesPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Orden> ordenes = ordenService.obtenerOrdenesPorUsuario(usuarioId);
            return ResponseEntity.ok(ordenes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Clase interna para la solicitud de creación de la orden
    static class CrearOrdenRequest {
        private Long usuarioId;
        private List<DetalleOrdenService> detalles;

        // Getters y Setters
        public Long getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }

        public List<DetalleOrdenService> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<DetalleOrdenService> detalles) {
            this.detalles = detalles;
        }
    }

    // Crear una nueva orden
    @PostMapping("/crearOrden")
    public ResponseEntity<?> crearOrden(@RequestBody CrearOrdenRequest crearOrdenRequest) {
        try {
            Orden nuevaOrden = ordenService.crearOrden(crearOrdenRequest.getUsuarioId(), crearOrdenRequest.getDetalles());
            return new ResponseEntity<>(
                    new CrearOrdenResponse(nuevaOrden.getOrdenId(), "Orden creada exitosamente"),
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Clase interna para la respuesta de creación de la orden
    static class CrearOrdenResponse {
        private Long ordenId;
        private String mensaje;

        public CrearOrdenResponse(Long ordenId, String mensaje) {
            this.ordenId = ordenId;
            this.mensaje = mensaje;
        }

        // Getters y Setters
        public Long getOrdenId() {
            return ordenId;
        }

        public void setOrdenId(Long ordenId) {
            this.ordenId = ordenId;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
