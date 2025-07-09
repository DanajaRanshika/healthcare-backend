package com.healthcaresystem.healthcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User patient;

    private LocalDate date;
    private LocalTime time;
    private String status;
}
