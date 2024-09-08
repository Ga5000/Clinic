package com.ga5000.Clinic.dtos.Appointment;

import com.ga5000.Clinic.entities.enums.AgeGroup;
import com.ga5000.Clinic.entities.enums.Gender;

import java.time.LocalDate;
import java.time.LocalTime;

public record DoctorAppointmentsDTO(
        Long appointmentId,
        String patientName,
        Integer age,
        LocalDate birthDate,
        Gender gender,
        AgeGroup ageGroup,
        LocalDate appointmentDate,
        LocalTime appointmentTime
){}
