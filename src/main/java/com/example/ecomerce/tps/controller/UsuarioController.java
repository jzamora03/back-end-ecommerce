package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

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
