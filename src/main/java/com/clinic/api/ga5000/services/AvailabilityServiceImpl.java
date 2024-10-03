package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.AvailabilityDTO;
import com.clinic.api.ga5000.entities.DoctorAvailability;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.enums.USState;
import com.clinic.api.ga5000.repositories.AvailabilityRepository;
import com.clinic.api.ga5000.services.interfaces.AvailabilityService;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final Finder finder;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, Finder finder) {
        this.availabilityRepository = availabilityRepository;
        this.finder = finder;
    }


    @Override
    public List<AvailabilityDTO> getDoctorAvailabilityByMedicalLicense(String medicalLicense) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        return availabilityRepository.findByDoctorMedicalLicense(medicalLicense)
                .stream().map(DtoConverter::convertToAvailabilityDTO).toList();
    }

    @Override
    public List<AvailabilityDTO> getDoctorAvailabilityByDateTimeAndLocationAndSpeciality(LocalDate date, LocalTime time,
                                                                                         String city, USState state,
                                                                                         Speciality speciality) {
        return availabilityRepository.
                findAvailableDoctorsByDateTimeAndLocationAndSpeciality(date, time, time.plusMinutes(30),
                        state, city, speciality)
                .stream().map(DtoConverter::convertToAvailabilityDTO).toList();
    }

    @Override
    public void addAvailabilities(List<DoctorAvailability> availabilities) {
        availabilityRepository.saveAll(availabilities);
    }

    @Override
    public void deleteAvailability(UUID availabilityId) {
        DoctorAvailability availability = finder.findAvailabilityById(availabilityId);
        availabilityRepository.delete(availability);
    }


    @Override
    public void updateAvailability(UUID availabilityId, AvailabilityDTO availabilityDTO) {
        DoctorAvailability availability = finder.findAvailabilityById(availabilityId);
        BeanUtils.copyProperties(availabilityDTO, availability);
        availabilityRepository.save(availability);
    }

    @Override
    public DoctorAvailability getDoctorAvailabilityById(UUID availabilityId) {
      return finder.findAvailabilityById(availabilityId);
    }
}
