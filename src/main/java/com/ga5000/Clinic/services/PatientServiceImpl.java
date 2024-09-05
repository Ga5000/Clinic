package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.PatientService;
import com.ga5000.Clinic.utils.DtoConversion;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<AppointmentDTO> getAllScheduledAppointments(Long patientId) {
        return patientRepository.findScheduledAppointments(patientId)
                .stream().map(DtoConversion::toAppointmentDTO).toList();
    }
}
