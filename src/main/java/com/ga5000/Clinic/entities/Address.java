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
    private String cep;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;
}
