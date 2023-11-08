package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appt_date")
    private LocalDate apptDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cx_id", referencedColumnName = "id")
    private Customer customer;
}
