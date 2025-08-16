package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.entity.Appointment;
import com.healthcaresystem.healthcare.service.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/patient/{id}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long id) {
        return appointmentService.getAppointmentsByPatientId(id);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long id) {
        return appointmentService.getAppointmentsByDoctorId(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointmentStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newStatus = request.get("status");
        return appointmentService.updateAppointmentStatus(id, newStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
