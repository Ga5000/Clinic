package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentDTO;

import java.util.List;

public interface PatientService {
    List<AppointmentDTO> getAllScheduledAppointments(Long patientId);
}
