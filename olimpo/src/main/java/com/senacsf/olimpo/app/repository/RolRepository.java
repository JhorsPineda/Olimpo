package com.senacsf.olimpo.app.repository;

import com.senacsf.olimpo.app.entities.RolEntitie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<RolEntitie, Long> {
    // Método para obtener rol por ID
    Optional<RolEntitie> findById(Long id);
}
