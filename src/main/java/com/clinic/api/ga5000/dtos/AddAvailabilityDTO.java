package com.clinic.api.ga5000.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record AddAvailabilityDTO(LocalDate date, LocalTime startTime, LocalTime endTime) {
}
