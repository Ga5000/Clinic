package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    // Method to cancel an appointment by ID and ssn of a patient
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.appointmentId = :appointmentId and a.patient.ssn = :ssn")
    void cancelAppointment(@Param("ssn") String ssn, @Param("appointmentId") UUID appointmentId);

    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.appointmentId = :appointmentId and a.doctor.medicalLicense = :medicalLicense")
    void cancelAppointmentForDoctor(@Param("medicalLicense") String medicalLicense, @Param("appointmentId") UUID appointmentId);

    // Method to cancel all appointments on a given date for a doctor
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.date = :date and a.doctor.medicalLicense = :medicalLicense")
    void cancelAllAppointmentsOfTheDay(@Param("medicalLicense") String medicalLicense, @Param("date") LocalDate date);

    // Method to find appointments filtered by date and doctor's medical license
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime," +
            " a.status as appointmentStatus, a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.doctor.medicalLicense = :medicalLicense AND a.date = :filterDate")
    List<Tuple> findAppointmentsFilteredByDate(@Param("medicalLicense") String medicalLicense, @Param("filterDate") LocalDate filterDate);

    // Method to find appointments within a specific time range, filtered by date and doctor's medical license
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime, a.status as appointmentStatus, " +
            "a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.doctor.medicalLicense = :medicalLicense AND a.date = :filterDate " +
            "AND a.time BETWEEN :startTime AND :endTime")
    List<Tuple> findAppointmentsWithinTimeRangeFilteredByDate(@Param("medicalLicense") String medicalLicense,
                                                              @Param("filterDate") LocalDate filterDate,
                                                              @Param("startTime") LocalTime startTime,
                                                              @Param("endTime") LocalTime endTime);
}
