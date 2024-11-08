package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.DetalleOrden;
import com.example.ecomerce.tps.model.Orden;
import com.example.ecomerce.tps.model.Producto;
import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.repository.OrdenRepository;
import com.example.ecomerce.tps.repository.ProductoRepository;
import com.example.ecomerce.tps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdenService {

    private Long usuarioId;
    private List<DetalleOrdenService> detalles;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Registrar una nueva orden
    public Orden crearOrden(Long usuarioId, List<DetalleOrdenService> detallesOrden) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findByNumeroIdentificacion(usuarioId.toString());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        // Crear nueva instancia de la orden
        Orden orden = new Orden();
        orden.setUsuario(usuario);
        orden.setFechaOrden(new Date());
        orden.setEstado("Pendiente");

        // Inicializar lista de detalles y calcular el total de la orden
        List<DetalleOrden> detalles = new ArrayList<>();
        Double totalOrden = 0.0;

        for (DetalleOrdenService detalleReq : detallesOrden) {
            Producto producto = productoRepository.findById(detalleReq.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            // Crear instancia del detalle de orden
            DetalleOrden detalle = new DetalleOrden();
            detalle.setOrden(orden);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleReq.getCantidad());
            detalle.setPrecio(producto.getPrecio() * detalleReq.getCantidad());
            detalles.add(detalle);
            totalOrden += detalle.getPrecio();
        }

        // Asignar detalles y total de la orden
        orden.setTotalOrden(totalOrden);
        orden.setDetalles(detalles);

        // Guardar la orden en la base de datos
        return ordenRepository.save(orden);
    }

    // Obtener todas las órdenes
    public List<Orden> obtenerTodasLasOrdenes() {
        return ordenRepository.findAll();
    }

    // Obtener una orden por ID
    public Orden obtenerOrdenPorId(Long ordenId) {
        return ordenRepository.findById(ordenId).orElse(null);
    }

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
