package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.enums.City;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    @Query("SELECT d FROM DoctorAvailability da " +
            "JOIN da.doctor d " +
            "LEFT JOIN d.appointments a ON a.date = :date " +
            "WHERE da.date = :date " +
            "AND da.startTime <= :startTime AND da.endTime >= :endTime " +
            "AND d.address.city = :city AND d.address.state = :state " +
            "AND (a IS NULL OR (a.time NOT BETWEEN :startTime AND :endTime))")
    List<Doctor> findAvailableDoctorsWithNoConflictingAppointments(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("city") String city,
            @Param("state") String state
    );

}
