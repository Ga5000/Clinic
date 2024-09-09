package com.ga5000.Clinic.dtos.Appointment;

import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;

import java.sql.Time;
import java.time.LocalDate;

public record AllAppointmentsDTO(Long appointmentId, LocalDate appointmentDate, Time appointmentTime,
                                 Speciality speciality, String doctorName, AppointmentStatus status) {
}
