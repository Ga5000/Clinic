package com.ga5000.Clinic.config;

import com.ga5000.Clinic.entities.*;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.InsuranceType;
import com.ga5000.Clinic.entities.enums.State;
import com.ga5000.Clinic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
        // Create Insurance objects
        Insurance insurance1 = new Insurance(null, "HealthCare Inc.", InsuranceType.BASIC, LocalDate.of(2025, 12, 31), 20.0);
        Insurance insurance2 = new Insurance(null, "Wellness Co.", InsuranceType.PREMIUM, LocalDate.of(2026, 5, 1), 10.0);
        Insurance insurance3 = new Insurance(null, "LifeCare", InsuranceType.CORPORATE, LocalDate.of(2024, 8, 15), 12.0);
        Insurance insurance4 = new Insurance(null, "MediSafe", InsuranceType.FAMILY, LocalDate.of(2027, 3, 10), 25.0);
        Insurance insurance5 = new Insurance(null, "Smile Dental", InsuranceType.DENTAL, LocalDate.of(2023, 11, 20), 30.0);
        insuranceRepository.saveAll(List.of(insurance1, insurance2, insurance3, insurance4, insurance5));

        // Create Doctor objects
        Doctor doctor1 = new Doctor("Dr. Alice Brown", "alice.brown@example.com", "1234567890", new Address("Main St", 100, "", "Downtown", "12345", "Metropolis", State.NEW_YORK), "CRM12345", Speciality.CARDIOLOGY, LocalTime.of(9, 0), LocalTime.of(17, 0), null, List.of(insurance1, insurance2));
        Doctor doctor2 = new Doctor("Dr. Bob Green", "bob.green@example.com", "0987654321", new Address("Elm St", 200, "Apt 3", "Uptown", "54321", "Gotham", State.CALIFORNIA), "CRM54321", Speciality.DERMATOLOGY, LocalTime.of(8, 0), LocalTime.of(16, 0), null, List.of(insurance2, insurance3));
        Doctor doctor3 = new Doctor("Dr. Charlie Black", "charlie.black@example.com", "1122334455", new Address("Oak St", 50, "", "Westside", "67890", "Star City", State.TEXAS), "CRM67890", Speciality.PEDIATRICS, LocalTime.of(10, 0), LocalTime.of(18, 0), null, List.of(insurance3, insurance4));
        Doctor doctor4 = new Doctor("Dr. Dana White", "dana.white@example.com", "5566778899", new Address("Pine St", 300, "", "Eastside", "98765", "Central City", State.FLORIDA), "CRM98765", Speciality.ORTHOPEDICS, LocalTime.of(7, 0), LocalTime.of(15, 0), null, List.of(insurance4, insurance5));
        Doctor doctor5 = new Doctor("Dr. Emily Gray", "emily.gray@example.com", "6677889900", new Address("Cedar St", 400, "", "Midtown", "87654", "Smallville", State.ILLINOIS), "CRM87654", Speciality.NEUROLOGY, LocalTime.of(11, 0), LocalTime.of(19, 0), null, List.of(insurance5, insurance1));
        doctorRepository.saveAll(List.of(doctor1, doctor2, doctor3, doctor4, doctor5));

        // Create Patient objects
        Patient patient1 = new Patient("John Doe", "john.doe@example.com", "1231231234", new Address("Maple St", 1, "", "Oldtown", "65432", "Central City", State.NEW_YORK), null, "12345678901", null, List.of(insurance1, insurance3));
        Patient patient2 = new Patient("Jane Smith", "jane.smith@example.com", "3213214321", new Address("Birch St", 5, "Apt 2", "Seaside", "98765", "Metropolis", State.CALIFORNIA), null, "10987654321", null, List.of(insurance2, insurance4));
        Patient patient3 = new Patient("Mark Miller", "mark.miller@example.com", "4564564567", new Address("Ash St", 10, "", "Hilltop", "24680", "Star City", State.TEXAS), null, "23456789012", null, List.of(insurance3, insurance5));
        Patient patient4 = new Patient("Lisa Johnson", "lisa.johnson@example.com", "6546546543", new Address("Pine St", 20, "", "Rivertown", "13579", "Gotham", State.FLORIDA), null, "34567890123", null, List.of(insurance4, insurance1));
        Patient patient5 = new Patient("Michael Williams", "michael.williams@example.com", "7897897890", new Address("Willow St", 15, "", "Mountainview", "36912", "Smallville", State.ILLINOIS), null, "45678901234", null, List.of(insurance5, insurance2));
        patientRepository.saveAll(List.of(patient1, patient2, patient3, patient4, patient5));

        // Create Appointment objects
        Appointment appointment1 = new Appointment(null, LocalDate.of(2024, 9, 15), LocalTime.of(14, 30), doctor1, patient1, 0, AppointmentStatus.SCHEDULED);
        Appointment appointment2 = new Appointment(null, LocalDate.of(2024, 9, 16), LocalTime.of(9, 0), doctor2, patient2, 0, AppointmentStatus.SCHEDULED);
        Appointment appointment3 = new Appointment(null, LocalDate.of(2024, 9, 17), LocalTime.of(11, 0), doctor3, patient3, 0, AppointmentStatus.SCHEDULED);
        Appointment appointment4 = new Appointment(null, LocalDate.of(2024, 9, 18), LocalTime.of(13, 30), doctor4, patient4, 0, AppointmentStatus.SCHEDULED);
        Appointment appointment5 = new Appointment(null, LocalDate.of(2024, 9, 19), LocalTime.of(15, 0), doctor5, patient5, 0, AppointmentStatus.SCHEDULED);
        appointmentRepository.saveAll(List.of(appointment1, appointment2, appointment3, appointment4, appointment5));
    }
}
