package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.PatientDTO;
import com.clinic.api.ga5000.services.PatientServiceImpl;
import com.clinic.api.ga5000.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Gets patient information by ID",
            description = "Retrieves the details of a patient using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found and details returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PatientDTO.class))),
            @ApiResponse(responseCode = "403", description = "User is not authorized to access this patient's details"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable UUID id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(patientDTO.email())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
    }

    @GetMapping("/all-patients")
    @Operation(summary = "Gets all patients",
            description = "Retrieves a list of all patients.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of patients retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PatientDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }


}
