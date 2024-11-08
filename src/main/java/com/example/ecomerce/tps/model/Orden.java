package com.example.ecomerce.tps.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordenId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "numeroIdentificacion")
    private Usuario usuario;

    private Date fechaOrden;
    private Double totalOrden;
    private String estado;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles;

    // Getters y Setters
    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Double getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(Double totalOrden) {
        this.totalOrden = totalOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
    }
}
