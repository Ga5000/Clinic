package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.InsuranceDTO;
import com.clinic.api.ga5000.entities.Insurance;
import com.clinic.api.ga5000.repositories.InsuranceRepository;
import com.clinic.api.ga5000.services.interfaces.InsuranceService;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;

import java.util.List;
import java.util.UUID;


public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final Finder finder;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, Finder finder) {
        this.insuranceRepository = insuranceRepository;
        this.finder = finder;
    }

    @Override
    public List<InsuranceDTO> getAllInsurances() {
        return insuranceRepository.findAll().stream().map(DtoConverter::convertToInsuranceDTO).toList();
    }

    @Override
    public InsuranceDTO getInsuranceById(UUID insuranceId) {
       return DtoConverter.convertToInsuranceDTO(finder.findInsuranceById(insuranceId));
    }

    @Override
    public void addInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }
}
