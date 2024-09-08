package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.Appointment.DoctorAppointmentsDTO;
import java.util.List;

public interface StaffService {
    List<DoctorAppointmentsDTO> getAppointmentsOfTheDay(Long doctorId);
    List<DoctorAppointmentsDTO> getAllUpcomingAppointments(Long doctorId, Integer daysInterval);
    void cancelAppointment(Long appointmentId, Long doctorId);
    void cancelAllAppointments(Long doctorId);
}
