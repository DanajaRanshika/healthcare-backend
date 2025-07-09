package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
}
