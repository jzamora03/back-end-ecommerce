package com.example.ecomerce.tps.model;

import jakarta.persistence.*;

@Entity
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleOrdenId;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;
    private double precio;

    // Getters y Setters
    public Long getDetalleOrdenId() {
        return detalleOrdenId;
    }

    public void setDetalleOrdenId(Long detalleOrdenId) {
        this.detalleOrdenId = detalleOrdenId;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
