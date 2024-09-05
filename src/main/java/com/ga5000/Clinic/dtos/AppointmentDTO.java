package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;

import java.sql.Time;
import java.time.LocalDate;

public record AppointmentDTO(LocalDate appointmentDate, Time appointmentTime,
                             Speciality speciality, String doctorName, AppointmentStatus status) {
}
