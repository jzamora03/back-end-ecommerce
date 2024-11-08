package com.example.ecomerce.tps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entidad que representa a un usuario en el sistema de e-commerce.
 *
 * Esta clase mapea los atributos del usuario a una tabla en la base de datos.
 * Cada usuario tiene un número de identificación único, nombre completo, contraseña,
 * y un cargo que describe su rol en el sistema.
 *
 * @Author Jhoseph Zamora - jhosephzc@gmail.com
 * @Date 8 de noviembre de 2024
 */
@Entity
public class Usuario {

    @Id
    private String numeroIdentificacion;
    private String nombreCompleto;
    private String password;
    private String cargo;

    /**
     * Obtiene el número de identificación del usuario.
     *
     * @return El número de identificación del usuario.
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Establece el número de identificación del usuario.
     *
     * @param numeroIdentificacion El número de identificación del usuario.
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombreCompleto El nombre completo del usuario.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * Nota: La contraseña se almacena encriptada en la base de datos.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario.
     *                 Se recomienda encriptarla antes de guardarla.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el cargo o rol del usuario en el sistema.
     *
     * @return El cargo del usuario (ej. Administrador, Usuario Regular, etc.).
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Establece el cargo o rol del usuario en el sistema.
     *
     * @param cargo El cargo del usuario (ej. Administrador, Usuario Regular, etc.).
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
