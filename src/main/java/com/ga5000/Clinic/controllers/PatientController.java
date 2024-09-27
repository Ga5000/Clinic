package com.ga5000.Clinic.controllers;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.BookAppointmentDTO;
import com.ga5000.Clinic.dtos.PatientDTO;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.State;
import com.ga5000.Clinic.services.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    private <T> ResponseEntity<Object> checkEmptyList(List<T> list) {
        return list.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointments found")
                : ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(@RequestBody @Valid BookAppointmentDTO bookAppointmentDTO) {
        patientService.bookAppointment(bookAppointmentDTO.ssn(), bookAppointmentDTO.medicalLicense(),
                bookAppointmentDTO.date(), bookAppointmentDTO.time());
        return ResponseEntity.status(HttpStatus.CREATED).body("Appointment booked");
    }

    @GetMapping("/my-appointments/{ssn}")
    public ResponseEntity<Object> myAppointments(@PathVariable @Valid String ssn) {
        List<AppointmentDTO> appointments = patientService.getMyAppointments(ssn);
        return checkEmptyList(appointments);
    }

    @GetMapping("/appointments/history/filtered/date")
    public ResponseEntity<Object> appointmentsHistoryFilteredDate(@RequestParam @Valid String ssn,
                                                                  @RequestParam @Valid LocalDate filteredDate) {
        List<AppointmentDTO> appointments = patientService.getAppointmentsHistoryFilteredByDate(ssn, filteredDate);
        return checkEmptyList(appointments);
    }

    @GetMapping("/appointments/history/filtered/speciality")
    public ResponseEntity<Object> appointmentsHistoryFilteredSpeciality(@RequestParam @Valid String ssn,
                                                                        @RequestParam @Valid Speciality speciality) {
        List<AppointmentDTO> appointments = patientService.getAppointmentsHistoryFilteredBySpeciality(ssn, speciality);
        return checkEmptyList(appointments);
    }

    @GetMapping("/appointments/history/range/date-time")
    public ResponseEntity<Object> appointmentsWithinDateRange(@RequestParam @Valid String ssn,
                                                              @RequestParam @Valid LocalDate startDate,
                                                              @RequestParam @Valid LocalDate endDate) {
        List<AppointmentDTO> appointments = patientService.getAppointmentsWithinDateRange(ssn, startDate, endDate);
        return checkEmptyList(appointments);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> cities() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getCities());
    }

    @GetMapping("/states")
    public ResponseEntity<List<State>> states() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getStates());
    }

    @PostMapping("/patient-info")
    public ResponseEntity<PatientDTO> info(@RequestParam @Valid String ssn) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getInfo(ssn));
    }

    @PutMapping("/appointments/cancel/{ssn}")
    public ResponseEntity<String> cancelAppointment(@PathVariable @Valid String ssn, @RequestParam @Valid UUID appointmentId) {
        patientService.cancelAppointment(ssn, appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body("Appointment cancelled");
    }
}
