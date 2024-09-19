package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentDTO(UUID appointmentId, LocalDate date, LocalTime time, double fee, AppointmentStatus status,
                             String doctorName, Speciality speciality) {
}
