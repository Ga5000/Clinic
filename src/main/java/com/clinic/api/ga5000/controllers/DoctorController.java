package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.DoctorDTO;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.services.DoctorServiceImpl;

import com.clinic.api.ga5000.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;
    private final SecurityUtil securityUtil;

    public DoctorController(DoctorServiceImpl doctorService, SecurityUtil securityUtil) {
        this.doctorService = doctorService;
        this.securityUtil = securityUtil;
    }

    @GetMapping("/doctor-info/{id}")
    @Operation(summary = "Get doctor by ID",
            description = "Retrieves the details of a doctor using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found and details returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DoctorDTO.class))),
            @ApiResponse(responseCode = "403", description = "User is not authorized to access this doctor's details"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable UUID id) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(id);

        if (doctorDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(doctorDTO.email())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);
    }

    @GetMapping("/all-doctors")
    @Operation(summary = "Get all doctors",
            description = "Retrieves a list of all registered doctors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of doctors retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DoctorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @GetMapping("/specialities")
    @Operation(summary = "Get all specialities",
            description = "Retrieves a list of all available medical specialities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of specialities retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Speciality.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Speciality>> getSpecialities() {
        List<Speciality> specialities = Arrays.stream(Speciality.class.getEnumConstants()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(specialities);
    }
}
