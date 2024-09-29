package com.clinic.api.ga5000.entities;

import com.clinic.api.ga5000.entities.enums.Genre;
import com.clinic.api.ga5000.entities.enums.Role;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.fields.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends UserEntity {
    @Column(unique = true, nullable = false)
    private String medicalLicense;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorAvailability> availabilities;

    public Doctor(String password, String email, String firstName, String lastName, String phoneNumber,
                  int age, LocalDate birthDate, Genre genre, Address address, Role role, String medicalLicense,
                  Speciality speciality, Set<Appointment> appointments, List<DoctorAvailability> availabilities) {
        super(password, email, firstName, lastName, phoneNumber, age, birthDate, genre, address, role);
        this.medicalLicense = medicalLicense;
        this.speciality = speciality;
        this.appointments = appointments;
        this.availabilities = availabilities;
        setRole(Role.DOCTOR);
    }
    public Doctor() {}

    public String getMedicalLicense() {
        return medicalLicense;
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<DoctorAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<DoctorAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
