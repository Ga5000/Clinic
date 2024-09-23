package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentDTO;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    void setAsFinished(UUID appointmentId);
    List<AppointmentDTO> getAll();

}
