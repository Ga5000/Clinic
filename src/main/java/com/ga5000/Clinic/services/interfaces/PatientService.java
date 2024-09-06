package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentDTO;

import java.util.List;

public interface PatientService {
    List<AppointmentDTO> getAllScheduledAppointments(Long patientId);
    List<AppointmentDTO> getAppointmentHistory(Long patientId);
    List<AppointmentDTO> getCanceledAppointments(Long patientId);
    List<AppointmentDTO> getAllAppointments(Long patientId);

    void cancelAppointment(Long patientId, Long appointmentId);
}
