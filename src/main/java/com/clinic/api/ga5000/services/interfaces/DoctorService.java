package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.dtos.DoctorDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    // get doctor by his id
    DoctorDTO getDoctorById(UUID id);
    // get all doctors
    List<DoctorDTO> getAllDoctors();
}
