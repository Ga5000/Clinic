package com.ga5000.Clinic.services;


import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.dtos.PatientDTO;
import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.State;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.repositories.PatientRepository;
import com.ga5000.Clinic.services.interfaces.PatientService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    private final Finder finder;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository,
                              Finder finder) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.finder = finder;
    }

    @Override
    @Transactional
    public void bookAppointment(String ssn, String medicalLicense, LocalDate selectedDate, LocalTime selectedTime) {
        Patient patient = finder.findAndReturnPatientBySsn(ssn);
        Doctor doctor = finder.findAndReturnDoctorByMedicalLicense(medicalLicense);

        Appointment newAppointment = new Appointment();
        newAppointment.setPatient(patient);
        newAppointment.setDoctor(doctor);
        newAppointment.setDate(selectedDate);
        newAppointment.setTime(selectedTime);
        newAppointment.setStatus(AppointmentStatus.SCHEDULED);

        appointmentRepository.save(newAppointment);
    }


    @Transactional
    @Override
    public void cancelAppointment(String ssn, UUID appointmentId) {
        finder.findPatientBySsn(ssn);
        finder.findAppointmentById(appointmentId);

        appointmentRepository.cancelAppointment(ssn,appointmentId);
    }

    @Override
    public List<AppointmentDTO> getMyAppointments(String ssn) {
        finder.findPatientBySsn(ssn);
        return patientRepository.findMyAppointments(ssn)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsHistoryFilteredByDate(String ssn, LocalDate filterDate) {
        finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentHistoryFilteredByDate(ssn,filterDate)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsHistoryFilteredBySpeciality(String ssn, Speciality speciality) {
        finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentsHistoryFilteredBySpeciality(ssn, speciality)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsWithinDateRange(String ssn, LocalDate startDate, LocalDate endDate) {
        finder.findPatientBySsn(ssn);
        return patientRepository.findAppointmentsWithinDateRange(ssn, startDate, endDate)
                .stream().map(DtoConverter::covertToAppointmentDTO).toList();
    }

    @Override
    public List<City> getCities() {
        return Arrays.stream(City.values())
                .sorted((city1, city2) -> city1.name().compareToIgnoreCase(city2.name())).toList();
    }

    @Override
    public List<State> getStates() {
        return Arrays.stream(State.values())
                .sorted((state1, state2) -> state1.name().compareToIgnoreCase(state2.name())).toList();

    }

    @Override
    public PatientDTO getInfo(String ssn) {
        Patient patient = finder.findAndReturnPatientBySsn(ssn);
        return DtoConverter.convertToPatientDTO(patient);
    }






}
