package com.ga5000.Clinic.dtos.Patient;

import com.ga5000.Clinic.entities.enums.AgeGroup;
import com.ga5000.Clinic.entities.enums.Gender;

import java.time.LocalDate;

public record PatientDTO(Long patientId, String name, int age, LocalDate birthdate, Gender gender,
                         String email, String phoneNumber, AgeGroup ageGroup) {
}
