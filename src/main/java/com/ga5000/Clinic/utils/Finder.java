package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.InsuranceRepository;
import com.ga5000.Clinic.repositories.PatientRepository;

import java.util.UUID;

public class Finder {

    public static PatientRepository patientRepository;
    public static DoctorRepository doctorRepository;
    public static AppointmentRepository appointmentRepository;
    public static InsuranceRepository insuranceRepository;

    public Finder(PatientRepository patientRepository, DoctorRepository doctorRepository,
                  AppointmentRepository appointmentRepository, InsuranceRepository insuranceRepository) {
        Finder.patientRepository = patientRepository;
        Finder.doctorRepository = doctorRepository;
        Finder.appointmentRepository = appointmentRepository;
        Finder.insuranceRepository = insuranceRepository;
    }

    public static void findPatientBySsn(String ssn){
        Patient patient = patientRepository.findById(ssn)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public static void findDoctorByMedicalLicense(String medicalLicense){
        Doctor doctor = doctorRepository.findById(medicalLicense)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public static void findAppointmentById(UUID appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public static Patient findAndReturnPatientBySsn(String ssn){
        return patientRepository.findById(ssn)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public static Doctor findAndReturnDoctorByMedicalLicense(String medicalLicense){
        return doctorRepository.findById(medicalLicense)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
