package com.senacsf.olimpo.app.business; // Declara el paquete al que pertenece esta clase de negocio

import com.senacsf.olimpo.app.dto.PersonDto; // Importa la clase PersonDto del paquete dto
import com.senacsf.olimpo.app.entities.PersonEntitie; // Importa la clase PersonEntitie del paquete entities
import com.senacsf.olimpo.app.entities.RolEntitie;
import com.senacsf.olimpo.app.repository.PersonRepository;
import com.senacsf.olimpo.app.service.PersonService; // Importa la clase PersonService del paquete service
import com.senacsf.olimpo.app.service.RolService;
import com.senacsf.olimpo.app.utilities.CustomException; // Importa la clase CustomException del paquete utilities
import org.modelmapper.ModelMapper; // Importa la clase ModelMapper de la biblioteca ModelMapper
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.stereotype.Component; // Importa la anotación Component de Spring

import java.util.ArrayList; // Importa la clase ArrayList de java.util para manejar colecciones
import java.util.List; // Importa la clase List de java.util para manejar listas

@Component // Marca esta clase como un componente de Spring
public class PersonBusiness { // Define una clase de negocio para manejar operaciones relacionadas con Person

    @Autowired // Inyecta automáticamente la dependencia de PersonService
    private PersonService personService; // Variable para acceder al servicio PersonService

    @Autowired
    private RolService rolService;

    @Autowired
    private PersonRepository personRepository;

    public String checkPersonExists(Long id) {
        if (id == null) {
            return "ID es nulo";
        }
        if (personExists(id)) {
            return "Persona existe";
        } else {
            return "Persona no existe";
        }
    }

    private boolean personExists(Long id) {
        return personRepository.existsById(id);
    }

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos

    // Método para obtener todas las personas
    public List<PersonDto> findAll() {
        try {
            List<PersonEntitie> personList = personService.findAll();
            if (personList.isEmpty()) {
                return new ArrayList<>();
            }

            List<PersonDto> personDtoList = new ArrayList<>();
            personList.forEach(person -> {
                // Mapear la entidad PersonEntitie a PersonDto
                PersonDto personDto = modelMapper.map(person, PersonDto.class);

                // Asignar manualmente la descripción del rol
                if (person.getRol() != null) {
                    personDto.setRolDescription(person.getRol().getDescripcion());
                }

                personDtoList.add(personDto);
            });

            return personDtoList;
        } catch (Exception e) {
            throw new CustomException("Error getting persons");
        }
    }

    // Método para obtener una persona por su ID
    public PersonDto findPersonById(Long id) {
        try {
            PersonEntitie person = personService.getById(id); // Obtener la persona por su ID usando el servicio
            if (person == null) {
                throw new CustomException("Person with id " + id + " not found"); // Lanzar una excepción si la persona no se encuentra
            }
            // Mapear la entidad a su DTO
            PersonDto personDto = modelMapper.map(person, PersonDto.class);

            // Verificar y asignar la descripción del rol si existe
            if (person.getRol() != null) {
                personDto.setRolDescription(person.getRol().getDescripcion());
            }

            return personDto; // Retornar el DTO de la persona con la descripción del rol
        } catch (Exception e) {
            throw new CustomException("Error getting person by ID"); // Lanzar una excepción personalizada en caso de error
        }
    }


    // Método para actualizar una persona
    public void update(PersonDto personDto) {
        try {
            // Mapear el DTO a la entidad PersonEntitie
            PersonEntitie person = modelMapper.map(personDto, PersonEntitie.class);
            // Guardar o actualizar la persona usando el servicio
            personService.save(person);
        } catch (Exception e) {
            throw new CustomException("Error saving person"); // Lanzar una excepción personalizada en caso de error
        }
    }

    // Método para crear una nueva persona

    private boolean existsByDocumentNumber(String documentNumber) {
        return personRepository.existsByDocumentNumber(documentNumber);
    }


    public void create(PersonDto personDto) {
        try {
            // Mapear el DTO a la entidad
            PersonEntitie person = modelMapper.map(personDto, PersonEntitie.class);

            // Verificar si el número de documento ya existe
            if (existsByDocumentNumber(person.getDocumentNumber())) {
                throw new CustomException("El número de documento " + person.getDocumentNumber() + " ya está registrado");
            }

            // Verificar si se ha proporcionado un roleId
            if (personDto.getRoleId() != null) {
                RolEntitie role = rolService.getById(personDto.getRoleId());
                if (role == null) {
                    throw new CustomException("El rol con ID " + personDto.getRoleId() + " no se encuentra");
                }
                person.setRol(role);
            }

            // Guardar la nueva persona
            personService.save(person);
        } catch (CustomException e) {
            throw e; // Relanzar la excepción personalizada
        } catch (Exception e) {
            throw new CustomException("Error al crear la persona: " + e.getMessage());
        }
    }

    // Método para eliminar una persona por su ID
    public void delete(Long personId) {
        try {
            PersonEntitie person = personService.getById(personId); // Obtener la persona por su ID usando el servicio
            if (person == null) { // Verificar si la persona no fue encontrada
                throw new CustomException("Person with id " + personId + " not found"); // Lanzar una excepción personalizada si la persona no existe
            }
            personService.delete(person); // Eliminar la persona usando el servicio
        } catch (Exception e) { // Capturar cualquier excepción
            System.err.println(e.getMessage()); // Imprimir el mensaje de error en la consola
        }
    }

}
