package com.senacsf.olimpo.app.controller;

import com.senacsf.olimpo.app.business.PersonBusiness;
import com.senacsf.olimpo.app.dto.PersonDto;
import com.senacsf.olimpo.app.entities.PersonEntitie;
import com.senacsf.olimpo.app.service.PersonService;
import com.senacsf.olimpo.app.utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonBusiness personBusiness;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper; // Inyectar ModelMapper

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        try {
            List<PersonDto> persons = personBusiness.findAll();
            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long personId) {
        try {
            PersonDto personDto = personBusiness.findPersonById(personId);
            return ResponseEntity.ok(personDto);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
        try {
            // Convertir PersonDto a PersonEntitie
            PersonEntitie personEntitie = modelMapper.map(personDto, PersonEntitie.class);

            // Crear la persona usando el servicio
            personService.create(personEntitie);

            return ResponseEntity.ok("Persona creada exitosamente");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la persona: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public void updatePerson(@RequestBody PersonDto personDto) {
        personBusiness.update(personDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable("id") Long personId) {
        personBusiness.delete(personId);
    }
}
