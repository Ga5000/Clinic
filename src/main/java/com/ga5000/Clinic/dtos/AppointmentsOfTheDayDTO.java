package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.AgeGroup;
import com.ga5000.Clinic.entities.enums.Gender;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentsOfTheDayDTO(
        Long appointmentId,
        String patientName,
        Integer age,
        LocalDate birthDate,
        Gender gender,
        AgeGroup ageGroup,
        LocalTime appointmentTime
){}
