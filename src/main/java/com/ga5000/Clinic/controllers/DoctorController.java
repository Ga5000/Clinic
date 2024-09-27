package com.ga5000.Clinic.controllers;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.services.DoctorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    private <T> ResponseEntity<Object> checkEmptyList(List<T> list) {
        return list.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments found")
                : ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/appointments/filtered/date")
    public ResponseEntity<Object> appointmentsFilteredDate(@RequestParam @Valid String medicalLicense, @RequestParam @Valid LocalDate date) {
        List<AppointmentDTO> appointments = doctorService.getDoctorAppointmentsFilteredByDate(medicalLicense, date);
        return checkEmptyList(appointments);
    }

    @GetMapping("/appointments/filtered/range/time")
    public ResponseEntity<Object> appointmentsWithinDateRange(@RequestParam @Valid String medicalLicense,
                                                              @RequestParam @Valid LocalDate date ,
                                                              @RequestParam @Valid LocalTime startTime,
                                                              @RequestParam @Valid LocalTime endTime){
        List<AppointmentDTO> appointments = doctorService.getDoctorAppointmentsWithinTimeRangeFilteredByDate(medicalLicense,date,startTime,endTime);
        return checkEmptyList(appointments);
    }

}
