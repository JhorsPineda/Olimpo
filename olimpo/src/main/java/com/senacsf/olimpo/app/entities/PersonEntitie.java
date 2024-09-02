package com.senacsf.olimpo.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "person")
public class PersonEntitie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long idPerson;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "cellular_number")
    private String cellularNumber;

    @Column(name = "training_center")
    private String trainingCenter;

    @ManyToOne
    @JoinColumn(name = "fk_id_rol")
    private RolEntitie rol;

    public enum DocumentType {
        TI("Tarjeta Identidad"),
        CC("Cedula Ciudadania"),
        Pasaporte("Pasaporte"),
        CE("Cedula Extrajera");

        private final String value;

        DocumentType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
