package com.senacsf.olimpo.app.service;

import com.senacsf.olimpo.app.entities.RolEntitie;
import com.senacsf.olimpo.app.repository.RolRepository;
import com.senacsf.olimpo.app.utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public RolEntitie getById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new CustomException("Role with ID " + id + " not found"));
    }
}


