package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.fields.Address;

import java.time.LocalDate;

public record DoctorRegisterDTO(String medicaLicense, String email, String password, String phoneNumber, String firstName, Speciality speciality, String lastName, LocalDate birthDate, Genre genre, Address address) {

}
