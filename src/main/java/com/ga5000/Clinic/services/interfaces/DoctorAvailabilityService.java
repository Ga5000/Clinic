package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.DoctorAvailabilityDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAvailabilityService {
    List<DoctorDTO>  getAvailableDoctorsWithNoConflictingAppointments( LocalDate date,
                                                                       LocalTime startTime,
                                                                      LocalTime endTime,
                                                                       String city,
                                                                       String state);

    List<DoctorAvailabilityDTO> getAllDoctorAvailability(String medicalLicense);

    void addAvailability(String medicalLicense, LocalDate date, LocalTime startTime, LocalTime endTime);

}
