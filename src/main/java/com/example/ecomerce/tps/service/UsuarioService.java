package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestiona las operaciones relacionadas con la entidad Usuario.
 *
 * Proporciona métodos para registrar, autenticar, y consultar usuarios en la base de datos.
 * @Author Jhoseph Zamora - jhosephzc@gmail.com
 * @Date 8 de noviembre de 2024
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario El objeto Usuario a registrar.
     * @return El objeto Usuario registrado, incluyendo su ID generado.
     * @throws IllegalArgumentException si el número de identificación ya está registrado en la base de datos.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el número de identificación ya existe en la base de datos
        if (usuarioRepository.findByNumeroIdentificacion(usuario.getNumeroIdentificacion()) != null) {
            throw new IllegalArgumentException("El número de identificación ya está registrado");
        }

        // Encriptar la contraseña antes de guardar el usuario
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica un usuario mediante su número de identificación y contraseña.
     *
     * @param numeroIdentificacion El número de identificación del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Usuario si las credenciales son correctas; null si las credenciales no son válidas.
     */
    public Usuario autenticarUsuario(String numeroIdentificacion, String password) {
        // Buscar al usuario por su número de identificación
        Usuario usuario = usuarioRepository.findByNumeroIdentificacion(numeroIdentificacion);

        // Verificar si el usuario existe y la contraseña coincide (comparando con la contraseña encriptada)
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }

        // Retornar null si el usuario no existe o la contraseña es incorrecta
        return null;
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return Una lista de todos los objetos Usuario en la base de datos.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene un usuario por su número de identificación.
     *
     * @param numeroIdentificacion El número de identificación del usuario a buscar.
     * @return El objeto Usuario si se encuentra; null si no existe un usuario con el número de identificación dado.
     */
    public Usuario obtenerUsuarioPorNumeroIdentificacion(String numeroIdentificacion) {
        return usuarioRepository.findByNumeroIdentificacion(numeroIdentificacion);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuario.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }
}
