package com.senacsf.olimpo.app.dto;


import lombok.Data;
import com.senacsf.olimpo.app.entities.PersonEntitie.DocumentType;


@Data
public class LoginRequestDto {
    private DocumentType documentType;
    private String documentNumber;
    private String password;
}
