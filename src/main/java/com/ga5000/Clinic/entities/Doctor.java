package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Doctor extends Person{
    @Id
    private String crm;

    @Column(nullable = false)
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


    public Doctor(String name, String email, String phoneNumber, Address address,
                  String crm, Speciality speciality, LocalTime startShift,
                  LocalTime endShift, List<Appointment> appointments, List<Insurance> insurances) {
        super(name, email, phoneNumber, address);
        this.crm = crm;
        this.speciality = speciality;
        this.startShift = startShift;
        this.endShift = endShift;
        this.appointments = appointments;
        this.insurances = insurances;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
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
}
