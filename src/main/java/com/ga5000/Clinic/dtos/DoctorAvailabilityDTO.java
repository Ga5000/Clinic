package com.ga5000.Clinic.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record DoctorAvailabilityDTO(Long id, String doctorName,
                                    String doctorEmail, LocalDate date, LocalTime startTime, LocalTime endTime) {
}
