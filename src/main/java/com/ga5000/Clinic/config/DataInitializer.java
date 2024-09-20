package com.ga5000.Clinic.config;

import com.ga5000.Clinic.entities.*;
import com.ga5000.Clinic.entities.enums.*;
import com.ga5000.Clinic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Insurance instances
        Insurance insurance1 = new Insurance("HealthCorp", InsuranceType.PREMIUM, LocalDate.of(2025, 12, 31), 10.0);
        Insurance insurance2 = new Insurance("WellCare", InsuranceType.STANDARD, LocalDate.of(2025, 12, 31), 15.0);

        insuranceRepository.saveAll(Arrays.asList(insurance1, insurance2));


        Address address1 = new Address("123 Elm St", 101, "Apt 1", "Downtown", "62704", City.SPRINGFIELD, State.ILLINOIS);
        Address address2 = new Address("456 Oak St", 202, "", "Suburbia", "62705", City.SPRINGFIELD, State.ILLINOIS);

        DoctorAvailability availability1 = new DoctorAvailability(null, LocalDate.of(2024, 9, 18), LocalTime.of(8, 0), LocalTime.of(12, 0));
        DoctorAvailability availability2 = new DoctorAvailability(null, LocalDate.of(2024, 9, 19), LocalTime.of(13, 0), LocalTime.of(16, 0));


        DoctorAvailability availability3 = new DoctorAvailability(null, LocalDate.of(2024, 9, 18), LocalTime.of(9, 0), LocalTime.of(12, 0));
        DoctorAvailability availability4 = new DoctorAvailability(null, LocalDate.of(2024, 9, 20), LocalTime.of(14, 0), LocalTime.of(17, 0));

        Doctor doctor1 = new Doctor("Dr. Smith", "password123", "smith@example.com", 45, LocalDate.of(1979, 5, 10),
                Genre.MALE, "123-456-7890", address1, "DL123456", Speciality.CARDIOLOGY, LocalTime.of(8, 0), LocalTime.of(16, 0),
                null, List.of(insurance1), List.of(availability1,availability2));

        availability1.setDoctor(doctor1);
        availability2.setDoctor(doctor1);

        Doctor doctor2 = new Doctor("Dr. Johnson", "password123", "johnson@example.com", 50, LocalDate.of(1974, 11, 22),
                Genre.FEMALE, "987-654-3210", address2, "DL654321", Speciality.DERMATOLOGY, LocalTime.of(9, 0), LocalTime.of(17, 0),
                null, List.of(insurance2), List.of(availability3,availability4));

        availability3.setDoctor(doctor2);
        availability4.setDoctor(doctor2);






        doctorRepository.saveAll(Arrays.asList(doctor1, doctor2));

        Patient patient1 = new Patient("John Doe", "password123", "doe@example.com", 30, LocalDate.of(1994, 7, 18),
                Genre.MALE, "555-1234", address1, "SSN123456789", null, List.of(insurance1), null);

        Patient patient2 = new Patient("Jane Roe", "password123", "roe@example.com", 28, LocalDate.of(1996, 2, 14),
                Genre.FEMALE, "555-5678", address2, "SSN987654321", null, List.of(insurance2), null);

        patientRepository.saveAll(Arrays.asList(patient1, patient2));

        // Create Appointment instances
        Appointment appointment1 = new Appointment(LocalDate.of(2024, 9, 15), LocalTime.of(10, 30), doctor1, patient1, 200.0, AppointmentStatus.SCHEDULED);
        Appointment appointment2 = new Appointment(LocalDate.of(2024, 9, 16), LocalTime.of(14, 0), doctor2, patient2, 180.0, AppointmentStatus.SCHEDULED);
        appointment1.setDoctor(doctor1);
        appointment2.setDoctor(doctor2);
        appointmentRepository.saveAll(List.of(appointment1, appointment2));
    }
}
