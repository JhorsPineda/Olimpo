package com.senacsf.olimpo.app.repository;

import com.senacsf.olimpo.app.entities.PersonEntitie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.senacsf.olimpo.app.entities.PersonEntitie.DocumentType;

public interface PersonRepository extends JpaRepository<PersonEntitie, Long> {
    Optional<PersonEntitie> findByDocumentNumberAndDocumentType(String documentNumber, DocumentType documentType);

    // Método para consultar personas por nombre
    List<PersonEntitie> findByName(String name);

    // Define el método para verificar la existencia por número de documento
    boolean existsByDocumentNumber(String documentNumber);
}
