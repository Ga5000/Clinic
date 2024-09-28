package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.fields.Address;

public record PatientDTO(String ssn, String name, String email, String phoneNumber, Genre genre, int age, Address address ) {
}
