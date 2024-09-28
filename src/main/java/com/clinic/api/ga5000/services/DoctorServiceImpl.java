package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.DoctorDTO;
import com.clinic.api.ga5000.entities.Doctor;
import com.clinic.api.ga5000.exceptions.UserNotFoundException;
import com.clinic.api.ga5000.repositories.DoctorRepository;
import com.clinic.api.ga5000.services.interfaces.DoctorService;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;

import java.util.List;
import java.util.UUID;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final Finder finder;

    public DoctorServiceImpl(DoctorRepository doctorRepository, Finder finder) {
        this.doctorRepository = doctorRepository;
        this.finder = finder;
    }

    @Override
    public DoctorDTO getDoctorById(UUID id) {
        return DtoConverter.convertToDoctorDTO(finder.findDoctorById(id));
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            throw new UserNotFoundException("No doctors found");
        }
            return doctors.stream().map(DtoConverter::convertToDoctorDTO).toList();
    }
}
