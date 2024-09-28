package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.AppointmentStatus;
import com.clinic.api.ga5000.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentDTO(UUID appointmentId, LocalDate appointmentDate, LocalTime appointmentTime,
                             double fee, String doctorName, Speciality speciality, AppointmentStatus status) {
}
