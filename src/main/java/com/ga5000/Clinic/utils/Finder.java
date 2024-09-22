package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Finder {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final InsuranceRepository insuranceRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    public Finder(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository,
                  InsuranceRepository insuranceRepository, DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.insuranceRepository = insuranceRepository;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public void findPatientBySsn(String ssn) {
        patientRepository.findById(ssn)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void findDoctorByMedicalLicense(String medicalLicense) {
        doctorRepository.findById(medicalLicense)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void findAppointmentById(UUID appointmentId) {
        appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void findAvailabilityById(Long availabilityId){
        doctorAvailabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
    }
    public Patient findAndReturnPatientBySsn(String ssn) {
        return patientRepository.findById(ssn)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Doctor findAndReturnDoctorByMedicalLicense(String medicalLicense) {
        return doctorRepository.findById(medicalLicense)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public DoctorAvailability findAndReturnAvailability(Long availabilityId){
        return doctorAvailabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
    }


}
