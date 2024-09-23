package com.ga5000.Clinic.utils;

public class FeeUtil {
    public static double applyCoPaymentPercentage(double fee, double coPaymentPercentage){
        return fee * (1 - (coPaymentPercentage / 100));
    }

}
