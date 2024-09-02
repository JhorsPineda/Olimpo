package com.senacsf.olimpo.app.service;

import com.senacsf.olimpo.app.dto.LoginRequestDto;
import com.senacsf.olimpo.app.dto.LoginResponseDto;
import com.senacsf.olimpo.app.entities.PersonEntitie;
import com.senacsf.olimpo.app.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) throws Exception {
        logger.info("Attempting to authenticate user with document number: {}", loginRequestDto.getDocumentNumber());

        PersonEntitie person = personRepository.findByDocumentNumberAndDocumentType(
                loginRequestDto.getDocumentNumber(),
                loginRequestDto.getDocumentType()
        ).orElseThrow(() -> new Exception("User not found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), person.getPassword())) {
            logger.warn("Invalid credentials for user with document number: {}", loginRequestDto.getDocumentNumber());
            throw new Exception("Invalid credentials");
        }

        logger.info("User authenticated successfully: {}", loginRequestDto.getDocumentNumber());

        LoginResponseDto response = new LoginResponseDto();
        response.setIdPerson(person.getIdPerson());
        response.setName(person.getName());
        response.setLastName(person.getLastName());
        response.setDocumentNumber(person.getDocumentNumber());
        response.setRol(person.getRol().getDescripcion());

        // Determina la URL de redirección basada en el rol del usuario
        if ("ADMIN".equalsIgnoreCase(person.getRol().getDescripcion())) {
            response.setRedirectUrl("http://aquiles-app-url/admin-dashboard");
        } else if ("Aprendiz".equalsIgnoreCase(person.getRol().getDescripcion())) {
            response.setRedirectUrl("http://localhost:3000/");
        } else if ("Instructor".equalsIgnoreCase(person.getRol().getDescripcion())) {
            response.setRedirectUrl("http://aquiles-app-url/instructor-dashboard");
        } else if ("Instructor Técnico".equalsIgnoreCase(person.getRol().getDescripcion())) {
            response.setRedirectUrl("http://aquiles-app-url/instructor-tecnico-dashboard");
        } else {
            response.setRedirectUrl("http://aquiles-app-url/default-dashboard");
        }

        return response;
    }
}
