package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los usuarios.
 *
 * Proporciona endpoints para consultar todos los usuarios registrados, así como
 * para buscar un usuario específico por su número de identificación.
 * @Author Jhoseph Zamora - jhosephzc@gmail.com
 * @Date 8 de noviembre de 2024
 */
@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint para obtener todos los usuarios registrados.
     *
     * @return Una lista con todos los usuarios almacenados en la base de datos.
     *         Devuelve un `ResponseEntity` con la lista de usuarios y un código de respuesta HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        // Obtener todos los usuarios desde el servicio
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios); // Responder con la lista de usuarios y código 200 OK
    }

    /**
     * Endpoint para obtener un usuario específico mediante su número de identificación.
     *
     * @param numeroIdentificacion El número de identificación del usuario a buscar.
     * @return El objeto `Usuario` correspondiente al número de identificación proporcionado.
     *         Devuelve un `ResponseEntity` con el usuario y un código de respuesta HTTP 200 OK si se encuentra.
     *         Si el usuario no existe, devuelve un código de respuesta HTTP 404 Not Found.
     */
    @GetMapping("/{numeroIdentificacion}")
    public ResponseEntity<Usuario> getUserByNumeroIdentificacion(@PathVariable String numeroIdentificacion) {

        Usuario usuario = usuarioService.obtenerUsuarioPorNumeroIdentificacion(numeroIdentificacion);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{numeroIdentificacion}")
    public ResponseEntity<String> updateUser(@PathVariable String numeroIdentificacion, @RequestBody Usuario updatedUser) {
        try {
            Usuario usuarioExistente = usuarioService.obtenerUsuarioPorNumeroIdentificacion(numeroIdentificacion);

            if (usuarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            usuarioExistente.setNombreCompleto(updatedUser.getNombreCompleto());
            usuarioExistente.setCargo(updatedUser.getCargo());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                usuarioExistente.setPassword(updatedUser.getPassword());
            }

            usuarioService.actualizarUsuario(usuarioExistente);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }
}
