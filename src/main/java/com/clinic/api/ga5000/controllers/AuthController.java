package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.DeleteAccountDTO;
import com.clinic.api.ga5000.dtos.DoctorRegisterDTO;
import com.clinic.api.ga5000.dtos.LoginDTO;
import com.clinic.api.ga5000.dtos.PatientRegisterDTO;
import com.clinic.api.ga5000.services.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "User login",
            description = "Authenticates the user and returns a JWT token for further requests.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
            @ApiResponse(responseCode = "400", description = "Invalid login credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/register/patient")
    @Operation(summary = "Patient registration",
            description = "Registers a new patient and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> register(@RequestBody @Valid PatientRegisterDTO registerDTO) {
        authService.registerPatient(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully");
    }

    @PostMapping("/register/doctor")
    @Operation(summary = "Doctor registration",
            description = "Registers a new doctor and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> register(@RequestBody @Valid DoctorRegisterDTO registerDTO) {
        authService.registerDoctor(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully");
    }

    @DeleteMapping("/account/delete")
    @Operation(summary = "Delete user account",
            description = "Deletes the user account based on the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid account credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteAccount(@RequestBody @Valid DeleteAccountDTO deleteAccountDTO) {
        authService.deleteUser(deleteAccountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
