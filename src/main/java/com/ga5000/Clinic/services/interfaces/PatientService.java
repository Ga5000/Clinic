package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface PatientService {
    void bookAppointment(String ssn, String medicalLicense, LocalDate date, LocalTime time);
    void cancelAppointment(String ssn, UUID appointmentId);
    List<AppointmentDTO> getMyAppointments(String ssn);
    List<AppointmentDTO> getAppointmentsHistoryFilteredByDate(String ssn, LocalDate filterDate);
    List<AppointmentDTO> getAppointmentsHistoryFilteredBySpeciality(String ssn, Speciality speciality);
    List<AppointmentDTO>  getAppointmentsWithinDateRange(String ssn, LocalDate startDate, LocalDate endDate);
}
