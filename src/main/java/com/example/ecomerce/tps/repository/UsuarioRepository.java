package com.example.ecomerce.tps.repository;

import com.example.ecomerce.tps.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Buscar un usuario por su número de identificación
    Usuario findByNumeroIdentificacion(String numeroIdentificacion);
}
