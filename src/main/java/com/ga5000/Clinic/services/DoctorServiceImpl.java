package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.services.interfaces.DoctorService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final Finder finder;

    public DoctorServiceImpl(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, Finder finder) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.finder = finder;
    }

    @Override
    public void cancelAppointment(String medicalLicense, UUID appointmentId) {
       finder.findDoctorByMedicalLicense(medicalLicense);
        finder.findAppointmentById(appointmentId);

        appointmentRepository.cancelAppointmentForDoctor(medicalLicense, appointmentId);
    }

    @Override
    public void cancelAllAppointmentsOfADay(String medicalLicense, LocalDate date) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        appointmentRepository.cancelAllAppointmentsOfADay(medicalLicense, date);
    }

    @Override
    public List<AppointmentDTO> getDoctorAppointmentsFilteredByDate(String medicalLicense, LocalDate filterDate) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        return appointmentRepository.findAppointmentsFilteredByDate(medicalLicense, filterDate)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getDoctorAppointmentsWithinTimeRangeFilteredByDate(String medicalLicense, LocalDate filterDate, LocalTime startTime, LocalTime endTime) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        return appointmentRepository.findAppointmentsWithinTimeRangeFilteredByDate(medicalLicense, filterDate, startTime, endTime)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<DoctorDTO> getDoctorsByEnterpriseAcceptance(String enterprise) {
        return doctorRepository.findDoctorsThatAcceptInsuranceFromAnEnterprise(enterprise)
                .stream().map(DtoConverter::covertToDoctorDTO).toList();
    }

    @Override
    public List<DoctorDTO> getDoctorsBySpeciality(Speciality speciality) {
        return doctorRepository.findBySpeciality(speciality)
                .stream().map(DtoConverter::covertToDoctorDTO).toList();
    }
}
