package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.Appointment.AppointmentDTO;
import com.ga5000.Clinic.dtos.Appointment.DoctorAppointmentsDTO;
import com.ga5000.Clinic.dtos.Patient.PatientDTO;
import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.AgeGroup;
import com.ga5000.Clinic.entities.enums.Gender;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;


@Component
public class DtoConversion {
    public static AppointmentDTO toAppointmentDTO(Appointment appointment){
        return new AppointmentDTO(
                appointment.getAppointmentId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getSpeciality(),
                appointment.getDoctor().getName()
        );
    }

    public static DoctorAppointmentsDTO toDoctorAppointmentsDTO(Tuple tuple){
        return new DoctorAppointmentsDTO(
                tuple.get("appointmentId", Long.class),
                tuple.get("patientName", String.class),
                tuple.get("patientAge", Integer.class),
                tuple.get("patientBirthDate", LocalDate.class),
                tuple.get("patientGender", Gender.class),
                tuple.get("patientAgeGroup", AgeGroup.class),
                tuple.get("appointmentDate", LocalDate.class),
                tuple.get("appointmentTime", LocalTime.class)
        );
    }

    public static PatientDTO toPatientDTO(Patient patient){
        return new PatientDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getAge(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getAgeGroup()
        );
    }

}
