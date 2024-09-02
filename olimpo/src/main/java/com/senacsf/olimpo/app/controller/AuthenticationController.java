package com.senacsf.olimpo.app.controller;

import com.senacsf.olimpo.app.dto.LoginRequestDto;
import com.senacsf.olimpo.app.dto.LoginResponseDto;
import com.senacsf.olimpo.app.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth", method = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.PATCH})
//@CrossOrigin(origins = "/*")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    private Map<String, Object> response;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto loginRequestDto) {
        this.response = new HashMap<>();
        try {
            LoginResponseDto loginResponse = authenticationService.authenticate(loginRequestDto);
            response.put("status", "success");
            response.put("data", loginResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Credenciales invalidas o usuario no registrado");
            return ResponseEntity.status(401).body(response);
        }
    }
}
