package com.clinic.api.ga5000.entities.fields;

import com.clinic.api.ga5000.entities.enums.USState;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Address {
    private String street;

    private String city;

    @Enumerated(EnumType.STRING)
    private USState state;

    private String postalCode;

    public Address(String street, String city, USState state, String postalCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Address() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public USState getState() {
        return state;
    }

    public void setState(USState state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
