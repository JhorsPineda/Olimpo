package com.senacsf.olimpo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.senacsf.olimpo.app.entities.PersonEntitie.DocumentType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long idPerson;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private String cellularNumber;
    private String trainingCenter;
    private String rolDescription;
    private Long roleId;

    // No incluimos la contraseña por seguridad en el DTO
}
