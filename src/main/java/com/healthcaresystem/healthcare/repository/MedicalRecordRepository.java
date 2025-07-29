package com.healthcaresystem.healthcare.repository;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);

    @Query("SELECT m FROM MedicalRecord m WHERE m.doctor.id = :doctorId")
    List<MedicalRecord> findByDoctorId(@Param("doctorId") Long doctorId);
}
