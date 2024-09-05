package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.Gender;
import com.ga5000.Clinic.entities.enums.Role;
import com.ga5000.Clinic.entities.enums.Shift;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.utils.CalculateAge;
import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @OneToMany
    private Set<Appointment> appointments;

    public Staff(Long staffId, String name, String email, String password, int age, Role role,
                 LocalDate birthDate, Speciality speciality, Gender gender,
                 Shift shift, Set<Appointment> appointments) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.role = role;
        this.birthDate = birthDate;
        this.speciality = speciality;
        this.gender = gender;
        this.shift = shift;
        this.appointments = appointments;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.age = CalculateAge.calculate(this.birthDate);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments != null ? appointments : new HashSet<>();
    }
}
