package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.State;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Address {

    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String zip;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;

    public Address(String street, int number, String complement, String neighborhood, String zip,
                   String city, State state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zip = zip;
        this.city = city;
        this.state = state;
    }

    public Address(){}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String cep) {
        this.zip = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
