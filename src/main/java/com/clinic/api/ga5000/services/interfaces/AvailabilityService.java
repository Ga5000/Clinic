package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.dtos.AvailabilityDTO;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.enums.USState;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityService {
    List<AvailabilityDTO> getDoctorAvailabilityByMedicalLicense(String medicalLicense);
    List<AvailabilityDTO> getDoctorAvailabilityByDateTimeAndLocationAndSpeciality(LocalDate date, LocalTime time,
                                                                                  String city, USState state,
                                                                                  Speciality speciality);
}
