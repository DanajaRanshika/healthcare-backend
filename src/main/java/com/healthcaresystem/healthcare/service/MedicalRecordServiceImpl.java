package com.healthcaresystem.healthcare.service;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import com.healthcaresystem.healthcare.entity.User;
import com.healthcaresystem.healthcare.repository.MedicalRecordRepository;
import com.healthcaresystem.healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MedicalRecord createRecord(Long doctorId, Long patientId, String diagnosis, String note, LocalDate date) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        MedicalRecord record = new MedicalRecord();
        record.setDoctor(doctor);
        record.setPatient(patient);
        record.setDiagnosis(diagnosis);
        record.setNote(note);
        record.setDate(date);

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

    // ✅ Only return records for the specific patient
    @Override
    public List<MedicalRecord> getRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
    @Override
    public List<MedicalRecord> getRecordsByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }
}
