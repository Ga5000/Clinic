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
}
