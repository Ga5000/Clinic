package com.ga5000.Clinic.dtos;

import com.ga5000.Clinic.entities.enums.InsuranceType;

public record InsuranceDTO(Long insuranceId, String enterprise, double coPaymentPercentage, InsuranceType insuranceType) {
}
