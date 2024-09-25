package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.RegisterPatientDTO;
import com.ga5000.Clinic.entities.Doctor;


public interface AuthService {
    void registerPatient(RegisterPatientDTO registerPatientDTO);
    void registerDoctor(Doctor doctor);
    void deleteUser(String userIdentification); // can be ssn or medicalLicense
    void loginUser(String email, String password);
}
