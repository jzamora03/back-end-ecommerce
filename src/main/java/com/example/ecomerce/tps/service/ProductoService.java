package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.Producto;
import com.example.ecomerce.tps.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerProductosActivos() {
        return productoRepository.findByProductoActivo("Activo");
    }

    public Producto crearProducto(Producto producto) {
        if (producto.getProductoActivo() == null ||
                (!producto.getProductoActivo().equalsIgnoreCase("Activo") && !producto.getProductoActivo().equalsIgnoreCase("Inactivo"))) {
            producto.setProductoActivo("Activo");
        }
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = obtenerProductoPorId(id);
        if (productoExistente != null) {
            productoExistente.setNombreProducto(producto.getNombreProducto());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setCategoria(producto.getCategoria());
            productoExistente.setImagenUrl(producto.getImagenUrl());
            productoExistente.setProductoActivo(producto.getProductoActivo());
            return productoRepository.save(productoExistente);
        }
        return null;
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }


}
