package com.healthcaresystem.healthcare.repository;

import com.healthcaresystem.healthcare.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
