package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.PatientService;
import com.ga5000.Clinic.utils.DtoConversion;
import com.ga5000.Clinic.utils.Finder;

import java.util.List;



public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public List<AppointmentDTO> getAllScheduledAppointments(Long patientId) {
       Finder.findPatientById(patientId);
        return patientRepository.findScheduledAppointments(patientId)
                .stream().map(DtoConversion::toAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentHistory(Long patientId) {
        Finder.findPatientById(patientId);
        return patientRepository.findAppointmentHistory(patientId)
                .stream().map(DtoConversion::toAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getCanceledAppointments(Long patientId) {
        Finder.findPatientById(patientId);
        return patientRepository.findCanceledAppointments(patientId)
                .stream().map(DtoConversion::toAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(Long patientId) {
        Finder.findPatientById(patientId);
        return patientRepository.findAllAppointments(patientId)
                .stream().map(DtoConversion::toAppointmentDTO).toList();
    }

    @Override
    public void cancelAppointment(Long patientId, Long appointmentId) {
        Finder.findPatientById(patientId);
        Finder.findAppointmentById(appointmentId);
        patientRepository.deleteAppointmentByPatientIdAndAppointmentId(patientId,appointmentId);
    }


}
