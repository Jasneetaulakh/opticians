package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professional_staff")
public class ProfessionalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "staff_pin", nullable = false, unique = true)
//    @NotBlank(message = "Pin cannot be blank")
    private String staffPin;

    @Column(name = "goc_num", nullable = false)
//    @NotBlank(message = "GOC Number cannot be blank")
    private String gocNum;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
