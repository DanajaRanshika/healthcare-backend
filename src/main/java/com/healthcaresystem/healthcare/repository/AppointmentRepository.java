package com.healthcaresystem.healthcare.repository;

import com.healthcaresystem.healthcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
