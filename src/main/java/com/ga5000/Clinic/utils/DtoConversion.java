package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.entities.Appointment;

public class DtoConversion {
    public static AppointmentDTO toAppointmentDTO(Appointment appointment){
        return new AppointmentDTO(
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getSpeciality(),
                appointment.getDoctor().getName(),
                appointment.getStatus()
        );
    }
}
