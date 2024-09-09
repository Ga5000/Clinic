package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.Appointment.AllAppointmentsDTO;
import com.ga5000.Clinic.dtos.Appointment.AppointmentDTO;
import com.ga5000.Clinic.dtos.Patient.PatientDTO;
import java.util.List;

public interface PatientService {
    List<AppointmentDTO> getAllScheduledAppointments(Long patientId);
    List<AppointmentDTO> getAppointmentHistory(Long patientId);
    List<AppointmentDTO> getCanceledAppointments(Long patientId);
    List<AllAppointmentsDTO> getAllAppointments(Long patientId);

    void cancelAppointment(Long patientId, Long appointmentId);

    PatientDTO getPatientInfoById(Long patientId);
}
