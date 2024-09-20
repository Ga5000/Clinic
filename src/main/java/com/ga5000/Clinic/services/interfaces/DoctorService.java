package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface DoctorService {
    void cancelAppointment(String medicalLicense, UUID appointmentId);
    void cancelAllAppointmentsOfADay(String medicalLicense, LocalDate date);
    List<AppointmentDTO> getDoctorAppointmentsFilteredByDate(String medicalLicense, LocalDate filterDate);
    List<AppointmentDTO> getDoctorAppointmentsWithinTimeRangeFilteredByDate(String medicalLicense, LocalDate filterDate,
                                                                             LocalTime startTime, LocalTime endTime);

    List<DoctorDTO> getDoctorsByEnterpriseAcceptance(String enterprise);
    List<DoctorDTO> getDoctorsBySpeciality(Speciality speciality);
}
