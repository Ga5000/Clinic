package com.ga5000.Clinic.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Patient extends Person {
    @Id
    private String ssn;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "patient_insurance",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id"))
    private List<Insurance> insurances;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    public Patient(String name, String email, String phoneNumber, Address address,
                   List<Notification> notifications, String ssn, List<Appointment> appointments,
                   List<Insurance> insurances) {
        super(name, email, phoneNumber, address);
        this.ssn = ssn;
        this.appointments = appointments;
        this.insurances = insurances;
        this.notifications = notifications;
    }

    public Patient(){
        super();
    }

    public String getSsn() {
        return ssn;
    }

    public void setCpf(String ssn) {
        this.ssn = ssn;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
