package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    // Get a list of appointments for a patient with details of the appointment and doctor
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, d.name as doctorName, d.speciality as doctorSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn")
    List<Tuple> findMyAppointments(@Param("ssn") String ssn);

    // Get a list of appointments for a patient on a specific date with details of the appointment and doctor
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.patient.ssn = :ssn AND a.date = :filterDate")
    List<Tuple> findAppointmentHistoryFilteredByDate(@Param("ssn") String ssn, @Param("filterDate") LocalDate filterDate);

    // Get a list of appointments for a patient filtered by a specific doctor's speciality with details of the appointment and doctor
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, a.doctor.name as doctorName, d.speciality as appointmentSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn AND d.speciality = :speciality")
    List<Tuple> findAppointmentsHistoryFilteredBySpeciality(@Param("ssn") String ssn, @Param("speciality") Speciality speciality);

    // Find patients by their name
    Optional<List<Patient>> findByName(String name);

    // Get a list of appointments for a patient within a date range with details of the appointment and doctor
    @Query("SELECT a.appointmentId as appointmentId, a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee, " +
            "a.status as appointmentStatus, a.doctor.name as doctorName, d.speciality as doctorSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn AND a.date BETWEEN :startDate AND :endDate")
    List<Tuple> findAppointmentsWithinDateRange(@Param("ssn") String ssn, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
