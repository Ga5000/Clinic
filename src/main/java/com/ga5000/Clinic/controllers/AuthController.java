package com.ga5000.Clinic.controllers;

import com.ga5000.Clinic.dtos.LoginDTO;
import com.ga5000.Clinic.dtos.RegisterDoctorDTO;
import com.ga5000.Clinic.dtos.RegisterPatientDTO;
import com.ga5000.Clinic.services.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @PostMapping("/register/patient")
    public ResponseEntity<Object> registerPatient(@RequestBody @Valid RegisterPatientDTO registerPatientDTO){
        authService.registerPatient(registerPatientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<Object> registerDoctor(@RequestBody @Valid RegisterDoctorDTO registerDoctorDTO){
        authService.registerDoctor(registerDoctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
    }

    @DeleteMapping("/delete/{userIdentification}")
    public ResponseEntity<Object> deletePatient(@PathVariable String userIdentification){
        authService.deleteUser(userIdentification);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.loginUser(loginDTO.email(), loginDTO.password()));
    }
}
