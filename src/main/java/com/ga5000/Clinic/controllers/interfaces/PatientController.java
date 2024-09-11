package com.ga5000.Clinic.controllers.interfaces;
import com.ga5000.Clinic.dtos.Appointment.AppointmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientController {
    ResponseEntity<List<AppointmentDTO>> getScheduledAppointments(Long patientId);
    ResponseEntity<List<AppointmentDTO>> getAppointmentHistory(Long patientId);
}
