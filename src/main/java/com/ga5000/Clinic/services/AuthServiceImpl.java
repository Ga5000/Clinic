package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.RegisterPatientDTO;
import com.ga5000.Clinic.entities.Address;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.Role;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl implements AuthService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository,
                           PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerPatient(RegisterPatientDTO registerPatientDTO) {
        String encodedPassword = passwordEncoder.encode(registerPatientDTO.password());
       Patient newPatient = new Patient();
       newPatient.setSsn(registerPatientDTO.ssn());
       newPatient.setName(registerPatientDTO.name());
       newPatient.setEmail(registerPatientDTO.email());
       newPatient.setPassword(encodedPassword);
       newPatient.setBirthDate(registerPatientDTO.birthDate());
       newPatient.setRole(Role.PATIENT);
       newPatient.setInsurances(registerPatientDTO.insurances());

       newPatient.setAddress(new Address(registerPatientDTO.street(), registerPatientDTO.number(),
               registerPatientDTO.complement(), registerPatientDTO.neighborhood(), registerPatientDTO.zip(),
               registerPatientDTO.city(), registerPatientDTO.state()));

       patientRepository.save(newPatient);
    }

    @Override
    public void registerDoctor(Doctor doctor) {

    }

    @Override
    public void deleteUser(String userIdentification) {

    }

    @Override
    public void loginUser(String email, String password) {

    }
}
