package com.ga5000.Clinic.controllers;

import com.ga5000.Clinic.services.PatientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(String ssn, String medicalLicense, LocalDate selectedDate,
                                                  LocalTime selectedTime){

        patientService.bookAppointment(ssn, medicalLicense, selectedDate, selectedTime);
        return ResponseEntity.ok("Appointment booked");

    }


}
