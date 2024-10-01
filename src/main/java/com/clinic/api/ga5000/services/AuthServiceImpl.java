package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.DeleteAccountDTO;
import com.clinic.api.ga5000.dtos.DoctorRegisterDTO;
import com.clinic.api.ga5000.dtos.LoginDTO;
import com.clinic.api.ga5000.dtos.PatientRegisterDTO;
import com.clinic.api.ga5000.entities.Doctor;
import com.clinic.api.ga5000.entities.Patient;
import com.clinic.api.ga5000.entities.UserEntity;
import com.clinic.api.ga5000.entities.enums.Role;
import com.clinic.api.ga5000.repositories.DoctorRepository;
import com.clinic.api.ga5000.repositories.PatientRepository;
import com.clinic.api.ga5000.repositories.UserEntityRepository;
import com.clinic.api.ga5000.services.interfaces.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserEntityRepository userRepository;
    private final TokenServiceImpl tokenService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserEntityRepository userRepository, TokenServiceImpl tokenService,
                           PatientRepository patientRepository, DoctorRepository doctorRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public String login(LoginDTO loginDTO) {
        UserDetails userDetails = loadUserByUsername(loginDTO.email());
        if (!passwordEncoder.matches(loginDTO.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return tokenService.generateToken((UserEntity) userDetails);
    }

    public void registerPatient(PatientRegisterDTO registerDTO) {
        try {
            loadUserByUsername(registerDTO.email());
            throw new BadCredentialsException("User already exists");
        } catch (UsernameNotFoundException e) {
                Patient patient = new Patient();
                BeanUtils.copyProperties(registerDTO, patient);
                patient.setPassword(passwordEncoder.encode(patient.getPassword()));
                patient.setRole(Role.PATIENT);
                patientRepository.save(patient);
        }

    }

    public void registerDoctor(DoctorRegisterDTO registerDTO) {
        try {
            loadUserByUsername(registerDTO.email());
            throw new BadCredentialsException("User already exists");
        } catch (UsernameNotFoundException e) {
            Doctor doctor = new Doctor();
            BeanUtils.copyProperties(registerDTO, doctor);
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctor.setRole(Role.DOCTOR);
            doctorRepository.save(doctor);
        }

    }

    public void deleteUser(DeleteAccountDTO deleteAccountDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails currentUserDetails) {
                String currentUserName = currentUserDetails.getUsername();

                if (currentUserName.equals(deleteAccountDTO.email()) &&
                        passwordEncoder.matches(deleteAccountDTO.password(), currentUserDetails.getPassword())) {

                    UserEntity user = userRepository.findByEmail(deleteAccountDTO.email())
                            .orElseThrow(() -> new UsernameNotFoundException(deleteAccountDTO.email()));


                    if (user instanceof Patient) {
                        patientRepository.delete((Patient) user);
                    } else if (user instanceof Doctor) {
                        doctorRepository.delete((Doctor) user);
                    }
                } else {
                    throw new BadCredentialsException("Invalid email or password");
                }
            }
        } else {
            throw new RuntimeException("User is not authenticated");
        }
    }
}
