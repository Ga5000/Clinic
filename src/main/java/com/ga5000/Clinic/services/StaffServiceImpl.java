package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.Appointment.DoctorAppointmentsDTO;
import com.ga5000.Clinic.dtos.Staff.StaffDTO;
import com.ga5000.Clinic.repositories.StaffRepository;
import com.ga5000.Clinic.services.interfaces.StaffService;
import com.ga5000.Clinic.utils.DtoConversion;
import com.ga5000.Clinic.utils.Finder;


import java.time.LocalDate;
import java.util.List;

public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<DoctorAppointmentsDTO> getAppointmentsOfTheDay(Long doctorId) {
        Finder.findStaffById(doctorId);
        return staffRepository.findAppointmentsOfTheDay(doctorId)
                .stream().map(DtoConversion::toDoctorAppointmentsDTO).toList();
    }

    @Override
    public List<DoctorAppointmentsDTO> getAllUpcomingAppointments(Long doctorId, Integer daysInterval) {
        Finder.findStaffById(doctorId);
        //                                                          startDate               endDate
        return staffRepository.findUpcomingAppointments(doctorId, LocalDate.now(), LocalDate.now().plusDays(daysInterval))
                .stream().map(DtoConversion::toDoctorAppointmentsDTO).toList();
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

    @Override
    public StaffDTO getStaffInfoById(Long staffId) {
        Finder.findStaffById(staffId);
        return DtoConversion.toStaffDTO(staffRepository.findStaffInfoById(staffId));
    }


}
