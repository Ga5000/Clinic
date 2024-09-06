package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.exceptions.AppointmentNotFoundException;
import com.ga5000.Clinic.exceptions.PatientNotFoundException;
import com.ga5000.Clinic.exceptions.StaffNotFoundException;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.repositories.StaffRepository;

import org.springframework.stereotype.Component;

@Component
public class Finder {
    private static PatientRepository patientRepository;
    private static StaffRepository staffRepository;
    private static AppointmentRepository appointmentRepository;

    public Finder(PatientRepository patientRepository, StaffRepository staffRepository,
                  AppointmentRepository appointmentRepository) {
        Finder.patientRepository = patientRepository;
        Finder.staffRepository = staffRepository;
        Finder.appointmentRepository = appointmentRepository;
    }

    public static void findPatientById(Long patientId){
        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient doesn't exist or wasn't found"));
    }

    public static void findAppointmentById(Long appointmentId){
        appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment doesn't exist or wasn't found"));
    }

    public static void findStaffById(Long staffId){
        staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException("Staff doesn't exist or wasn't found"));
    }
}
