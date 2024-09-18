package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Method to make an appointment
    //void makeAppointment(Speciality speciality, LocalTime time, LocalDate date, String ssn);

    // Method to cancel an appointment by ID
    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.appointmentId = :appointmentId")
    void cancelAppointment(Long appointmentId);

    // Method to cancel all appointments on a given date
    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.date = :date")
    void cancelAllAppointmentsOfTheDay(LocalDate date);

    // Method to find appointments filtered by date and doctor's medical license
    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime," +
            " a.status as appointmentStatus, a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.doctor.medicalLicense = :medicalLicense AND a.date = :filterDate")
    List<Tuple> findAppointmentsFilteredByDate(@Param("medicalLicense") String medicalLicense, @Param("filterDate") LocalDate filterDate);

    // Method to find appointments within a specific time range, filtered by date and doctor's medical license
    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.status as appointmentStatus, " +
            "a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.doctor.medicalLicense = :medicalLicense AND a.date = :filterDate " +
            "AND a.time BETWEEN :startTime AND :endTime")
    List<Tuple> findAppointmentsWithinTimeRangeFilteredByDate(@Param("medicalLicense") String medicalLicense,
                                                              @Param("filterDate") LocalDate filterDate,
                                                              @Param("startTime") LocalTime startTime,
                                                              @Param("endTime") LocalTime endTime);
}
