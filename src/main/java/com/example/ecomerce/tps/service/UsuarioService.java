package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {

        if (usuarioRepository.findByNumeroIdentificacion(usuario.getNumeroIdentificacion()) != null) {
            throw new IllegalArgumentException("El número de identificación ya está registrado");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticarUsuario(String numeroIdentificacion, String password) {

        Usuario usuario = usuarioRepository.findByNumeroIdentificacion(numeroIdentificacion);

        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }

        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

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
