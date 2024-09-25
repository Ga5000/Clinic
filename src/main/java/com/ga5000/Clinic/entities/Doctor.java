package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.Genre;
import com.ga5000.Clinic.entities.enums.Role;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends Person {
    @Id
    private String medicalLicense;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Column(nullable = false)
    private LocalTime startShift;

    @Column(nullable = false)
    private LocalTime endShift;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "doctor_insurance",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id"))
    private List<Insurance> insurances;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorAvailability> availabilities;

    public Doctor(String name, String password, String email, int age, LocalDate birthDate, Genre genre,
                  String phoneNumber, Address address, Role role, boolean enabled, String medicalLicense, Speciality speciality,
                  LocalTime startShift, LocalTime endShift, List<Appointment> appointments, List<Insurance> insurances,
                  List<DoctorAvailability> availabilities) {
        super(name, password, email, age, birthDate, genre, phoneNumber, address, role, enabled);
        this.medicalLicense = medicalLicense;
        this.speciality = speciality;
        this.startShift = startShift;
        this.endShift = endShift;
        this.appointments = appointments;
        this.insurances = insurances;
        this.availabilities = availabilities;
        setRole(Role.DOCTOR);
    }

    public Doctor() {
        super();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR"));
    }

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

    public LocalTime getStartShift() {
        return startShift;
    }

    public void setStartShift(LocalTime startShift) {
        this.startShift = startShift;
    }

    public LocalTime getEndShift() {
        return endShift;
    }

    public void setEndShift(LocalTime endShift) {
        this.endShift = endShift;
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

    public List<DoctorAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<DoctorAvailability> availabilities) {
        this.availabilities = availabilities;
    }
}
