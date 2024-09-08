package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.AppointmentsOfTheDayDTO;
import com.ga5000.Clinic.entities.Appointment;
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
                appointment.getDoctor().getName(),
                appointment.getStatus()
        );
    }

    public static AppointmentsOfTheDayDTO toAppointmentsOfTheDayDTO(Tuple tuple){
        return new AppointmentsOfTheDayDTO(
                tuple.get("appointmentId", Long.class),
                tuple.get("name", String.class),
                tuple.get("age", Integer.class),
                tuple.get("birthDate", LocalDate.class),
                tuple.get("gender", Gender.class),
                tuple.get("ageGroup", AgeGroup.class),
                tuple.get("appointmentTime", LocalTime.class)
        );
    }

}
