package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.Inventario;
import com.example.ecomerce.tps.model.Producto;
import com.example.ecomerce.tps.repository.InventarioRepository;
import com.example.ecomerce.tps.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo inventario
    public Inventario crearInventario(Inventario inventario, Long productoId) {
        // Buscar el producto por su ID
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        // Asignar el producto al inventario
        inventario.setProducto(producto);

        // Guardar el inventario
        return inventarioRepository.save(inventario);
    }

    // Obtener todos los inventarios
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioRepository.findAll();
    }

    // Obtener inventario por su ID
    public Inventario obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventario no encontrado"));
    }
}
