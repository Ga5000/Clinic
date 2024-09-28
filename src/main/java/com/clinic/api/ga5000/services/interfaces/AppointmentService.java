package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.dtos.AppointmentDTO;
import com.clinic.api.ga5000.entities.Appointment;
import com.clinic.api.ga5000.entities.enums.Speciality;
import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface AppointmentService {

    // Create a new appointment
    void createAppointment(Appointment appointment);

    // Retrieve an appointment by its ID
    AppointmentDTO getAppointmentById(UUID appointmentId);

    // Cancel an individual appointment
    void cancelAppointment(UUID appointmentId, String ssn, String medicalLicense);

    // Cancel all appointments for a doctor on a specific date
    void cancelAllAppointmentsOfADay(LocalDate date, String medicalLicense);

    // Find appointments by SSN only
    Set<AppointmentDTO> findAppointmentsBySsn(String ssn);

    // Find appointments by Doctor's Medical License only
    Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicense(String medicalLicense);

    // Find appointments by SSN and filter date
    Set<AppointmentDTO> findAppointmentsBySsnAndDate(String ssn, LocalDate filterDate);

    // Find appointments by Doctor's Medical License and filter date
    Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicenseAndDate(String medicalLicense, LocalDate filterDate);

    // Find appointments by SSN and speciality
    Set<AppointmentDTO> findAppointmentsBySsnAndSpeciality(String ssn, Speciality speciality);

    // Find appointments by date range
    Set<AppointmentDTO> findAppointmentsByDateRange(LocalDate startDate, LocalDate endDate);

    // Find appointments by SSN and date range
    Set<AppointmentDTO> findAppointmentsBySsnAndDateRange(String ssn, LocalDate startDate, LocalDate endDate);

    // Find appointments by Doctor's Medical License and date range
    Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicenseAndDateRange(String medicalLicense, LocalDate startDate, LocalDate endDate);

    // Find appointments by SSN, speciality, and date range
    Set<AppointmentDTO> findAppointmentsBySsnAndSpecialityAndDateRange(String ssn, Speciality speciality, LocalDate startDate, LocalDate endDate);

}
