package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.AppointmentsOfTheDayDTO;
import com.ga5000.Clinic.repositories.StaffRepository;
import com.ga5000.Clinic.services.interfaces.StaffService;
import com.ga5000.Clinic.utils.DtoConversion;
import com.ga5000.Clinic.utils.Finder;


import java.util.List;

public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<AppointmentsOfTheDayDTO> getAppointmentsOfTheDay(Long doctorId) {
        Finder.findStaffById(doctorId);
        return staffRepository.findAppointmentsOfTheDay(doctorId)
                .stream().map(DtoConversion::toAppointmentsOfTheDayDTO).toList();
    }

    @Override
    public void cancelAppointment(Long appointmentId, Long doctorId) {
        Finder.findAppointmentById(appointmentId);
        Finder.findStaffById(doctorId);
        staffRepository.cancelAppointment(appointmentId,doctorId);
    }

    @Override
    public void cancelAllAppointments(Long doctorId) {
        Finder.findStaffById(doctorId);
        staffRepository.cancelAllAppointments(doctorId);
    }


}
