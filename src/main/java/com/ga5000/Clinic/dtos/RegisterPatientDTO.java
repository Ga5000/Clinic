package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.Genre;
import com.ga5000.Clinic.entities.enums.State;

import java.time.LocalDate;
import java.util.List;

public record RegisterPatientDTO(String ssn, String name, String email, String password, LocalDate birthDate, Genre genre,
                                 String phoneNumber, String street, int number, String complement, String neighborhood,
                                 String zip, City city, State state) {
}
