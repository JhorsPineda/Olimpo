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
@Table(name = "sheet")
public class SheetEntitie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_sheet", nullable = false)
    private Long idStudentSheet;

    @Column(name = "program", nullable = false)
    private String program;

    @Column(name = "jornada", nullable = false)
    private String jornada;

    @Column(name = "phase", nullable = false)
    private String phase;
}
