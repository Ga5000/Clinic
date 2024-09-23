package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookAppointmentDTO(String patientName, String patientEmail, String doctorName,
                                 Speciality speciality, LocalDate date, LocalTime time, double fee) {
}
