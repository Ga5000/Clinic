package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.AddAvailabilityDTO;
import com.clinic.api.ga5000.dtos.AvailabilityDTO;
import com.clinic.api.ga5000.entities.Doctor;
import com.clinic.api.ga5000.entities.DoctorAvailability;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.enums.USState;
import com.clinic.api.ga5000.services.AvailabilityServiceImpl;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;
import com.clinic.api.ga5000.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctor/availability")
public class AvailabilityController {
    private final AvailabilityServiceImpl availabilityService;
    private final Finder finder;
    private final SecurityUtil securityUtil;

    public AvailabilityController(AvailabilityServiceImpl availabilityService, Finder finder, SecurityUtil securityUtil) {
        this.availabilityService = availabilityService;
        this.finder = finder;
        this.securityUtil = securityUtil;
    }


    @PostMapping("/add")
    @Operation(summary = "Add availabilities for a doctor",
            description = "Adds a list of availabilities to the specified doctor's schedule.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Availabilities added successfully"),
            @ApiResponse(responseCode = "403", description = "User is not authorized to add availabilities for this doctor"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> addAvailabilities(@RequestParam UUID doctorId, @RequestBody List<AddAvailabilityDTO> availabilities) {
        Doctor doctor = finder.findDoctorById(doctorId);

        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!securityUtil.isAuthorized(doctor.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<DoctorAvailability> availabilityList = availabilities.stream()
                .map(availabilityDTO -> {
                    DoctorAvailability availability = new DoctorAvailability();
                    availability.setDoctor(doctor);
                    availability.setDate(availabilityDTO.date());
                    availability.setStartTime(availabilityDTO.startTime());
                    availability.setEndTime(availabilityDTO.endTime());
                    return availability;
                }).toList();

        availabilityService.addAvailabilities(availabilityList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Availabilities added successfully");
    }

    @GetMapping("/doctor/{medicalLicense}")
    @Operation(summary = "Get doctor's availability by medical license",
            description = "Retrieves a list of availabilities for a doctor using their medical license.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Availabilities retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailabilityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Doctor has no availabilities or is not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AvailabilityDTO>> getDoctorAvailability(@PathVariable String medicalLicense) {
        List<AvailabilityDTO> availability = availabilityService.getDoctorAvailabilityByMedicalLicense(medicalLicense);
        if (availability.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(availability);
    }

    @DeleteMapping("/delete/{availabilityId}")
    @Operation(summary = "Delete availability by ID",
            description = "Deletes an availability from a doctor's schedule using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Availability deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteAvailability(@PathVariable UUID availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{availabilityId}")
    @Operation(summary = "Update availability by ID",
            description = "Updates an availability in the doctor's schedule.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Availability updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> updateAvailability(@PathVariable UUID availabilityId, @RequestBody AvailabilityDTO availabilityDTO) {
        availabilityService.updateAvailability(availabilityId, availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Availability updated successfully");
    }

    @GetMapping("/find/{availabilityId}")
    @Operation(summary = "Get availability by ID",
            description = "Retrieves an availability by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Availability found and returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailabilityDTO.class))),
            @ApiResponse(responseCode = "404", description = "Availability not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable UUID availabilityId) {
        DoctorAvailability availability = availabilityService.getDoctorAvailabilityById(availabilityId);
        if (availability == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoConverter.convertToAvailabilityDTO(availability));
    }

    @GetMapping("/find")
    @Operation(summary = "Get doctor's availability by date, time, location, and speciality",
            description = "Retrieves a list of availabilities for doctors based on date, time, city, state, and medical speciality.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Availabilities found and returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailabilityDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AvailabilityDTO>> getDoctorAvailabilityByDateTimeAndLocationAndSpeciality(@RequestParam LocalDate date,
                                                                                                         @RequestParam LocalTime time,
                                                                                                         @RequestParam String city,
                                                                                                         @RequestParam USState state,
                                                                                                         @RequestParam Speciality speciality) {
        List<AvailabilityDTO> availabilities =  availabilityService.getDoctorAvailabilityByDateTimeAndLocationAndSpeciality(date, time, city, state, speciality);
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }
}
