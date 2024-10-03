package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.DoctorDTO;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.services.DoctorServiceImpl;

import com.clinic.api.ga5000.utils.SecurityUtil;
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
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable UUID id) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(id);

        if (doctorDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(!securityUtil.isAuthorized(doctorDTO.email())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);
    }


    @GetMapping("/all-doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @GetMapping("/specialities")
    public ResponseEntity<List<Speciality>> getSpecialities() {
        List<Speciality> specialities =  Arrays.stream(Speciality.class.getEnumConstants()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(specialities);
    }
}
