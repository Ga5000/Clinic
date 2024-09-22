package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.Address;
import com.ga5000.Clinic.entities.enums.Genre;

public record PatientDTO(String ssn, String name, String email, Address address, Genre genre, int age, String phoneNumber) {
}
