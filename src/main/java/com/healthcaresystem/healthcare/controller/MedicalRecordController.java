package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import com.healthcaresystem.healthcare.service.MedicalRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

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
}
