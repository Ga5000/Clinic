package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.Appointment.AllAppointmentsDTO;
import com.ga5000.Clinic.dtos.Appointment.AppointmentDTO;
import com.ga5000.Clinic.dtos.Appointment.DoctorAppointmentsDTO;
import com.ga5000.Clinic.dtos.Patient.PatientDTO;
import com.ga5000.Clinic.dtos.Staff.StaffDTO;
import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.Staff;
import com.ga5000.Clinic.entities.enums.AgeGroup;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Gender;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Component
public class DtoConversion {
    public static AppointmentDTO toAppointmentDTO(Tuple tuple){
        return new AppointmentDTO(
                tuple.get("appointmentId", Long.class),
                tuple.get("appointmentDate", LocalDate.class),
                Time.valueOf(tuple.get("appointmentTime", LocalTime.class)),
                tuple.get("speciality", Speciality.class),
                tuple.get("doctorName", String.class)
        );
    }

    public static AllAppointmentsDTO toAllAppointmentsDTO(Tuple tuple){
        return new AllAppointmentsDTO(
                tuple.get("appointmentId", Long.class),
                tuple.get("appointmentDate", LocalDate.class),
                Time.valueOf(tuple.get("appointmentTime", LocalTime.class)),
                tuple.get("speciality", Speciality.class),
                tuple.get("doctorName", String.class),
                tuple.get("appointmentStatus", AppointmentStatus.class)
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

    public static PatientDTO toPatientDTO(Tuple tuple){
        return new PatientDTO(
               tuple.get("patientId", Long.class),
               tuple.get("name", String.class),
               tuple.get("age", Integer.class),
               tuple.get("birthDate", LocalDate.class),
               tuple.get("gender", Gender.class),
               tuple.get("email", String.class),
               tuple.get("phoneNumber", String.class),
               tuple.get("ageGroup", AgeGroup.class)
        );
    }

    public static StaffDTO toStaffDTO(Staff staff){
        return new StaffDTO(
                staff.getStaffId(),
                staff.getName(),
                staff.getEmail(),
                staff.getAge(),
                staff.getBirthDate(),
                staff.getSpeciality(),
                staff.getGender(),
                staff.getRole(),
                ShiftMapping.getTimeRange(staff.getShift())
        );
    }

}
