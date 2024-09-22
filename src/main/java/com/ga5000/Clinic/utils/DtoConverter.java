package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.DoctorAvailabilityDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.dtos.PatientDTO;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class DtoConverter {

    public static AppointmentDTO covertToAppointmentDTO(Tuple data){
        return new AppointmentDTO(
                data.get("appointmentId", UUID.class),
                data.get("appointmentDate", LocalDate.class),
                data.get("appointmentTime", LocalTime.class),
                data.get("appointmentFee", Double.class),
                data.get("appointmentStatus", AppointmentStatus.class),
                data.get("doctorName", String.class),
                data.get("doctorSpeciality", Speciality.class)
        );
    }

    public static DoctorDTO covertToDoctorDTO(Doctor doctor){
        return new DoctorDTO(
                doctor.getMedicalLicense(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getGenre(),
                doctor.getSpeciality(),
                doctor.getAddress().getCity(),
                doctor.getAddress().getState()

        );
    }

    public static PatientDTO convertToPatientDTO(Patient patient){
        return new PatientDTO(
                patient.getSsn(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getGenre(),
                patient.getAge(),
                patient.getPhoneNumber()
        );
    }

    public static DoctorAvailabilityDTO convertToDoctorAvailabilityDTO(DoctorAvailability doctorAvailability){
        return new DoctorAvailabilityDTO(
                doctorAvailability.getId(),
                doctorAvailability.getDoctor().getName(),
                doctorAvailability.getDoctor().getEmail(),
                doctorAvailability.getDate(),
                doctorAvailability.getStartTime(),
                doctorAvailability.getEndTime()
        );
    }
}
