package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.DeleteAccountDTO;
import com.clinic.api.ga5000.dtos.DoctorRegisterDTO;
import com.clinic.api.ga5000.dtos.LoginDTO;
import com.clinic.api.ga5000.dtos.PatientRegisterDTO;
import com.clinic.api.ga5000.services.AuthServiceImpl;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginDTO));
    }

    @PostMapping("/register/patient")
    public ResponseEntity<String> register(@RequestBody @Valid PatientRegisterDTO registerDTO) {
        authService.registerPatient(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully");
    }


    @PostMapping("/register/doctor")
    public ResponseEntity<String> register(@RequestBody @Valid DoctorRegisterDTO registerDTO) {
        authService.registerDoctor(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully");
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody @Valid DeleteAccountDTO deleteAccountDTO) {
        authService.deleteUser(deleteAccountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account Deleted");
    }


}
