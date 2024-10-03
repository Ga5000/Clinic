package com.clinic.api.ga5000.entities;

import com.clinic.api.ga5000.entities.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Appointment {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "ssn", referencedColumnName = "ssn", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicalLicense", referencedColumnName = "medicalLicense", nullable = false)
    private Doctor doctor;

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, Patient patient, Doctor doctor) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.doctor = doctor;

        switch (doctor.getSpeciality()) {
            case CARDIOLOGY -> this.fee = 200.00;
            case DERMATOLOGY -> this.fee = 150.00;
            case GYNECOLOGY, UROLOGY -> this.fee = 180.00;
            case NEUROLOGY, EMERGENCY_MEDICINE -> this.fee = 250.00;
            case ORTHOPEDICS -> this.fee = 170.00;
            case PEDIATRICS -> this.fee = 120.00;
            case PSYCHIATRY, RHEUMATOLOGY -> this.fee = 160.00;
            case RADIOLOGY -> this.fee = 220.00;
            case GENERAL_PRACTICE -> this.fee = 100.00;
            case OPHTHALMOLOGY -> this.fee = 140.00;
            case ONCOLOGY -> this.fee = 230.00;
            case ENT -> this.fee = 130.00;
            case ANESTHESIOLOGY -> this.fee = 210.00;
            case FAMILY_MEDICINE -> this.fee = 110.00;
            case INTERNAL_MEDICINE -> this.fee = 190.00;
            case SURGERY -> this.fee = 300.00;
            case PHYSICAL_THERAPY -> this.fee = 90.00;
            default -> throw new IllegalArgumentException("Unknown speciality");
        }
    }

    public Appointment() {}

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
