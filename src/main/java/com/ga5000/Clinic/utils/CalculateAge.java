package com.ga5000.Clinic.utils;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {
    public static int calculate(LocalDate birthDate){
        LocalDate today = LocalDate.now();

        return Period.between(birthDate,today).getYears();
    }
}
