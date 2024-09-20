package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    @Query("SELECT da FROM DoctorAvailability da WHERE da.doctor = :doctor AND da.date = :date")
    List<DoctorAvailability> findByDoctorAndDate(@Param("doctor") Doctor doctor, @Param("date") LocalDate date);

    @Query("SELECT da FROM DoctorAvailability da WHERE da.doctor.medicalLicense = :medicalLicense AND da.date = :date AND da.startTime <= :startTime AND da.endTime >= :endTime")
    DoctorAvailability findAvailabilityForDoctor(
            @Param("medicalLicense") String medicalLicense,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    @Query("SELECT da FROM DoctorAvailability da WHERE da.date = :date AND da.startTime <= :startTime AND da.endTime >= :endTime AND da.doctor.speciality = :speciality")
    List<DoctorAvailability> findByDateAndTimeRangeAndSpeciality(@Param("date") LocalDate date,
                                                                 @Param("startTime") LocalTime startTime,
                                                                 @Param("endTime") LocalTime endTime,
                                                                 @Param("speciality") Speciality speciality);
    @Query("SELECT DISTINCT d.address.city as cities FROM Doctor d")
    List<String> findCities();


}
