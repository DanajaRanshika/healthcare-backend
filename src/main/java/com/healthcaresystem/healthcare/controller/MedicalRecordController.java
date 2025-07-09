package com.healthcaresystem.healthcare.controller;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import com.healthcaresystem.healthcare.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.createRecord(record);
    }

    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecord getRecordById(@PathVariable Long id) {
        return medicalRecordService.getRecordById(id);
    }

    @PutMapping("/{id}")
    public MedicalRecord updateRecord(@PathVariable Long id, @RequestBody MedicalRecord record) {
        return medicalRecordService.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
    }
}
