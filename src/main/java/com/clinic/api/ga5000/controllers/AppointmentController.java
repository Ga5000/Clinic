package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.dtos.AppointmentDTO;
import com.clinic.api.ga5000.entities.Appointment;
import com.clinic.api.ga5000.entities.Doctor;
import com.clinic.api.ga5000.entities.Patient;
import com.clinic.api.ga5000.entities.enums.AppointmentStatus;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.exceptions.UserNotFoundException;
import com.clinic.api.ga5000.repositories.UserEntityRepository;
import com.clinic.api.ga5000.services.AppointmentServiceImpl;
import com.clinic.api.ga5000.utils.Finder;
import com.clinic.api.ga5000.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;
    private final SecurityUtil securityUtil;
    private final Finder finder;
    private final UserEntityRepository userEntityRepository;

    public AppointmentController(AppointmentServiceImpl appointmentService, SecurityUtil securityUtil, Finder finder, UserEntityRepository userEntityRepository) {
        this.appointmentService = appointmentService;
        this.securityUtil = securityUtil;
        this.finder = finder;
        this.userEntityRepository = userEntityRepository;
    }

    @PostMapping("/make")
    public ResponseEntity<String> makeAppointment(@RequestParam String medicalLicense,
                                                  @RequestParam LocalDate appointmentDate,
                                                  @RequestParam LocalTime appointmentTime) {

        String currentUsername = securityUtil.getCurrentUsername();
        Patient patient = (Patient) userEntityRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));


        Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        Appointment appointment = new Appointment(appointmentDate,appointmentTime,0,patient,doctor,
                AppointmentStatus.SCHEDULED);

        appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Appointment made successfully");
    }

    @PutMapping("/cancel")
    public ResponseEntity<Object> cancelAppointment(@RequestParam UUID appointmentId,
                                                    @RequestParam(required = false) String ssn,
                                                    @RequestParam(required = false) String medicalLicense) {



        if(ssn == null && medicalLicense == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if(ssn != null){
            Patient patient = finder.findPatientBySsn2(ssn);
           if (patient == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
           if(!securityUtil.isAuthorized(patient.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
           appointmentService.cancelAppointment(appointmentId,ssn,null);
        }
        else {
            Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
            if(doctor == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            if(!securityUtil.isAuthorized(doctor.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            appointmentService.cancelAppointment(appointmentId,null,medicalLicense);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/cancel/all")
    public ResponseEntity<Object> cancelAllAppointmentsOfADay(@RequestParam LocalDate date,
                                                                @RequestParam String medicalLicense) {
        Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        if(doctor == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(!securityUtil.isAuthorized(doctor.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        appointmentService.cancelAllAppointmentsOfADay(date,medicalLicense);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/doctor")
    public ResponseEntity<Set<AppointmentDTO>> getAppointmentsByDoctorMedicalLicense(@RequestParam String medicalLicense) {
        Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        if (doctor == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(doctor.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsByDoctorMedicalLicense(medicalLicense);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("/patient/by-date")
    public ResponseEntity<Set<AppointmentDTO>> getAppointmentsBySsnAndDate(@RequestParam String ssn,
                                                                           @RequestParam LocalDate date) {
        Patient patient = finder.findPatientBySsn2(ssn);
        if (patient == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(patient.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsBySsnAndDate(ssn, date);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("/doctor/by-date")
    public ResponseEntity<Set<AppointmentDTO>> getAppointmentsByDoctorMedicalLicenseAndDate(@RequestParam String medicalLicense,
                                                                                            @RequestParam LocalDate date) {
        Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        if (doctor == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(doctor.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsByDoctorMedicalLicenseAndDate(medicalLicense, date);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient/date-range/by-speciality")
    public ResponseEntity<Set<AppointmentDTO>> getAppointmentsBySsnAndSpecialityAndDateRange(@RequestParam String ssn,
                                                                                             @RequestParam Speciality speciality,
                                                                                             @RequestParam LocalDate startDate,
                                                                                             @RequestParam LocalDate endDate) {
        Patient patient = finder.findPatientBySsn2(ssn);
        if (patient == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(patient.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsBySsnAndSpecialityAndDateRange(ssn, speciality, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("/date-range")
    public ResponseEntity<Set<AppointmentDTO>> getAppointmentsByDateRange(@RequestParam LocalDate startDate,
                                                                          @RequestParam LocalDate endDate) {
        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsByDateRange(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("/find/ssn-date-range")
    public ResponseEntity<Set<AppointmentDTO>> findAppointmentsBySsnAndDateRange(@RequestParam String ssn,
                                                                                 @RequestParam LocalDate startDate,
                                                                                 @RequestParam LocalDate endDate) {
        Patient patient = finder.findPatientBySsn2(ssn);
        if (patient == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(patient.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsBySsnAndDateRange(ssn, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

    @GetMapping("/find/doctor-date-range")
    public ResponseEntity<Set<AppointmentDTO>> findAppointmentsByDoctorMedicalLicenseAndDateRange(@RequestParam String medicalLicense,
                                                                                                  @RequestParam LocalDate startDate,
                                                                                                  @RequestParam LocalDate endDate) {
        Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        if (doctor == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!securityUtil.isAuthorized(doctor.getUsername())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        Set<AppointmentDTO> appointments = appointmentService.findAppointmentsByDoctorMedicalLicenseAndDateRange(medicalLicense, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }

}
