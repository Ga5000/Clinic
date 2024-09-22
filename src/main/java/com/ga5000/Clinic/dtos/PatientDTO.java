package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.Address;
import com.ga5000.Clinic.entities.Insurance;
import com.ga5000.Clinic.entities.enums.Genre;

import java.util.List;

public record PatientDTO(String ssn, String name, String email, Address address, Genre genre, int age, String phoneNumber, List<InsuranceDTO> insurances) {
}
