package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.dtos.PatientDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    // get patient by his id
    PatientDTO getPatientById(UUID id);
    //get all patients
    List<PatientDTO> getAllPatients();

}
