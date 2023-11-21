package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "appt_date", nullable = false)
    @NonNull
    private LocalDate apptDate;

    @Column(name = "time_slot", nullable = false)
    @NonNull
    private String timeSlot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cx_id", referencedColumnName = "id")
    private Customer customer;
}
