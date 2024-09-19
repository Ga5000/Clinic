package com.ga5000.Clinic.services;


import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.repositories.DoctorAvailabilityRepository;
import com.ga5000.Clinic.repositories.DoctorRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.PatientService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorRepository doctorRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository,
                              DoctorAvailabilityRepository doctorAvailabilityRepository,
                              DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional
    public void bookAppointment(String ssn, String medicalLicense, LocalDate selectedDate, LocalTime selectedTime) {
        Patient patient = Finder.findAndReturnPatientBySsn(ssn);
        Doctor doctor = Finder.findAndReturnDoctorByMedicalLicense(medicalLicense);

        LocalTime blockedEndTime = selectedTime.plusMinutes(30);

        if(!isTimeSlotAvailable(doctor,selectedTime,blockedEndTime)){
            throw new IllegalArgumentException("The selected time is not available");
        }

        DoctorAvailability doctorAvailability = doctorAvailabilityRepository.
                findAvailabilityForDoctor(doctor,selectedDate,selectedTime,blockedEndTime);

        doctorAvailability.markAsBooked();
        doctorAvailabilityRepository.save(doctorAvailability);

        Appointment appointment = new Appointment(selectedDate,selectedTime,doctor,patient,0,
                                                  AppointmentStatus.SCHEDULED);

        appointmentRepository.save(appointment);
    }

    @Transactional
    @Override
    public void cancelAppointment(String ssn, UUID appointmentId) {
        Finder.findPatientBySsn(ssn);
        Finder.findAppointmentById(appointmentId);

        appointmentRepository.cancelAppointment(ssn,appointmentId);
    }

    @Override
    public List<AppointmentDTO> getMyAppointments(String ssn) {
        Finder.findPatientBySsn(ssn);
        return patientRepository.findMyAppointments(ssn)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsHistoryFilteredByDate(String ssn, LocalDate filterDate) {
        Finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentHistoryFilteredByDate(ssn,filterDate)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsHistoryFilteredBySpeciality(String ssn, Speciality speciality) {
        Finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentsHistoryFilteredBySpeciality(ssn, speciality)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsWithinDateRange(String ssn, LocalDate startDate, LocalDate endDate) {
        Finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentsWithinDateRange(ssn, startDate, endDate)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    private boolean isTimeSlotAvailable(Doctor doctor, LocalTime startTime, LocalTime endTime){
        List<DoctorAvailability> availableSlots = doctor.getAvailabilities();
        return  availableSlots.removeIf(slot -> slot.getStartTime().isBefore(endTime)
                && slot.getEndTime().isAfter(startTime));
    }

    private void updateDoctorAvailability(Doctor doctor, LocalTime startTime, LocalTime endTime) {
        List<DoctorAvailability> availableSlots = doctor.getAvailabilities();
        availableSlots.removeIf(slot -> slot.getStartTime().isBefore(endTime) && slot.getEndTime().isAfter(startTime));
        doctor.setAvailabilities(availableSlots);
        doctorRepository.save(doctor);
    }

    public List<DoctorDTO> getAvailableDoctors(LocalDate date, LocalTime startTime, LocalTime endTime, Speciality speciality){
        List<DoctorAvailability> availableSlots = doctorAvailabilityRepository.
                findByDateAndTimeRangeAndSpeciality(date, startTime, endTime,speciality);

        return availableSlots.stream()
                .map(DoctorAvailability::getDoctor).distinct()
                .map(DtoConverter::covertToDoctorDTO).toList();
    }




}
