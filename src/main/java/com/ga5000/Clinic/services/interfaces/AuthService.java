package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.RegisterDoctorDTO;
import com.ga5000.Clinic.dtos.RegisterPatientDTO;
import com.ga5000.Clinic.entities.Doctor;


public interface AuthService {
    void registerPatient(RegisterPatientDTO registerPatientDTO);
    void registerDoctor(RegisterDoctorDTO registerDoctorDTO);
    void deleteUser(String userIdentification); // can be ssn or medicalLicense
    String loginUser(String email, String password);
}
