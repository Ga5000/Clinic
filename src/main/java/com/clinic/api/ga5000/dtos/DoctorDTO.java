package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.enums.Genre;
public record DoctorDTO(String medicalLicense, String name, String email, Genre genre, int age, Speciality speciality) {
}
