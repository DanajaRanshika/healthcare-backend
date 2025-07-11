package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import com.healthcaresystem.healthcare.entity.User;
import com.healthcaresystem.healthcare.security.JwtUtil;
import com.healthcaresystem.healthcare.service.MedicalRecordService;
import com.healthcaresystem.healthcare.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // 🛡️ Only doctors can create records
    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.createRecord(record);
    }

    // 🛡️ Only doctors can view all records
    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordService.getAllRecords();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/{id}")
    public MedicalRecord getRecordById(@PathVariable Long id) {
        return medicalRecordService.getRecordById(id);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/{id}")
    public MedicalRecord updateRecord(@PathVariable Long id, @RequestBody MedicalRecord record) {
        return medicalRecordService.updateRecord(id, record);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
    }

    // 🛡️ Only patients can view their own records
    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/patient/{id}")
    public List<MedicalRecord> getRecordsForPatient(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtUtil.extractUsername(token);

        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isEmpty() || !optionalUser.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return medicalRecordService.getRecordsByPatientId(id);
    }
}
