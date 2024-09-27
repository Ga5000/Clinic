package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.RegisterDoctorDTO;
import com.ga5000.Clinic.dtos.RegisterPatientDTO;
import com.ga5000.Clinic.entities.Address;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.Role;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final TokenServiceImpl tokenService;

    public AuthServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository,
                           PasswordEncoder passwordEncoder, CustomUserDetailsServiceImpl customUserDetailsService,
                           TokenServiceImpl tokenService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.tokenService = tokenService;
    }


    @Override
    public void registerPatient(RegisterPatientDTO registerPatientDTO) {
        String encodedPassword = passwordEncoder.encode(registerPatientDTO.password());
        Patient newPatient = new Patient();
        newPatient.setSsn(registerPatientDTO.ssn());
        newPatient.setName(registerPatientDTO.name());
        newPatient.setEmail(registerPatientDTO.email());
        newPatient.setPassword(encodedPassword);
        newPatient.setPhoneNumber(registerPatientDTO.phoneNumber());
        newPatient.setBirthDate(registerPatientDTO.birthDate());
        newPatient.setGenre(registerPatientDTO.genre());
        newPatient.setRole(Role.PATIENT);

        newPatient.setAddress(new Address(registerPatientDTO.street(), registerPatientDTO.number(),
                registerPatientDTO.complement(), registerPatientDTO.neighborhood(), registerPatientDTO.zip(),
                registerPatientDTO.city(), registerPatientDTO.state()));

        patientRepository.save(newPatient);
    }

    @Override
    public void registerDoctor(RegisterDoctorDTO registerDoctorDTO) {
        String encodePassword = passwordEncoder.encode(registerDoctorDTO.password());
        Doctor newDoctor = new Doctor();
        newDoctor.setName(registerDoctorDTO.name());
        newDoctor.setEmail(registerDoctorDTO.email());
        newDoctor.setPassword(encodePassword);
        newDoctor.setBirthDate(registerDoctorDTO.birthDate());
        newDoctor.setRole(Role.DOCTOR);
        newDoctor.setInsurances(registerDoctorDTO.insurances());
        newDoctor.setSpeciality(registerDoctorDTO.speciality());
        newDoctor.setEndShift(registerDoctorDTO.endShift());
        newDoctor.setStartShift(registerDoctorDTO.startShift());

        newDoctor.setAddress(new Address(registerDoctorDTO.street(), registerDoctorDTO.number(),
                registerDoctorDTO.complement(), registerDoctorDTO.neighborhood(), registerDoctorDTO.zip(),
                registerDoctorDTO.city(), registerDoctorDTO.state()));

        doctorRepository.save(newDoctor);
    }

    @Override
    public void deleteUser(String userIdentification) {
        Optional<Patient> patient = patientRepository.findById(userIdentification);
        Optional<Doctor> doctor = doctorRepository.findById(userIdentification);
        if (patient.isPresent()) {
            patientRepository.deleteById(userIdentification);
        } else if (doctor.isPresent()) {
            doctorRepository.deleteById(userIdentification);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public String loginUser(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return null;
        }
        return tokenService.generateToken(userDetails);
    }
}
