package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "examination")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "history")
    private String history;

    @Column(name = "internal_eye_exam")
    private String internalEyeExam;

    @Column(name = "external_eye_exam")
    private String externalEyeExam;

    @Column(name = "management")
    private String management;

    @Column(name = "recall")
    private String recall;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cx_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ps_id", referencedColumnName = "id")
    private ProfessionalStaff professionalStaff;

}
