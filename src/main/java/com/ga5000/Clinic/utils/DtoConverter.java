package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;

import javax.print.Doc;
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
                data.get("speciality", Speciality.class)
        );
    }

    public static DoctorDTO covertToDoctorDTO(Doctor doctor){
        return new DoctorDTO(
                doctor.getMedicalLicense(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getGenre(),
                doctor.getSpeciality()

        );
    }
}
