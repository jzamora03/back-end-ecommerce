package com.example.ecomerce.tps.service;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa la interfaz `UserDetailsService` de Spring Security.
 *
 * Este servicio es utilizado por Spring Security para autenticar usuarios y proporcionar
 * los detalles necesarios para la seguridad basada en roles y permisos.
 * @Author Jhoseph Zamora - jhosephzc@gmail.com
 * @Date 8 de noviembre de 2024
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga los detalles del usuario mediante su número de identificación.
     *
     * @param username El número de identificación del usuario.
     * @return Un objeto `UserDetails` que contiene los detalles del usuario para la autenticación.
     * @throws UsernameNotFoundException si no se encuentra el usuario con el número de identificación dado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByNumeroIdentificacion(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getNombreCompleto())
                .password(user.getPassword())
                .passwordEncoder(s -> s)
                .build();
    }
}
