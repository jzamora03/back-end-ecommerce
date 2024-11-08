package com.example.ecomerce.tps.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ecomerce.tps.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // Usa una clave secreta consistente entre la generación y validación
    private static final String SECRET_KEY = "a5feed2e0399567e3c706c09b774a22c9469205d179a327c3c30e9751c7b0c18";

    // Generar el token JWT para un usuario
    public String generarToken(Usuario usuario) {
        try {
            long expirationTime = 1000 * 60 * 60 * 24 * 10;

            return JWT.create()
                    .withSubject(usuario.getNumeroIdentificacion())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            System.out.println("Error al generar el token: " + e.getMessage());
            throw new RuntimeException("Error al generar el token JWT");
        }
    }

    // Validar un token JWT
    public boolean validarToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            Date expirationDate = decodedJWT.getExpiresAt();
            return expirationDate.after(new Date());
        } catch (Exception e) {
            System.out.println("Error al validar el token: " + e.getMessage());
            return false;
        }
    }

    // Obtener el nombre de usuario desde el token
    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }
}
