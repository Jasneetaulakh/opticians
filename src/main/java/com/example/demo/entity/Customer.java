package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @NonNull
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    @Past(message = "Date must be in the past")
    @NonNull
    private LocalDate dateOfBirth;

    @Column(name = "postcode")
    @NonNull
    private String postcode;

    @Column(name = "door_number")
    @NonNull
    private String doorNumber;

    @Column(name = "phoneNumber", nullable = false)
    @NonNull
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Examination> examinations;

}
