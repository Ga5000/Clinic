package com.clinic.api.ga5000.entities;


import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.enums.Role;
import com.clinic.api.ga5000.entities.fields.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends UserEntity {

    @Column(unique = true, nullable = false)
    private String ssn;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Insurance> insurances;

    public Patient(String password, String email, String firstName, String lastName, String phoneNumber,
                   int age, LocalDate birthDate, Genre genre, Address address, Role role, String ssn,
                   Set<Appointment> appointments, Set<Notification> notifications, Set<Insurance> insurances) {
        super(password, email, firstName, lastName, phoneNumber, age, birthDate, genre, address, role);
        this.ssn = ssn;
        this.appointments = appointments;
        this.notifications = notifications;
        this.insurances = insurances;
        setRole(Role.PATIENT);
    }

    public Patient() {}

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<Insurance> insurances) {
        this.insurances = insurances;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
