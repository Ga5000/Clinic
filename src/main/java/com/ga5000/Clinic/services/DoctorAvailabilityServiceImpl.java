package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.DoctorAvailabilityDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.repositories.DoctorAvailabilityRepository;
import com.ga5000.Clinic.services.interfaces.DoctorAvailabilityService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final Finder finder;

    public DoctorAvailabilityServiceImpl(DoctorAvailabilityRepository doctorAvailabilityRepository, Finder finder) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
        this.finder = finder;
    }

    @Override
    public List<DoctorDTO> getAvailableDoctorsWithNoConflictingAppointments(LocalDate date, LocalTime startTime,
                                                                            LocalTime endTime, String city, String state) {
        return doctorAvailabilityRepository.findAvailableDoctorsWithNoConflictingAppointments(date, startTime, endTime, city, state)
                .stream().map(DtoConverter::covertToDoctorDTO).toList();
    }

    @Override
    public List<DoctorAvailabilityDTO> getAllDoctorAvailability(String medicalLicense) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        return doctorAvailabilityRepository.findAll().stream().map(DtoConverter::convertToDoctorAvailabilityDTO)
                .toList();
    }

    @Override
    public void addAvailability(String medicalLicense, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Doctor doctor = finder.findAndReturnDoctorByMedicalLicense(medicalLicense);
        DoctorAvailability newAvailability = new DoctorAvailability(doctor,date,startTime,endTime);
        doctorAvailabilityRepository.save(newAvailability);
    }

}
