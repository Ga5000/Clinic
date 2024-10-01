package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.fields.Address;

import java.time.LocalDate;

public record PatientRegisterDTO(String ssn, String email, String password, String phoneNumber, String firstName, String lastName, LocalDate birthDate, Genre genre, Address address) {
}
