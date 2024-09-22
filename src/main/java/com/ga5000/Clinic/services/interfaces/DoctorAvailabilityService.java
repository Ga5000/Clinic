package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.DoctorAvailabilityDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.State;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAvailabilityService {
    List<DoctorDTO>  getAvailableDoctorsWithNoConflictingAppointments( LocalDate date,
                                                                       LocalTime startTime,
                                                                      LocalTime endTime,
                                                                       City city,
                                                                       State state);

    List<DoctorAvailabilityDTO> getAllDoctorAvailability(String medicalLicense);

    void addAvailability(String medicalLicense, LocalDate date, LocalTime startTime, LocalTime endTime);
    void updateAvailability(Long availabilityId, String medicalLicense, LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime);
    void deleteAvailability(Long availabilityId, String medicalLicense);

}
