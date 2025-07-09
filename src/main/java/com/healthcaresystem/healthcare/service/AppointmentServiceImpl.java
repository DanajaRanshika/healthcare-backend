package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.Appointment;
import com.healthcaresystem.healthcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updated) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) return null;

        appointment.setDate(updated.getDate());
        appointment.setTime(updated.getTime());
        appointment.setStatus(updated.getStatus());
        appointment.setDoctor(updated.getDoctor());
        appointment.setPatient(updated.getPatient());

        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
