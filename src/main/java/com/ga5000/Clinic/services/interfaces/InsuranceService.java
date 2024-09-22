package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.InsuranceDTO;
import com.ga5000.Clinic.entities.enums.InsuranceType;

import java.time.LocalDate;
import java.util.List;

public interface InsuranceService {
   List< InsuranceDTO> getByEnterprise(String enterprise);
    List<InsuranceDTO> getAll();
    List<InsuranceType> getInsuranceTypes();

    void add(String enterprise, double coPaymentPercentage, LocalDate expiresAt, InsuranceType insuranceType);
    void delete(Long insuranceId);

    void deletePatientInsurance(String ssn, Long insuranceId);
    void deleteDoctorInsurance(String medicalLicense, Long insuranceId);

    void update(Long insuranceId, String enterprise, double coPaymentPercentage, LocalDate expiresAt, InsuranceType insuranceType);
}
