package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.DoctorDTO;
import com.clinic.api.ga5000.services.DoctorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor-info/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable UUID id) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorDTO);
    }


    @GetMapping("/all-doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
}
