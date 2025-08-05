package com.healthcaresystem.healthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRecordRequest {
    private Long doctorId;
    private Long patientId;
    private String diagnosis;
    private String note;
    private LocalDate date;
}
