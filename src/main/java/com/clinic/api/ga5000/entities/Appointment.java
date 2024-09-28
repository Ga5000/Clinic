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

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, double fee, Patient patient,
                       Doctor doctor, AppointmentStatus status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.fee = fee;
        this.patient = patient;
        this.doctor = doctor;
        this.status = AppointmentStatus.SCHEDULED;
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
