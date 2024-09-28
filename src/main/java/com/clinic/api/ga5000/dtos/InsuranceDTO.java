package com.clinic.api.ga5000.dtos;

import com.clinic.api.ga5000.entities.enums.Genre;

import java.util.UUID;

public record InsuranceDTO(UUID insuranceId, String providerName, String policyNumber, String coverageDetails,
                           String contactNumber, String patientName, String patientEmail,
                           String patientPhoneNumber, Genre patientGenre){}
