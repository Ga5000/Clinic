package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.dtos.AppointmentsOfTheDayDTO;

import java.util.List;

public interface StaffService {
    List<AppointmentsOfTheDayDTO> getAppointmentsOfTheDay(Long doctorId);
    void cancelAppointment(Long appointmentId, Long doctorId);
    void cancelAllAppointments(Long doctorId);
}
