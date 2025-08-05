package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecord createRecord(Long doctorId, Long patientId, String diagnosis, String note, LocalDate date);
    List<MedicalRecord> getAllRecords();
    MedicalRecord getRecordById(Long id);
    MedicalRecord updateRecord(Long id, MedicalRecord updatedRecord);
    void deleteRecord(Long id);
    List<MedicalRecord> getRecordsByPatientId(Long patientId);
    List<MedicalRecord> getRecordsByDoctorId(Long doctorId);
}