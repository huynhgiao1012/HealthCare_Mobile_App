package com.healthcareapp.healthcareappbackend.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SymptomPatientDto {
    private Long id;
    private Long symptomId;
    private Long patientId;
}
