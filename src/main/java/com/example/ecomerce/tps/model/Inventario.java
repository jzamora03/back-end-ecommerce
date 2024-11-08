package com.example.ecomerce.tps.model;

import jakarta.persistence.*;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventarioId;

    @OneToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "productoId")
    private Producto producto;

    private Integer cantidadEnStock;

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(Integer cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
}
