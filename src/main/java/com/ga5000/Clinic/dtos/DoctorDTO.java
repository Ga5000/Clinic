package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.Genre;
import com.ga5000.Clinic.entities.enums.Speciality;

public record DoctorDTO(String medicalLicense, String name, String email, Genre genre, Speciality speciality) {
}
