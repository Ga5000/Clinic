package com.clinic.api.ga5000.utils;

import com.clinic.api.ga5000.entities.*;
import com.clinic.api.ga5000.exceptions.AppointmentNotFoundException;
import com.clinic.api.ga5000.exceptions.AvailabilityNotFoundException;
import com.clinic.api.ga5000.exceptions.InsuranceNotFoundException;
import com.clinic.api.ga5000.exceptions.UserNotFoundException;
import com.clinic.api.ga5000.repositories.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Finder {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final InsuranceRepository insuranceRepository;
    private final AvailabilityRepository doctorAvailabilityRepository;

    public Finder(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository,
                  InsuranceRepository insuranceRepository, AvailabilityRepository doctorAvailabilityRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.insuranceRepository = insuranceRepository;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public Patient findPatientById(UUID id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
    }

    public void findPatientBySsn(String ssn){
        patientRepository.findBySsn(ssn)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
    }

    public Patient findPatientBySsn2(String ssn){
        return patientRepository.findBySsn(ssn)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
    }

    public Doctor findDoctorById(UUID id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));
    }

    public void findDoctorByMedicalLicense(String medicalLicense){
        doctorRepository.findByMedicalLicense(medicalLicense)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));
    }

    public Doctor findDoctorByMedicalLicense2(String medicalLicense){
        return doctorRepository.findByMedicalLicense(medicalLicense)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));
    }

    public Appointment findAppointmentById(UUID id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
    }

    public Insurance findInsuranceById(UUID id){
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException("Insurance not found"));

    }

    public DoctorAvailability findAvailabilityById(UUID id){
        return doctorAvailabilityRepository.findById(id)
                .orElseThrow(() -> new AvailabilityNotFoundException("Availability not found"));
    }

}
