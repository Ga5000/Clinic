package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.dtos.InsuranceDTO;
import com.clinic.api.ga5000.entities.Insurance;

import java.util.List;
import java.util.UUID;

public interface InsuranceService {
    //get all insurances
    List<InsuranceDTO> getAllInsurances();
    //get insurance by id
    InsuranceDTO getInsuranceById(UUID insuranceId);

    //add insurance to a patient
    void addInsurance(Insurance insurance);
}
