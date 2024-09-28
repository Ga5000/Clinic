package com.clinic.api.ga5000.utils;

import com.clinic.api.ga5000.dtos.*;
import com.clinic.api.ga5000.entities.*;
import com.clinic.api.ga5000.entities.enums.AppointmentStatus;
import com.clinic.api.ga5000.entities.enums.Speciality;
import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class DtoConverter {
    public static AppointmentDTO convertToAppointmentDTO(Tuple tuple){
        return new AppointmentDTO(
                tuple.get("id", UUID.class),
                tuple.get("date", LocalDate.class),
                tuple.get("time", LocalTime.class),
                tuple.get("fee", Double.class),
                tuple.get("doctorName", String.class),
                tuple.get("speciality", Speciality.class),
                tuple.get("status", AppointmentStatus.class)

        );
    }

    public static AppointmentDTO convertToAppointmentDTO(Appointment appointment){
        return new AppointmentDTO(
                appointment.getAppointmentId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getFee(),
                appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName(),
                appointment.getDoctor().getSpeciality(),
                appointment.getStatus()
        );
    }

    public static DoctorDTO convertToDoctorDTO(Doctor doctor){
        return new DoctorDTO(
                doctor.getMedicalLicense(),
                doctor.getFirstName() + " " + doctor.getLastName(),
                doctor.getEmail(),
                doctor.getGenre(),
                doctor.getAge(),
                doctor.getSpeciality()
        );
    }

    public static InsuranceDTO convertToInsuranceDTO(Insurance insurance){
        return new InsuranceDTO(
                insurance.getInsuranceId(),
                insurance.getProviderName(),
                insurance.getPolicyNumber(),
                insurance.getCoverageDetails(),
                insurance.getContactNumber(),
                insurance.getPatient().getFirstName() + " " + insurance.getPatient().getLastName(),
                insurance.getPatient().getEmail(),
                insurance.getPatient().getPhoneNumber(),
                insurance.getPatient().getGenre()
        );
    }

    public static PatientDTO convertToPatientDTO(Patient patient){
        return new PatientDTO(
                patient.getSsn(),
                patient.getFirstName() + " " + patient.getLastName(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getGenre(),
                patient.getAge(),
                patient.getAddress()
        );
    }

    public static AvailabilityDTO convertToAvailabilityDTO(DoctorAvailability doctorAvailability){
        return new AvailabilityDTO(
                doctorAvailability.getDoctor().getFirstName() + " " + doctorAvailability.getDoctor().getLastName(),
                doctorAvailability.getDoctor().getGenre(),
                doctorAvailability.getDoctor().getSpeciality(),
                doctorAvailability.getDate(),
                doctorAvailability.getStartTime(),
                doctorAvailability.getEndTime()
        );
    }
}
