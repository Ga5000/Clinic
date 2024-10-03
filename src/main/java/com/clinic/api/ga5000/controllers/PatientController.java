package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.PatientDTO;
import com.clinic.api.ga5000.services.PatientServiceImpl;
import com.clinic.api.ga5000.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientServiceImpl patientService;
    private final SecurityUtil securityUtil;

    public PatientController(PatientServiceImpl patientService, SecurityUtil securityUtil) {
        this.patientService = patientService;
        this.securityUtil = securityUtil;
    }

    @GetMapping("/patient-info/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable UUID id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(!securityUtil.isAuthorized(patientDTO.email())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
    }

    @GetMapping("/all-patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }


}
