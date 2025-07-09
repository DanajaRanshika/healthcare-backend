package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import com.healthcaresystem.healthcare.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord createRecord(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    @Override
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord getRecordById(Long id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }

    @Override
    public MedicalRecord updateRecord(Long id, MedicalRecord updatedRecord) {
        MedicalRecord record = medicalRecordRepository.findById(id).orElse(null);
        if (record == null) return null;

        record.setDoctor(updatedRecord.getDoctor());
        record.setPatient(updatedRecord.getPatient());
        record.setDiagnosis(updatedRecord.getDiagnosis());
        record.setNote(updatedRecord.getNote());
        record.setDate(updatedRecord.getDate());

        return medicalRecordRepository.save(record);
    }

    @Override
    public void deleteRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}
