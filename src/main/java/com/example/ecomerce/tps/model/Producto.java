package com.example.ecomerce.tps.model;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String imagenUrl;
    private String productoActivo = "Activo";

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setProductoActivo(String productoActivo) {
        if (productoActivo.equalsIgnoreCase("Activo") || productoActivo.equalsIgnoreCase("Inactivo")) {
            this.productoActivo = productoActivo;
        } else {
            throw new IllegalArgumentException("El estado del producto debe ser 'Activo' o 'Inactivo'.");
        }
    }

    public String getProductoActivo() {
        return productoActivo;
    }
}
