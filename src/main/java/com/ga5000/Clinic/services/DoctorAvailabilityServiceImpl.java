package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.DoctorAvailabilityDTO;
import com.ga5000.Clinic.dtos.DoctorDTO;
import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.State;
import com.ga5000.Clinic.repositories.DoctorAvailabilityRepository;
import com.ga5000.Clinic.services.interfaces.DoctorAvailabilityService;
import com.ga5000.Clinic.utils.DtoConverter;
import com.ga5000.Clinic.utils.Finder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final Finder finder;

    public DoctorAvailabilityServiceImpl(DoctorAvailabilityRepository doctorAvailabilityRepository, Finder finder) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
        this.finder = finder;
    }

    @Override
    public List<DoctorDTO> getAvailableDoctorsWithNoConflictingAppointments(LocalDate date, LocalTime startTime,
                                                                            LocalTime endTime, City city, State state) {
        List<Doctor> doctorList = doctorAvailabilityRepository.findAvailableDoctorsWithNoConflictingAppointments(date, startTime, endTime, city, state);
        if(doctorList.isEmpty()){
            throw new RuntimeException("No Available doctors for selected date or time");
        }
            return doctorList.stream().map(DtoConverter::covertToDoctorDTO).toList();
    }

    @Override
    public List<DoctorAvailabilityDTO> getAllDoctorAvailability(String medicalLicense) {
        finder.findDoctorByMedicalLicense(medicalLicense);
        List<DoctorAvailability> availabilityList = doctorAvailabilityRepository.findAll();
            if(availabilityList.isEmpty()){
            throw new RuntimeException("This doctor doesn't have any availability");
            }
                return availabilityList.stream().map(DtoConverter::convertToDoctorAvailabilityDTO)
                .toList();

    }

    @Override
    @Transactional
    public void addAvailability(String medicalLicense, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Doctor doctor = finder.findAndReturnDoctorByMedicalLicense(medicalLicense);
        DoctorAvailability newAvailability = new DoctorAvailability(doctor,date,startTime,endTime);
        doctorAvailabilityRepository.save(newAvailability);
    }

    @Override
    @Transactional
    public void updateAvailability(Long availabilityId, String medicalLicense, LocalDate newDate, LocalTime newStartTime, LocalTime newEndTime) {
        DoctorAvailability availability = finder.findAndReturnAvailabilityById(availabilityId);
        finder.findDoctorByMedicalLicense(medicalLicense);
        availability.setDate(newDate);
        availability.setStartTime(newStartTime);
        availability.setEndTime(newEndTime);
        doctorAvailabilityRepository.save(availability);
    }

    @Override
    @Transactional
    public void deleteAvailability(Long availabilityId, String medicalLicense) {
        finder.findAvailabilityById(availabilityId);
        finder.findDoctorByMedicalLicense(medicalLicense);

        doctorAvailabilityRepository.deleteById(availabilityId);
    }


}
