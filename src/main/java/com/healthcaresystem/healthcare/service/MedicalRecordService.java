package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecord createRecord(MedicalRecord record);
    List<MedicalRecord> getAllRecords();
    MedicalRecord getRecordById(Long id);
    MedicalRecord updateRecord(Long id, MedicalRecord updatedRecord);
    void deleteRecord(Long id);
}
