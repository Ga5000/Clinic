package com.ga5000.Clinic.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class CalculateAge {
    public static int calculate(LocalDate birthDate){
        LocalDate today = LocalDate.now();

        return Period.between(birthDate,today).getYears();
    }
}
