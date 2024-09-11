package com.ga5000.Clinic.controllers;

import com.ga5000.Clinic.controllers.interfaces.PatientController;
import com.ga5000.Clinic.dtos.Appointment.AppointmentDTO;
import com.ga5000.Clinic.services.PatientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientControllerImpl implements PatientController {

    private final PatientServiceImpl patientService;

    public PatientControllerImpl(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/scheduled-appointments/{patientId}")
    @Override
    public ResponseEntity<List<AppointmentDTO>> getScheduledAppointments(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllScheduledAppointments(patientId));
    }

    @Override
    public ResponseEntity<List<AppointmentDTO>> getAppointmentHistory(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAppointmentHistory(patientId));
    }
}
