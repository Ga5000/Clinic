package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.Insurance;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.Genre;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.State;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RegisterDoctorDTO(String medicalLicense, String name, String email, String password, LocalDate birthDate, Genre genre,
                                String phoneNumber, String street, int number, String complement, String neighborhood,
                                String zip, City city, State state, Speciality speciality, LocalTime startShift,
                                LocalTime endShift, List<Insurance> insurances) {
}
