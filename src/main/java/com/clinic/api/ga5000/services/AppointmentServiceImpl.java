package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.dtos.AppointmentDTO;
import com.clinic.api.ga5000.entities.Appointment;

import com.clinic.api.ga5000.entities.Doctor;
import com.clinic.api.ga5000.entities.enums.AppointmentStatus;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.repositories.AppointmentRepository;
import com.clinic.api.ga5000.services.interfaces.AppointmentService;
import com.clinic.api.ga5000.utils.DtoConverter;
import com.clinic.api.ga5000.utils.Finder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final Finder finder;
    private final NotificationServiceImpl notificationService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, Finder finder,
                                  NotificationServiceImpl notificationService) {
        this.appointmentRepository = appointmentRepository;
        this.finder = finder;
        this.notificationService = notificationService;
    }

    @Override
    public void markAsFinished(UUID appointmentId) {
        Appointment appointment = finder.findAppointmentById(appointmentId);
        appointment.setStatus(AppointmentStatus.FINISHED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        notificationService.sendConfirmationNotification(appointment);
    }

    @Override
    public AppointmentDTO getAppointmentById(UUID appointmentId) {
        return DtoConverter.convertToAppointmentDTO(finder.findAppointmentById(appointmentId));
    }

    @Override
    public void cancelAppointment(UUID appointmentId, String ssn, String medicalLicense) {
        Appointment appointment = finder.findAppointmentById(appointmentId);
        finder.findDoctorByMedicalLicense(medicalLicense);
        appointmentRepository.cancelAppointment(appointmentId,ssn,medicalLicense);
        notificationService.sendCancelationNotification(appointment);
    }

    @Override
    public void cancelAllAppointmentsOfADay(LocalDate date, String medicalLicense) {
       Doctor doctor = finder.findDoctorByMedicalLicense2(medicalLicense);
        Set<Appointment> appointments = appointmentRepository.findAppointmentByAppointmentDateAndDoctorMedicalLicense(date, medicalLicense);
        appointmentRepository.cancelAllAppointmentsOfADay(date, medicalLicense);
        for(Appointment appointment : appointments){
            notificationService.sendCancelationNotification(appointment);
        }

    }

    @Override
    public Set<AppointmentDTO> findAppointmentsBySsn(String ssn) {
        finder.findPatientBySsn(ssn);
        return appointmentRepository.findAppointments(ssn,null,null,
                null,null,null).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicense(String medicalLicense) {
       finder.findDoctorByMedicalLicense(medicalLicense);
        return appointmentRepository.findAppointments(null,medicalLicense, null,
                        null,null,null).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsBySsnAndDate(String ssn, LocalDate filterDate) {
        finder.findPatientBySsn(ssn);
        return appointmentRepository.findAppointments(ssn,null, filterDate,
                        null,null,null).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicenseAndDate(String medicalLicense, LocalDate filterDate) {
       finder.findDoctorByMedicalLicense(medicalLicense);
        return appointmentRepository.findAppointments(null,medicalLicense, filterDate,
                        null,null,null).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsBySsnAndSpeciality(String ssn, Speciality speciality) {
        finder.findPatientBySsn(ssn);
        return appointmentRepository.findAppointments(ssn,null,null,
                        speciality,null,null).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findAppointments(null,null,null,
                        null,startDate, endDate).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsBySsnAndDateRange(String ssn, LocalDate startDate, LocalDate endDate) {
        finder.findPatientBySsn(ssn);
        return appointmentRepository.findAppointments(ssn,null,null,
                        null,startDate, endDate).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsByDoctorMedicalLicenseAndDateRange(String medicalLicense, LocalDate startDate, LocalDate endDate) {
       finder.findDoctorByMedicalLicense(medicalLicense);
        return appointmentRepository.findAppointments(null,medicalLicense,null,
                        null,startDate, endDate).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<AppointmentDTO> findAppointmentsBySsnAndSpecialityAndDateRange(String ssn, Speciality speciality, LocalDate startDate, LocalDate endDate) {
       finder.findPatientBySsn(ssn);
        return appointmentRepository.findAppointments(ssn,null,null,
                        speciality, startDate, endDate).stream().map(DtoConverter::convertToAppointmentDTO)
                .collect(Collectors.toSet());
    }

}
