package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.PatientDTO;
import com.clinic.api.ga5000.entities.Patient;
import com.clinic.api.ga5000.exceptions.UserNotFoundException;
import com.clinic.api.ga5000.repositories.PatientRepository;
import com.clinic.api.ga5000.services.interfaces.PatientService;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final Finder finder;

    public PatientServiceImpl(PatientRepository patientRepository, Finder finder) {
        this.patientRepository = patientRepository;
        this.finder = finder;
    }

    @Override
    public PatientDTO getPatientById(UUID id) {
        return DtoConverter.convertToPatientDTO(finder.findPatientById(id));
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) {
            throw new UserNotFoundException("No patients found");
        }
            return patients.stream().map(DtoConverter::convertToPatientDTO).toList();
    }
}
