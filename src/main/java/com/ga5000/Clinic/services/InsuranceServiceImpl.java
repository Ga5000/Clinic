package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.InsuranceDTO;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Insurance;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.InsuranceType;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.InsuranceRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.InsuranceService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final Finder finder;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, PatientRepository patientRepository,
                                DoctorRepository doctorRepository, Finder finder) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.finder = finder;
    }

    @Override
    public List<InsuranceDTO> getByEnterprise(String enterprise) {
       List<Insurance> insuranceList = insuranceRepository.findByEnterprise(enterprise);
       if(insuranceList.isEmpty()){
           throw new RuntimeException("Insurances were not found");
       }
        return insuranceList.stream().map(DtoConverter::convertToInsuranceDTO).toList();
    }

    @Override
    public List<InsuranceDTO> getAll() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        if(insuranceList.isEmpty()){
            throw new RuntimeException("Insurances were not found");
        }
        return insuranceList.stream().map(DtoConverter::convertToInsuranceDTO).toList();

    }

    @Override
    public List<InsuranceType> getInsuranceTypes() {
        return Arrays.stream(InsuranceType.class.getEnumConstants()).toList();
    }

    @Override
    public void add(String enterprise, double coPaymentPercentage, LocalDate expiresAt, InsuranceType insuranceType) {
        if(expiresAt.isBefore(LocalDate.now())){
            throw new RuntimeException("Expire date can't be before today");
        }
        Insurance insurance = new Insurance(enterprise,insuranceType,expiresAt,coPaymentPercentage);
        insuranceRepository.save(insurance);

    }

    @Override
    public void delete(Long insuranceId) {
        Insurance insurance = finder.findAndReturnInsuranceById(insuranceId);
        insuranceRepository.deleteById(insuranceId);
    }

    @Override
    @Transactional
    public void deletePatientInsurance(String ssn, Long insuranceId) {
        Patient patient = finder.findAndReturnPatientBySsn(ssn);
        patient.getInsurances().removeIf(insurance -> insurance.getInsuranceId().equals(insuranceId));

        patientRepository.save(patient);
    }

    @Override
    public void deleteDoctorInsurance(String medicalLicense, Long insuranceId) {
        Doctor doctor = finder.findAndReturnDoctorByMedicalLicense(medicalLicense);
        doctor.getInsurances().removeIf(insurance -> insurance.getInsuranceId().equals(insuranceId));

        doctorRepository.save(doctor);
    }

    @Override
    public void update(Long insuranceId, String enterprise, double coPaymentPercentage, LocalDate expiresAt, InsuranceType insuranceType) {
        if(expiresAt.isBefore(LocalDate.now())){
            throw new RuntimeException("Expire date can't be before today");
        }
        Insurance insurance = finder.findAndReturnInsuranceById(insuranceId);
        insurance.setInsuranceType(insuranceType);
        insurance.setEnterprise(enterprise);
        insurance.setExpiresAt(expiresAt);
        insurance.setCoPaymentPercentage(coPaymentPercentage);

        insuranceRepository.save(insurance);
    }
}
