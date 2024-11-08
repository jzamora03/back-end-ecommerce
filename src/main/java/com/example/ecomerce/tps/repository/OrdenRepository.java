package com.example.ecomerce.tps.repository;

import com.example.ecomerce.tps.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
