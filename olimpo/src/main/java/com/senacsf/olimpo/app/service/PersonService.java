package com.senacsf.olimpo.app.service;

import com.senacsf.olimpo.app.entities.PersonEntitie;
import com.senacsf.olimpo.app.repository.PersonRepository;
import com.senacsf.olimpo.app.service.dao.Idao;
import com.senacsf.olimpo.app.utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements Idao<PersonEntitie, Long> {

    @Autowired
    private PersonRepository personRepository;

    public boolean existsByDocumentNumber(String documentNumber) {
        return personRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public List<PersonEntitie> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntitie getById(Long id) {
        Optional<PersonEntitie> person = personRepository.findById(id);
        return person.orElse(null); // Devuelve la persona si está presente, o null si no
    }

    @Override
    public void update(PersonEntitie entity) {
        if (personRepository.existsById(entity.getIdPerson())) {
            personRepository.save(entity);
        }
    }

    @Override
    public PersonEntitie save(PersonEntitie entity) {
        return personRepository.save(entity);
    }

    @Override
    public void delete(PersonEntitie entity) {
        if (personRepository.existsById(entity.getIdPerson())) {
            personRepository.delete(entity);
        }
    }

    public void create(PersonEntitie person) throws CustomException {
        // Verificar si el documento ya existe
        boolean exists = personRepository.existsByDocumentNumber(person.getDocumentNumber());
        if (exists) {
            throw new CustomException("El número de documento ya está en uso.");
        }

        // Si el documento no existe, guardar la nueva persona
        personRepository.save(person);
    }

    // Método adicional para buscar personas por nombre
    public List<PersonEntitie> findByName(String name) {
        return personRepository.findByName(name);
    }

    // Método adicional para buscar personas por documento y tipo de documento
    public Optional<PersonEntitie> findByDocumentNumberAndDocumentType(String documentNumber, PersonEntitie.DocumentType documentType) {
        return personRepository.findByDocumentNumberAndDocumentType(documentNumber, documentType);
    }
}
