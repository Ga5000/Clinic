package com.ga5000.Clinic.services;

import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public CustomUserDetailsServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findByEmail(email);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            List<GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + patient.getRole()));

            return User.withUsername(patient.getEmail())
                    .password(patient.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(!patient.isEnabled())
                    .build();
        }

        Optional<Doctor> doctorOptional = doctorRepository.findByEmail(email);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            List<GrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + doctor.getRole()));

            return User.withUsername(doctor.getEmail())
                    .password(doctor.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(!doctor.isEnabled())
                    .build();
        }

        // If neither a patient nor a doctor is found, throw an exception
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
