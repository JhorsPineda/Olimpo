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
@Table(name = "apprentice")
public class ApprenticeEntitie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apprentice", nullable = false)
    private Long idApprentice;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name = "fk_id_person", nullable = false)
    private PersonEntitie person;

    @ManyToOne
    @JoinColumn(name = "fk_id_student_sheet", nullable = false)
    private SheetEntitie studentSheet;

    public enum State {
        En_Formación, Condicionado, En_Plan_De_Mejora, Retiro_Voluntario, Cancelado, Traslado, Comite
    }
}
