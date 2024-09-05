package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private Time appointmentTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    @ManyToOne @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne @JoinColumn(name = "staffId", nullable = false)
    private Staff doctor;

    public Appointment(Long appointmentId, LocalDate appointmentDate, Time appointmentTime, Speciality speciality,
                       Patient patient, Staff doctor, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.speciality = speciality;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getDoctor() {
        return doctor;
    }

    public void setDoctor(Staff doctor) {
        this.doctor = doctor;
    }
}
