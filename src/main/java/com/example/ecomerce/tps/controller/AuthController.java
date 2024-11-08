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

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param user El objeto `Usuario` con la información del nuevo usuario.
     * @return Una respuesta HTTP 200 OK si el registro es exitoso,
     *         o HTTP 409 CONFLICT si el número de identificación ya está registrado.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario user) {
        try {
            usuarioService.registrarUsuario(user);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (IllegalArgumentException e) {
            // Devuelve HTTP 409 CONFLICT si el número de identificación ya está registrado
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Endpoint para autenticar un usuario.
     *
     * @param user El objeto `Usuario` con el número de identificación y la contraseña del usuario.
     * @return Una respuesta HTTP 200 OK si las credenciales son correctas, que incluye un token JWT.
     *         Devuelve HTTP 401 UNAUTHORIZED si las credenciales son incorrectas.
     */
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

    /**
     * Clase interna para representar la respuesta del login que incluye el mensaje y el token.
     */
    static class LoginResponse {
        private String message;
        private String token;

        /**
         * Constructor de la clase LoginResponse.
         *
         * @param message El mensaje de respuesta.
         * @param token El token JWT generado.
         */
        public LoginResponse(String message, String token) {
            this.message = message;
            this.token = token;
        }

        /**
         * Obtiene el mensaje de la respuesta.
         *
         * @return El mensaje de la respuesta.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Establece el mensaje de la respuesta.
         *
         * @param message El mensaje de la respuesta.
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * Obtiene el token JWT de la respuesta.
         *
         * @return El token JWT generado.
         */
        public String getToken() {
            return token;
        }

        /**
         * Establece el token JWT de la respuesta.
         *
         * @param token El token JWT generado.
         */
        public void setToken(String token) {
            this.token = token;
        }
    }
}
