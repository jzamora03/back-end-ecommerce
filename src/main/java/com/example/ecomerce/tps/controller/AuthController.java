package com.example.ecomerce.tps.controller;

import com.example.ecomerce.tps.model.Usuario;
import com.example.ecomerce.tps.service.JwtService;
import com.example.ecomerce.tps.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la autenticación y registro de usuarios.
 *
 * Proporciona endpoints para registrar nuevos usuarios y autenticar usuarios existentes,
 * generando un token JWT para el acceso seguro a otros servicios.
 *
 * @Author Jhoseph Zamora - jhosephzc@gmail.com
 * @Date 8 de noviembre de 2024
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario user) {
        try {
            usuarioService.registrarUsuario(user);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        Usuario foundUser = usuarioService.autenticarUsuario(user.getNumeroIdentificacion(), user.getPassword());

        if (foundUser != null) {
            String token = jwtService.generarToken(foundUser);
            return ResponseEntity.ok().body(new LoginResponse("Login exitoso", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }

    static class LoginResponse {
        private String message;
        private String token;


        public LoginResponse(String message, String token) {
            this.message = message;
            this.token = token;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
