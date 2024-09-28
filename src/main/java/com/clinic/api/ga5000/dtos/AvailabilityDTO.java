package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;

public record AvailabilityDTO(String doctorName, Genre genre, Speciality speciality, LocalDate date,
                              LocalTime startTime, LocalTime endTime) {
}
