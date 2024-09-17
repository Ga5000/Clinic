package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;


@MappedSuperclass
public abstract class Person {

    @Column(nullable = false, length = 140)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
     private int age;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private Genre genre;


    @Column(nullable = false)
    private String phoneNumber;

    @Embedded
    @Column(nullable = false)
    private Address address;


    public Person(String name, String password, String email, int age, LocalDate birthDate,
                  Genre genre, String phoneNumber, Address address) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.age = calculateAge(this.birthDate);
        this.genre = genre;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.age = calculateAge(birthDate);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    protected int calculateAge(LocalDate birthDate){
        return birthDate != null ? LocalDate.now().getYear() - birthDate.getYear() : 0;
    }

}
